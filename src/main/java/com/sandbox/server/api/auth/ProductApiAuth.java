package com.sandbox.server.api.auth;

import com.sandbox.server.config.redis.RedisKeys;
import com.sandbox.server.model.param.ProductParam;
import com.sandbox.server.model.result.RestResult;
import com.sandbox.server.service.front.ProductFrontService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@EnableSpringDataWebSupport
public class ProductApiAuth implements WebMvcConfigurer {
    private final ProductFrontService productFrontService;

    @GetMapping("/products")
    public RestResult product(@PageableDefault(page=0, size=3, sort="seq", direction = Sort.Direction.DESC)Pageable pageable){
        return productFrontService.getAllProducts(pageable);
    }

    @Cacheable(value = RedisKeys.TEST, key="#seq", unless = "#seq ==''")
    @GetMapping("/product/{seq}")
    public RestResult product(@PathVariable Long seq){
        return productFrontService.getProduct(seq);
    }

    @PostMapping("/product")
    public RestResult saveProduct(@RequestBody ProductParam productParam){ return productFrontService.saveProduct(productParam); }

    @DeleteMapping("/product")
    public RestResult deleteProduct(@RequestParam Long seq){ return productFrontService.deleteProduct(seq); }

    @PostMapping("/updateProduct")
    public RestResult updateProduct(@RequestBody ProductParam productParam){ return productFrontService.updateProducts(productParam); }
}
