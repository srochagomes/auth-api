package com.api.authbase.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Authbase {

    private Long id;

    private String token;
}