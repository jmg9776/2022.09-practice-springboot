package com.sandbox.server.api.thirdParty;

import com.sandbox.server.model.param.AccountParam;
import com.sandbox.server.model.result.RestResult;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class RabbitMq {
    final RabbitTemplate rabbitTemplate;
    final ObjectConverter objectConverter = new ObjectConverter();

    public void telegramSender(String message){
        rabbitTemplate.convertAndSend("telegram", "telegram-send", message);
    }
    public RestResult withdrawal(AccountParam accountParam){
        String response = (String) rabbitTemplate.convertSendAndReceive("withdrawal", "withdrawal-send", objectConverter.objectToJson(accountParam));
        RestResult result = (RestResult) objectConverter.jsonToObject(response, RestResult.class);
        return result;
    }
}
