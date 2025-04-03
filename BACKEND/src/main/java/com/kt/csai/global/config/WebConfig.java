package com.kt.csai.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kt.csai.global.common.component.CsaiInterceptor;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
	private final CsaiInterceptor csaiInterceptor;
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("https://csai.kt.co.kr:8080", "https://dev.csai.kt.co.kr:8080", "http://localhost:8080", "http://localhost:8081")
		.allowedMethods("GET", "POST")
		.allowCredentials(true)
		.maxAge(3000);
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(csaiInterceptor)
		.addPathPatterns("/*");
	}

}
