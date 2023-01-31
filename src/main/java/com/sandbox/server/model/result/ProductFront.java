package com.sandbox.server.model.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductFront {

    private Long seq;
    private String name;
    private String description;
    private Long price;

}
