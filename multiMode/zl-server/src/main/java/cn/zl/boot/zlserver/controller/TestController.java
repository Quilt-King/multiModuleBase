package cn.zl.boot.zlserver.controller;

import cn.zl.boot.common.config.rabbit.ProducerUtil;
import cn.zl.boot.common.utils.redis.RedisTemplateUtil;
import cn.zl.boot.zlapi.api.user.dto.User;
import cn.zl.boot.zlapi.mapper.user.UserMapper;
import jakarta.annotation.Resource;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zcy
 * @version 1.0.0
 * @date 2023/5/5 20:58
 * @description:
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Resource
    ProducerUtil producerUtil;
    @Resource
    RedisTemplateUtil redisTemplateUtil;
    @Resource
    UserMapper userMapper;

    @GetMapping("/get")
    public String test(@RequestParam("name") String name) {
        log.info("测试请求{}", name);
        producerUtil.send("TestQueue", name);
        Object o = redisTemplateUtil.get(name);
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
        return o != null ? o.toString() : null;
    }
}
