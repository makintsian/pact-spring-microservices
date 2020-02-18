package com.github.makintsian;

import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.PactProviderRule;
import au.com.dius.pact.consumer.junit.PactVerification;
import au.com.dius.pact.core.model.PactSpecVersion;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.github.makintsian.model.FailRequest;
import com.github.makintsian.model.FailResponse;
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

public class ConsumerFailTest {

    private RestTemplate restTemplate = new RestTemplate();
    private Gson gson = new GsonBuilder().create();

    @Rule
    public PactProviderRule mockProvider = new PactProviderRule("fail_provider", PactSpecVersion.V3, this);

    @Pact(provider = "fail_provider", consumer = "fail_consumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        Map<String, String> headers = Map.of("Content-Type", MediaType.APPLICATION_JSON_VALUE);

//        PactDslJsonBody failRequest = new PactDslJsonBody()
//                .stringValue("name", "name_test")
//                .stringType("description", "description_test")
//                .stringType("empty", "empty_test");

        FailRequest failRequest = FailRequest.builder()
                .name("name_test")
                .description("description_test")
                .empty("empty_test")
                .build();

        FailResponse failResponse = new FailResponse(2, "name_test", "description_test", "empty_test");

        return builder.given("create fail test")
                .uponReceiving("a request to check fail test")
                    .method(RequestMethod.POST.name())
                    .path("/provider/fail")
                    .headers(headers)
                    .body(gson.toJson(failRequest))
                .willRespondWith()
                    .status(200)
                    .headers(headers)
                    .body(gson.toJson(failResponse))
                .toPact();
    }

    @Test
    @PactVerification
    public void checkFailConsumerTest() throws IOException {
        String uri = mockProvider.getUrl() + "/provider/fail";
        Integer id = 2;

        FailRequest failRequest = FailRequest.builder()
                .name("name_test")
                .description("description_test")
                .empty("empty_test")
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> request = new HttpEntity<>(failRequest, headers);

        ResponseEntity<FailResponse> responseEntity = restTemplate.postForEntity(uri, request, FailResponse.class);

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertEquals(id, responseEntity.getBody().getId());
        Assert.assertEquals(failRequest.getName(), responseEntity.getBody().getName());
        Assert.assertEquals(failRequest.getDescription(), responseEntity.getBody().getDescription());
        Assert.assertEquals(failRequest.getEmpty(), responseEntity.getBody().getEmpty());
    }
}
