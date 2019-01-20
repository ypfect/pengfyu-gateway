//package com.pengfyu.zuul.filter;
//
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.servlet.Filter;
//
//@Configuration
//public class ComponentFilterOrderConfig {
//	@Bean
//	public Filter AccessFilter(){
//		return new AccessFilter();//自定义的过滤器
//	}
////	@Bean
////	public Filter MyFilterSecurityInterceptor(){
////		return new MyFilterSecurityInterceptor();//自定义的过滤器
////	}
////	@Bean
////	public Filter PreauthFilter(){
////		return new PreauthFilter();//自定义的过滤器
////	}
//	@Bean
//	public FilterRegistrationBean filterRegistrationBean1(){
//		FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
//		filterRegistrationBean.setFilter(AccessFilter());
//		filterRegistrationBean.addUrlPatterns("/");
//		filterRegistrationBean.setOrder(5);//order的数值越小 则优先级越高
//		return filterRegistrationBean;
//	}
////	@Bean
////	public FilterRegistrationBean filterRegistrationBean2(){
////		FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
////		filterRegistrationBean.setFilter(MyFilterSecurityInterceptor());
////		filterRegistrationBean.addUrlPatterns("/");
////		filterRegistrationBean.setOrder(0);
////		return filterRegistrationBean;
////	}
////	@Bean
////	public FilterRegistrationBean filterRegistrationBean3(){
////		FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
////		filterRegistrationBean.setFilter(PreauthFilter());
////		filterRegistrationBean.addUrlPatterns("/");
////		filterRegistrationBean.setOrder(-1);
////		return filterRegistrationBean;
////	}
//
//}
//
