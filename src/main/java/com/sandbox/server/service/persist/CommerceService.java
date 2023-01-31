package com.sandbox.server.service.persist;

import com.sandbox.server.model.entity.Commerce;
import com.sandbox.server.repository.CommerceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommerceService {
    private final CommerceRepository commerceRepository;
    public Commerce buy(Commerce commerce) {
        return commerceRepository.save(commerce);
    }
}
