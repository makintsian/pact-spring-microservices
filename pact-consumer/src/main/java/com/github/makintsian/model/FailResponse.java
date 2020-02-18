package com.github.makintsian.model;

import lombok.*;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FailResponse {

    @NonNull
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private String empty;
}
