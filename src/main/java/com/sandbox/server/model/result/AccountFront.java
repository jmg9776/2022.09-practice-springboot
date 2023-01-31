package com.sandbox.server.model.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountFront {
    private Long uid;
    private String id;
    private String pw;
    private String name;
    private Long cash;
}
