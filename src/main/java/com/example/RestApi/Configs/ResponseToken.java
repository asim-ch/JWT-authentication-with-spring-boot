package com.example.RestApi.Configs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseToken {

    private String token;
    private String tokenType;
}
