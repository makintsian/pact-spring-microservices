package com.github.makintsian.model;

import lombok.*;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SuccessResponse {

    @NonNull
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private String description;
}
