package cn.zl.boot.common.config.rabbit;

import org.springframework.stereotype.Component;

/**
 * @author zcy
 * @date 2023/2/9
 * @description
 */
@Component
public class ProducerUtil {

    static Producer producer = new NoProducer();

    private ProducerUtil() {
    }

    public void send(String queueName, String msg) {
        producer.send(queueName, msg);
    }
}
