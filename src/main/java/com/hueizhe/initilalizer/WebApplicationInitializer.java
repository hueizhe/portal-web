package com.hueizhe.initilalizer;



import ch.qos.logback.ext.spring.web.LogbackConfigListener;
import com.hueizhe.config.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;



public class WebApplicationInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {
    private final static Logger logger = LoggerFactory.getLogger(WebApplicationInitializer.class);

        @Override
        protected Class<?>[] getRootConfigClasses() {
            return new Class<?>[]{
      //              CachingConfig.class,
                    WebSecurityConfig.class,
                    RootConfig.class,
                    HttpSessionConfig.class,

            };
        }

        @Override
        protected Class<?>[] getServletConfigClasses() {
            return new Class<?>[]{WebConfig.class};
        }

        @Override
        protected String[] getServletMappings() {
            return new String[]{"/"};
        }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);

        servletContext.setInitParameter("logbackConfigLocation", "classpath:logback.xml");
        servletContext.addListener(LogbackConfigListener.class);
    }


}
