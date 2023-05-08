package cn.zl.boot.zlapi.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import java.sql.Types;
import java.util.Collections;

/**
 * @author zcy
 * @version 1.0.0
 * @date 2023/5/8 10:24
 * @description:
 */
public class CodeGenerator {

    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3306/batiesPlus?characterEncoding=utf-8&useUnicode=true&useSSL=false";
        String username = "root";
        String password = "123456";
        FastAutoGenerator.create(url, username,password)
                .globalConfig(builder -> {
                    builder.author("zcy") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D://plus"); // 指定输出目录
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))
                .packageConfig(builder -> {
                    builder.parent("cn.zl.boot.zlapi.generator.CodeGenerator") // 设置父包名
                            .moduleName("user") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D://mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("user") // 设置需要生成的表名
                            .addTablePrefix(); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
