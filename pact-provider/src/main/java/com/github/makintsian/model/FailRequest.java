package com.github.makintsian.model;

import lombok.*;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FailRequest {

    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private String empty;
}
