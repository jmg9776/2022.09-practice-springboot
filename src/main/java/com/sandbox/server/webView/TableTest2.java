package com.sandbox.server.webView;

import com.sandbox.server.model.entity.Product;
import com.sandbox.server.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class TableTest2 {
    final TestRepository testRepository;
    @GetMapping("/getData")
    public @ResponseBody DataTablesOutput<Product> getData(@Valid DataTablesInput input) {
        return testRepository.findAll(input);
    }
}
