package com.sandbox.server.model.param;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sandbox.server.model.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class ProductParam {
    private Long seq;
    private String name;
    private String description;
    private Long price;

    public Product toEntity(){
        return Product.builder()
                .seq(seq)
                .name(name)
                .description(description)
                .price(price)
                .build();
    }
}
