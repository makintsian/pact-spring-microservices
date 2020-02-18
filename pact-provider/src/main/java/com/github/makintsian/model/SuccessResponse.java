package com.github.makintsian.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@ToString
@Getter
@Builder
public class SuccessResponse {

    @NonNull
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private String description;
}
