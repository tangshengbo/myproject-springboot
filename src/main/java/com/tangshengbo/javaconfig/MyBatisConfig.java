package com.tangshengbo.javaconfig;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

import static com.tangshengbo.util.ProjectConstant.MAPPER_INTERFACE_REFERENCE;
import static com.tangshengbo.util.ProjectConstant.MAPPER_PACKAGE;

/**
 * Created by Tang on 2017/6/27.
 */
@Configuration
@AutoConfigureAfter(MyBatisConfig.class)
public class MyBatisConfig  {

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
        mapperScannerConfigurer.setBasePackage(MAPPER_PACKAGE);

        //配置通用Mapper，详情请查阅官方文档
        // 这里要特别注意，不要把MyMapper放到 basePackage 中，也就是不能同其他Mapper一样被扫描到。
        Properties properties = new Properties();
        properties.setProperty("mappers", MAPPER_INTERFACE_REFERENCE);
        properties.setProperty("notEmpty", "false");//insert、update是否判断字符串类型!='' 即 test="str != null"表达式内是否追加 and str != ''
        properties.setProperty("IDENTITY", "MYSQL");
        mapperScannerConfigurer.setProperties(properties);

        return mapperScannerConfigurer;
    }

}