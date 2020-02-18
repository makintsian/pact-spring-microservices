package com.github.makintsian.model;

import lombok.*;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SuccessRequest {

    @NonNull
    private String name;
    @NonNull
    private String description;
}
