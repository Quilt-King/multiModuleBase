package cn.zl.boot.common.config.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnsCallback;
import org.springframework.stereotype.Component;

/**
 * @author zcy
 * @date 2023/2/9
 * @description
 */
@Component
@Slf4j
public class MqCallback implements ConfirmCallback, ReturnsCallback {


    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String msgId = correlationData.getId();
        if (ack) {
            //发送成功
            log.info("ack,消息投递到exchange成功,msgId:" + msgId);
        } else {
            //发送失败，重试
            log.error("ack,消息投递exchange失败，msgId:" + msgId + ",原因" + cause);
        }
    }

    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
        log.error("消息发送失败-消息回退，应答码：" + returnedMessage.getReplyCode() + "，原因：" + returnedMessage.getReplyText() + "，交换机：" + returnedMessage.getExchange() + "，路由键：" + returnedMessage.getRoutingKey());
        String msgId = returnedMessage.getMessage().getMessageProperties().getCorrelationId();
        String data = new String(returnedMessage.getMessage().getBody());
        log.error(msgId + ":消息发送失败:" + data);
    }
}
