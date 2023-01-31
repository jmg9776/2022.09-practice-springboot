package com.sandbox.server.model.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommerceFront {
    Long order_num;
    Long uid;
    Long seq;
    java.sql.Timestamp buyAt;
}
