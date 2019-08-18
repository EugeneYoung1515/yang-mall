package com.ywcjxf.mall;

import com.ywcjxf.mall.service.util.IdUtil;
import com.ywcjxf.mall.web.filter.LoginFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
@MapperScan({"com.ywcjxf.mall.dao"})
public class DemoApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

	public static void main(String[] args) {
		IdUtil.setShardId(1);
		SpringApplication.run(DemoApplication.class, args);
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DemoApplication.class);
	}

	@Bean
	public LoginFilter loginFilter(){
		LoginFilter loginFilter = new LoginFilter();
		String[] noLoginUrls = {"/addcart","/goods_num"};
		loginFilter.setNoLoginUrls(noLoginUrls);
		return loginFilter;
	}

	@Bean
	public FilterRegistrationBean loginFilter2(){
		FilterRegistrationBean loginFilter = new FilterRegistrationBean();
		loginFilter.setFilter(loginFilter());
		loginFilter.addUrlPatterns("/*");
		loginFilter.setOrder(Integer.MAX_VALUE);
		return loginFilter;
	}

}
