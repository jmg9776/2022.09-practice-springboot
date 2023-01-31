package com.sandbox.server.model.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestError {

    private String code;
    private String errorCode;
    private String message;
    private String detailed;
    private String link;
    private String title;
    private String redirect;

    public RestError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public RestError(String code, String errorCode, String message) {
        this.code = code;
        this.errorCode = errorCode;
        this.message = message;
    }

}