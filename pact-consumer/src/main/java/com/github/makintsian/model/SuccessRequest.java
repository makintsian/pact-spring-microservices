package com.github.makintsian.model;

import lombok.*;

@ToString
@Getter
@Builder
public class SuccessRequest {

    @NonNull
    private String name;
    @NonNull
    private String description;
}
