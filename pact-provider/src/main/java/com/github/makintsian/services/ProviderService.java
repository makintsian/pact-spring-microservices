package com.github.makintsian.services;

import com.github.makintsian.model.FailRequest;
import com.github.makintsian.model.FailResponse;
import com.github.makintsian.model.SuccessRequest;
import com.github.makintsian.model.SuccessResponse;
import org.springframework.stereotype.Service;

@Service
public class ProviderService {

    public SuccessResponse saveSuccess(SuccessRequest request) {
        return SuccessResponse.builder()
                .id(1)
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public FailResponse saveFail(FailRequest request) {
        return FailResponse.builder()
                .id(2)
                .name(request.getName())
                .descriptionFail(request.getDescription())
                .build();
    }
}
