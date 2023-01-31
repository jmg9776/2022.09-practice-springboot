package com.sandbox.server.service.persist;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sandbox.server.api.thirdParty.IpAPI;
import com.sandbox.server.config.redis.RedisKeys;
import com.sandbox.server.model.entity.Ip;
import com.sandbox.server.repository.IpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IpService {
    final IpRepository ipRepository;
    final IpAPI ipAPI;

    @Cacheable(value = RedisKeys.TEST, key="#clientIp", unless = "#clientIp ==''")
    public String UpdateIp(String clientIp) {
        Optional<Ip> findResult = ipRepository.findByIp(clientIp);
        Ip res = null;
        if (findResult.isEmpty()) {
            Object requestResult = ipAPI.sendMessage(clientIp);
            ObjectMapper objectMapper = new ObjectMapper();
            String ipData = "";
            try {
                ipData = objectMapper.writeValueAsString(requestResult);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            res = ipRepository.save(Ip.builder().ip(clientIp)
                            .data(ipData)
                            .build());
        } else res = findResult.get();
        return res.getData();
    }
}
