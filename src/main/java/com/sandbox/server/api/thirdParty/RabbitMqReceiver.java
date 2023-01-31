package com.sandbox.server.api.thirdParty;

import com.sandbox.server.config.redis.RedisUtils;
import com.sandbox.server.model.param.AccountParam;
import com.sandbox.server.service.front.AccountFrontService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("rabbitReceive")
@AllArgsConstructor
@Component
public class RabbitMqReceiver {
    private final TelegramAPI telegramAPI;
    private final AccountFrontService accountFrontService;
    private final RedisUtils redisUtils;

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(name="telegram", type = ExchangeTypes.TOPIC),
            value = @Queue(name="telegram-receive"),
            key = "telegram-send")
    )
    public void telegram(String message){
        //telegramAPI.sendMessage("5755521520:AAEvejo2Kv6YTwz1ARLND03UyTUxJqOrv34",message,"-1001716691333");
    }

    // todo Redis를 사용한 이유가 캐시인가요? 그러면 @Cacheable 을 활용한 구현이 나을것같습니다. 확인 부탁드릴께요 :)
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(name="withdrawal", type = ExchangeTypes.TOPIC),
            value = @Queue(name="withdrawal-receive"),
            key = "withdrawal-send")
    )
    public String withdrawal(String message) {
        final ObjectConverter objectConverter = new ObjectConverter();
        AccountParam accountParam = (AccountParam) objectConverter.jsonToObject(message, AccountParam.class);
        String result = objectConverter.objectToJson(accountFrontService.withdrawal(accountParam));
        return result;
    }
}
