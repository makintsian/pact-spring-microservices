package com.github.makintsian;

import au.com.dius.pact.provider.junit.Consumer;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.SpringRestPactRunner;
import au.com.dius.pact.provider.spring.target.SpringBootHttpTarget;
import com.github.makintsian.model.FailRequest;
import com.github.makintsian.model.FailResponse;
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
@Provider("fail_provider")
@Consumer("fail_consumer")
public class ProviderFailTest {

    @MockBean
    private ProviderService service;

    @TestTarget
    public final Target target = new SpringBootHttpTarget();

    @State(value = "create fail test")
    public void saveFailState() throws Exception {
        FailResponse failResponse = FailResponse.builder()
                .id(2)
                .name("name_test")
                .descriptionFail("descriptionFail_test")
                .build();
        Mockito.when(service.saveFail(ArgumentMatchers.any(FailRequest.class))).thenReturn(failResponse);
    }
}
