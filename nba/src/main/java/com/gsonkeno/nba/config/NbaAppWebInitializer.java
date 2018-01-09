package com.gsonkeno.nba.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//AbstractAnnotationConfigDispatcherServletInitializer会同时创建DispatcherServlet和
//ContextLoaderListener。
public class NbaAppWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  //该方法返回的带有@Configuration注解的类将会被用来定义ContextLoaderListener应用上下文中的bean
  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class<?>[] { RootConfig.class };
  }

  //该方法返回的带有@Configuration注解的类将会被用来定义DispatcherServlet应用上下文中的bean
  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class<?>[] { WebConfig.class };
  }

  @Override
  protected String[] getServletMappings() {
    return new String[] { "/" };
  }

}