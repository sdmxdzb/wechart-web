package com.lanwon.aop.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebAppConfigurer extends WebMvcConfigurerAdapter {

	@Bean
	public AuthoredInterceptor  getAuthoredInterceptor(){
		return new AuthoredInterceptor();
	}
	
		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(getAuthoredInterceptor()).addPathPatterns("/**");
			//super.addInterceptors(registry);
		}
}
