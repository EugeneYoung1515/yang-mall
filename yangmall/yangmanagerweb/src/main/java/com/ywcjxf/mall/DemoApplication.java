package com.ywcjxf.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.DelegatingFilterProxy;

@SpringBootApplication
@MapperScan({"com.ywcjxf.mall.dao"})
public class DemoApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DemoApplication.class);
	}

	@Bean
	public FilterRegistrationBean delegatingFilterProxy(){
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		DelegatingFilterProxy proxy = new DelegatingFilterProxy();
		proxy.setTargetFilterLifecycle(true);
		proxy.setTargetBeanName("shiroFilter");
		filterRegistrationBean.setFilter(proxy);
		return filterRegistrationBean;
	}

}
