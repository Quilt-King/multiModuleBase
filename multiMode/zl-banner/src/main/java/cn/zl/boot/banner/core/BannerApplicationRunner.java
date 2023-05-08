package cn.zl.boot.banner.core;

import cn.hutool.core.thread.ThreadUtil;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.util.ClassUtils;

/**
 * 项目启动成功后，提供文档相关的地址
 *
 * @author zcy
 */
@Slf4j
public class BannerApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ThreadUtil.execute(() -> {
            ThreadUtil.sleep(1, TimeUnit.SECONDS); // 延迟 1 秒，保证输出到结尾
            log.info("\n"
                             + "    ____                                           __             ___             __             \n"
                             + "   /\\  _`\\                                        /\\ \\__         /\\_ \\           /\\ \\__          \n"
                             + "   \\ \\ \\/\\_\\    ___     ___      __   _ __    __  \\ \\ ,_\\  __  __\\//\\ \\      __  \\ \\ ,_\\    __   \n"
                             + "    \\ \\ \\/_/_  / __`\\ /' _ `\\  /'_ `\\/\\`'__\\/'__`\\ \\ \\ \\/ /\\ \\/\\ \\ \\ \\ \\   /'__`\\ \\ \\ \\/  /'__`\\ \n"
                             + "     \\ \\ \\L\\ \\/\\ \\L\\ \\/\\ \\/\\ \\/\\ \\L\\ \\ \\ \\//\\ \\L\\.\\_\\ \\ \\_\\ \\ \\_\\ \\ \\_\\ \\_/\\ \\L\\.\\_\\ \\ \\_/\\  __/ \n"
                             + "      \\ \\____/\\ \\____/\\ \\_\\ \\_\\ \\____ \\ \\_\\\\ \\__/.\\_\\\\ \\__\\\\ \\____/ /\\____\\ \\__/.\\_\\\\ \\__\\ \\____\\\n"
                             + "       \\/___/  \\/___/  \\/_/\\/_/\\/___L\\ \\/_/ \\/__/\\/_/ \\/__/ \\/___/  \\/____/\\/__/\\/_/ \\/__/\\/____/\n"
                             + "                                 /\\____/                                                         \n"
                             + "                                 \\_/__/                                                          \n");
        });
    }

    private static boolean isNotPresent(String className) {
        return !ClassUtils.isPresent(className, ClassUtils.getDefaultClassLoader());
    }

}
