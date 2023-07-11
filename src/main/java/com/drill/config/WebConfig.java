package com.drill.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		System.out.println("Interceptor OK!!!");
		registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/write");
	}
}
