package com.bluevine.server.configuration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class SetContext extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(SetContext.class.getName());

    public SetContext() {
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            logger.printf(Level.DEBUG, "Entry  in init method");
            super.init(config);
            ServletContext context = this.getServletContext();
//            ServerContext.initialized(context);
            logger.printf(Level.DEBUG, "Exit init method");
        } catch (Exception e) {
            logger.printf(Level.ERROR,"Exception in init method :%s" + e.toString());
        }
    }
}
