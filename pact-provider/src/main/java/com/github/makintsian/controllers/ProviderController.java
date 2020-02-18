package com.github.makintsian.controllers;

import com.github.makintsian.model.FailRequest;
import com.github.makintsian.model.FailResponse;
import com.github.makintsian.model.SuccessRequest;
import com.github.makintsian.model.SuccessResponse;
import com.github.makintsian.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {

    @Autowired
    private ProviderService service;

    @PostMapping(path = "/provider/success", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuccessResponse> providerSuccess(@RequestBody SuccessRequest request) {
        return ResponseEntity.ok(service.saveSuccess(request));
    }

    @PostMapping(path = "/provider/fail", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FailResponse> providerFail(@RequestBody FailRequest request) {
        return ResponseEntity.ok(service.saveFail(request));
    }
}
