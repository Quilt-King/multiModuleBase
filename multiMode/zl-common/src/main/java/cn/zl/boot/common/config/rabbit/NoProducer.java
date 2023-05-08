package cn.zl.boot.common.config.rabbit;

/**
 * @author zcy
 * @date 2023/2/10
 * @description
 */
class NoProducer extends Producer {
    NoProducer() {
    }

    @Override
    void send(String queueName, String msg) {
        super.send(queueName, msg);
    }

    @Override
    void send(String exchange, String routingKey, String msg) {
        super.send(exchange, routingKey, msg);
    }
}
