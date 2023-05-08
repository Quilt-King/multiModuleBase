package cn.zl.boot.common.config.rabbit.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author zcy
 * @version 1.0.0
 * @date 2023/5/6 14:21
 * @description:
 */
@Component
@EnableRabbit
@Slf4j
public class MqConsumer {

    @RabbitListener(queues = {"TestQueue"})
    public void processMyQueue(String message) {
        log.info("消费消息Received from TestDirectExchange : {} ", new String(message.getBytes()));
    }
}
