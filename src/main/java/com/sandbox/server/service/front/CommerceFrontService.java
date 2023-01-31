package com.sandbox.server.service.front;

import com.sandbox.server.exception.SandBoxServerForbiddenException;
import com.sandbox.server.model.entity.Account;
import com.sandbox.server.model.entity.Commerce;
import com.sandbox.server.model.entity.Product;
import com.sandbox.server.model.param.CommerceParam;
import com.sandbox.server.model.result.AccountFront;
import com.sandbox.server.model.result.CommerceFront;
import com.sandbox.server.model.result.RestResult;
import com.sandbox.server.model.result.RestResultBuilder;
import com.sandbox.server.service.persist.AccountService;
import com.sandbox.server.service.persist.CommerceService;
import com.sandbox.server.service.persist.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommerceFrontService {

    private final AccountService accountService;
    private final ProductService productService;
    private final CommerceService commerceService;

    private final RestResultBuilder<Class> accountRestResultBuilder = new RestResultBuilder<>(AccountFront.class,"account");
    private final RestResultBuilder<Class> commerceRestResultBuilder = new RestResultBuilder<>(CommerceFront.class,"commerce");
    public RestResult buy(CommerceParam commerceParam) {
        Product product = productService.getProduct(commerceParam.getSeq());
        Account account = accountService.getUid(commerceParam.getUid());
        if (account.getCash()>product.getPrice()) {
            account = accountService.withdrawal(Account.builder()
                    .uid(commerceParam.getUid())
                    .cash(product.getPrice())
                    .build());
            Commerce commerce = commerceService.buy(Commerce.builder()
                            .seq(product.getSeq())
                            .uid(account.getUid())
                            .build());
            Map<String, Object> data = new HashMap<>();
            data.putAll(accountRestResultBuilder.resultBuilder(account));
            data.putAll(commerceRestResultBuilder.resultBuilder(commerce));
            return new RestResult(data);
        }
        else{
            throw new SandBoxServerForbiddenException("Cash is not enough");
        }
    }
}
