package com.sandbox.server.model.param;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sandbox.server.model.entity.Account;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class AccountParam {
    private Long uid;
    private String id;
    private String pw;
    private String name;
    private Long cash;

    public Account toEntity(){
        return Account.builder()
                .uid(uid)
                .id(id)
                .pw(pw)
                .name(name)
                .cash(cash)
                .build();
    }
}
