package com.sandbox.server.service.persist;

import com.sandbox.server.exception.SandBoxServerForbiddenException;
import com.sandbox.server.model.entity.Product;
import com.sandbox.server.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Page<Product> getAllProducts(Pageable pageable) { return productRepository.findAll(pageable); }
    public Product getProduct(Long seq) {
        Optional<Product> productOptional = productRepository.findById(seq);
        if (productOptional.isEmpty())
            throw new SandBoxServerForbiddenException(seq + "is not exist");
        return productOptional.get();
    }

    public Product saveProduct(Product product) { return productRepository.save(product); }

    public void deleteProducts(Long seq) { productRepository.deleteById(seq); }

    public Product updateProducts(Product product) {
        Optional<Product> productOptional = productRepository.findById(product.getSeq());
        if(productOptional.isEmpty()) {
            throw new SandBoxServerForbiddenException(product.getSeq()+" is not exist");
        }
        return productRepository.save(product);
    }
}
