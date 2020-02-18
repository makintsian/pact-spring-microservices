package com.github.makintsian;

import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.PactProviderRule;
import au.com.dius.pact.consumer.junit.PactVerification;
import au.com.dius.pact.core.model.PactSpecVersion;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.github.makintsian.model.SuccessRequest;
import com.github.makintsian.model.SuccessResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

public class ConsumerSuccessTest {

    private RestTemplate restTemplate = new RestTemplate();
    private Gson gson = new GsonBuilder().create();

    @Rule
    public PactProviderRule mockProvider = new PactProviderRule("success_provider", PactSpecVersion.V3, this);

    @Pact(provider = "success_provider", consumer = "success_consumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        Map<String, String> headers = Map.of("Content-Type", MediaType.APPLICATION_JSON_VALUE);

//        PactDslJsonBody successRequest = new PactDslJsonBody()
//                .stringValue("name", "name_test")
//                .stringType("description", "description_test");

        SuccessRequest successRequest = SuccessRequest.builder()
                .name("name_test")
                .description("description_test")
                .build();

        SuccessResponse successResponse = new SuccessResponse(1, "name_test", "description_test");

        return builder.given("create success test")
                .uponReceiving("a request to check success test")
                    .method(RequestMethod.POST.name())
                    .path("/provider/success")
                    .headers(headers)
                    .body(gson.toJson(successRequest))
                .willRespondWith()
                    .status(200)
                    .headers(headers)
                    .body(gson.toJson(successResponse))
                .toPact();
    }

    @Test
    @PactVerification
    public void checkSuccessConsumerTest() throws IOException {
        String uri = mockProvider.getUrl() + "/provider/success";
        Integer id = 1;

        SuccessRequest successRequest = SuccessRequest.builder()
                .name("name_test")
                .description("description_test")
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> request = new HttpEntity<>(successRequest, headers);

        ResponseEntity<SuccessResponse> responseEntity = restTemplate.postForEntity(uri, request, SuccessResponse.class);

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertEquals(id, responseEntity.getBody().getId());
        Assert.assertEquals(successRequest.getName(), responseEntity.getBody().getName());
        Assert.assertEquals(successRequest.getDescription(), responseEntity.getBody().getDescription());
    }
}
