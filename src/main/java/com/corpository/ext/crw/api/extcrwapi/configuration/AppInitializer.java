package com.bluevine.server.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private static final Logger logger = LogManager.getLogger(AppInitializer.class.getName());

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        logger.printf(Level.DEBUG, "Entry in onStartup method");
        super.onStartup(servletContext);
        ServletRegistration.Dynamic context = servletContext.addServlet("context", SetContext.class);
        context.setLoadOnStartup(1);
        logger.printf(Level.DEBUG, "Exit onStartup method");
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{ServletConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

//    @Override
//    protected Filter[] getServletFilters() {
//        Filter[] singleton = {new CORSFilter()};
//        return singleton;
//    }
}
