package com.github.makintsian;

import au.com.dius.pact.provider.junit.Consumer;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.SpringRestPactRunner;
import au.com.dius.pact.provider.spring.target.SpringBootHttpTarget;
import com.github.makintsian.model.SuccessRequest;
import com.github.makintsian.model.SuccessResponse;
import com.github.makintsian.services.ProviderService;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@RunWith(SpringRestPactRunner.class)
@SpringBootTest(classes = Application.class, properties = {"spring.profiles.active=test", "spring.cloud.config.enabled=false"}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@PactBroker(host = "${pact.host}", port = "${pact.port}")
@Provider("success_provider")
@Consumer("success_consumer")
public class ProviderSuccessTest {

    @MockBean
    private ProviderService service;

    @TestTarget
    public final Target target = new SpringBootHttpTarget();

    @State(value = "create success test")
    public void saveSuccessState() throws Exception {
        SuccessResponse successResponse = SuccessResponse.builder()
                .id(1)
                .name("name_test")
                .description("description_test")
                .build();
        Mockito.when(service.saveSuccess(ArgumentMatchers.any(SuccessRequest.class))).thenReturn(successResponse);
    }
}
