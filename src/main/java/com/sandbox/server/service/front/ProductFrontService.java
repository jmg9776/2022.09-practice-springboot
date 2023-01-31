package com.sandbox.server.service.front;

import com.sandbox.server.exception.SandBoxServerForbiddenException;
import com.sandbox.server.model.entity.Product;
import com.sandbox.server.model.param.ProductParam;
import com.sandbox.server.model.result.ProductFront;
import com.sandbox.server.model.result.RestResult;
import com.sandbox.server.model.result.RestResultBuilder;
import com.sandbox.server.service.persist.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductFrontService {

    private final ProductService productService;
    private final RestResultBuilder<Class> restResultBuilder = new RestResultBuilder<>(ProductFront.class, "product");

    public RestResult getProduct(Long seq) {
        final Product product = productService.getProduct(seq);
        return new RestResult(restResultBuilder.resultBuilder(product));
    }

    public RestResult getAllProducts(Pageable pageable) {
        final Page<Product> productList = productService.getAllProducts(pageable);
        if (productList.isEmpty()) {
            throw new SandBoxServerForbiddenException("Arguments is not Exist");
        }
        return new RestResult(restResultBuilder.listResultBuilder(Collections.singletonList(productList.getContent())));
    }

    public RestResult deleteProduct(Long seq) {
        productService.deleteProducts(seq);
        Map<String, Object> data = new HashMap<>();
        data.put("result", "ok");
        return new RestResult(data);
    }

    public RestResult saveProduct(ProductParam productParam) {
        final Product product = productService.saveProduct(productParam.toEntity());
        return new RestResult(restResultBuilder.resultBuilder(product));
    }

    public RestResult updateProducts(ProductParam productParam) {
        final Product product = productService.updateProducts(productParam.toEntity());
        return new RestResult(restResultBuilder.resultBuilder(product));
    }
}
