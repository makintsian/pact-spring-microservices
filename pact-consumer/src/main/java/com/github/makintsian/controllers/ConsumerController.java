package com.github.makintsian.controllers;

import com.github.makintsian.model.FailRequest;
import com.github.makintsian.model.FailResponse;
import com.github.makintsian.model.SuccessRequest;
import com.github.makintsian.model.SuccessResponse;
import com.github.makintsian.services.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Autowired
    private ConsumerService service;

    @GetMapping("/consumer/success")
    public ResponseEntity<SuccessResponse> consumerSuccess(@RequestParam(value = "name") String name) {
        SuccessRequest request = SuccessRequest.builder()
                .name(name)
                .description("success test")
                .build();
        return ResponseEntity.ok(service.getSuccess(request));
    }

    @GetMapping("/consumer/fail")
    public ResponseEntity<FailResponse> consumerFail(@RequestParam(value = "name") String name) {
        FailRequest request = FailRequest.builder()
                .name(name)
                .description("fail test")
                .empty("empty value")
                .build();
        return ResponseEntity.ok(service.getFail(request));
    }
}
