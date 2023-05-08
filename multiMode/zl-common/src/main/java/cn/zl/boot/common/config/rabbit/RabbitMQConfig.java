package cn.zl.boot.common.config.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zcy
 * @date 2023/2/9
 * @description
 */
@Configuration
public class RabbitMQConfig {
    @Bean(name = "TestQueue")
    public Queue testDirectExchange(){
        return new Queue("TestQueue",true);
    }

    @Bean
    public DirectExchange testExchange(){
        return new DirectExchange("testExchange",true, false);
    }

    @Bean
    public Binding deviceTreatedBinding(){
        return BindingBuilder.bind(testDirectExchange()).to(testExchange()).with("testRoutingKey");
    }
    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory cachingConnectionFactory) {
        return new RabbitTemplate(cachingConnectionFactory);
    }
}
