package cn.zl.boot.zlserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zcy
 */
@SpringBootApplication(scanBasePackages = {"cn.zl.boot"})
@MapperScan("cn.zl.boot")
public class ZlServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZlServerApplication.class, args);
    }

}
