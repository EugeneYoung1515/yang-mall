package com.ywcjxf.mall.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import static com.ywcjxf.mall.service.constant.DefaultCharset.UTF_8;

@Configuration
public class FreemarkerConfig {

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer(){
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setDefaultEncoding(UTF_8);
        freeMarkerConfigurer.setTemplateLoaderPath("classpath:freemarker/");
        return freeMarkerConfigurer;
    }
}
