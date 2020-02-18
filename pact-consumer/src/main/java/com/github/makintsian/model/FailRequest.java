package com.github.makintsian.model;

import lombok.*;

@ToString
@Getter
@Builder
public class FailRequest {

    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private String empty;
}
