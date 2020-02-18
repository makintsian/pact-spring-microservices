package com.github.makintsian.services;

import com.github.makintsian.model.FailRequest;
import com.github.makintsian.model.FailResponse;
import com.github.makintsian.model.SuccessRequest;
import com.github.makintsian.model.SuccessResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class ConsumerService {

    private static final String PROVIDER_SUCCESS_URI = "http://provider:8082/provider/success";
    private static final String PROVIDER_FAIL_URI = "http://provider:8082/provider/fail";

    public SuccessResponse getSuccess(SuccessRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<SuccessRequest> httpEntity = new HttpEntity<>(request);
        ResponseEntity<SuccessResponse> response = restTemplate.postForEntity(URI.create(PROVIDER_SUCCESS_URI), httpEntity, SuccessResponse.class);
        return response.getBody();
    }

    public FailResponse getFail(FailRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<FailRequest> httpEntity = new HttpEntity<>(request);
        ResponseEntity<FailResponse> response = restTemplate.postForEntity(URI.create(PROVIDER_FAIL_URI), httpEntity, FailResponse.class);
        return response.getBody();
    }
}
