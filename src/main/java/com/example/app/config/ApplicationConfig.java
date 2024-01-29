package com.example.app.config;

import java.util.ResourceBundle;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.app.filter.AuthFilter;

@Configuration
public class ApplicationConfig implements WebMvcConfigurer {
	
	@Override
	public Validator getValidator() {
	var validator = new LocalValidatorFactoryBean();
	validator.setValidationMessageSource(messageSource());
	return validator;
	}
	
	@Bean
	ResourceBundleMessageSource messageSource() {
	var messageSource = new ResourceBundleMessageSource();
	messageSource.setBasename("validation");
	return messageSource;
	}
	
	// 認証用フィルタの有効化
	@Bean
	FilterRegistrationBean<AuthFilter> authFilter() {
	var bean =
	new FilterRegistrationBean<AuthFilter>(new AuthFilter());
	bean.addUrlPatterns("/admintop/*");
	bean.addUrlPatterns("/usertop/*");
	return bean;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		ResourceBundle bundle = ResourceBundle.getBundle("application");
	registry.addResourceHandler("/uploads/**")
	.addResourceLocations(bundle.getString("upload.path"));
	}

}
