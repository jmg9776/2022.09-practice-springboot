package com.sandbox.server.service.persist;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sandbox.server.model.entity.FilterLog;
import com.sandbox.server.repository.FilterLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FilterLogService {
    final FilterLogRepository filterLogRepository;
    public void logSave(Object header, String request, String response) {
        ObjectMapper objectMapper = new ObjectMapper();
        String headerData = "";
        try {
            headerData = objectMapper.writeValueAsString(header);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        request = request.replaceAll(" ", "");
        request = request.replaceAll("\n", "");
        filterLogRepository.save(FilterLog.builder()
                        .header(headerData)
                        .request(request)
                        .response(response)
                        .build());
    }
}
