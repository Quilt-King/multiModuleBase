package cn.zl.boot.common.config.rabbit;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import java.util.concurrent.atomic.AtomicLong;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zcy
 * @date 2023/2/9
 * @description mq生产者
 */
@Component
@Slf4j
public class Producer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    MqCallback mqCallback;
    private final AtomicLong atomicLong = new AtomicLong(0L);

    public Producer() {
    }

    @PostConstruct
    public void init() {
        log.info("加载MQ成功");
        ProducerUtil.producer = this;
        //ack回调
        this.rabbitTemplate.setConfirmCallback(this.mqCallback);
        //回退回调
        this.rabbitTemplate.setReturnsCallback(this.mqCallback);
    }

    void send(String queueName, String msg) {
        String id = this.atomicLong.incrementAndGet() + "";
        log.info(id + ":发送mq队列：" + queueName + ",消息：" + msg);
        this.rabbitTemplate.convertAndSend(queueName, (Object) msg, new CorrelationData(id + ":" + msg));
    }

    //topic模式发送
    void send(String exchange, String routingKey, String msg) {
        String id = this.atomicLong.incrementAndGet() + "";
        log.info(id + ":topic模式发送MQ " + ",交换机：" + exchange + ",routingKey: " + routingKey + ",消息：" + msg);
        this.rabbitTemplate.convertAndSend(exchange, routingKey, (Object) msg, new CorrelationData(id + ":" + msg));
    }

//    //广播模式发送MQ    topic模式中routingKey为空字符串 "" 是广播模式的实现
//    void send(String exchange, String msg) {
//        String id = this.atomicLong.incrementAndGet() + "";
//        logger.info(id + ":广播模式发送MQ " + ",交换机：" + exchange + ",消息：" + msg);
//        this.rabbitTemplate.convertAndSend(exchange,"", (Object) msg, new CorrelationData(id + ":" + msg));
//    }

}
