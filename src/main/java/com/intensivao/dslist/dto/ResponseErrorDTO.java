package com.intensivao.dslist.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ResponseErrorDTO {
    private Map<String, String> response = new HashMap<>();

    public ResponseErrorDTO(String message) {
        response.put("message", message);
    }
}
