package com.sandbox.server.repository;

import com.sandbox.server.model.entity.Product;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface TestRepository extends DataTablesRepository<Product, Long> {
}
