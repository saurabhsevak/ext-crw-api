//package com.bluevine.server.configuration;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.apache.logging.log4j.Level;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//public class ServiceHandlerInterceptor implements HandlerInterceptor {
//
//    private static final Logger logger = LogManager.getLogger(ServiceHandlerInterceptor.class.getName());
//
////    @Override
//    public boolean preHandle(HttpServletRequest hsreq, HttpServletResponse hsres, Object handler) throws Exception {
//        StringBuffer requrl = hsreq.getRequestURL();
//        logger.printf(Level.DEBUG, "Entry in preHandle with Requested url  :%s ", requrl);
//        return true;
//    }
//
////    @Override
//    public void postHandle(HttpServletRequest hsreq, HttpServletResponse hsres, Object handler, ModelAndView mav) throws Exception {
//        logger.printf(Level.DEBUG, "Entry  in preHandle");
//        hsres.setHeader("Access-Control-Allow-Origin", "*");
//        hsres.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
//        hsres.setHeader("Access-Control-Max-Age", "3600");
//        hsres.setHeader("Access-Control-Allow-Headers", "*");
//        logger.printf(Level.DEBUG, "Exit postHandle method");
////        hsres.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type");
//    }
//
////    @Override
//    public void afterCompletion(HttpServletRequest hsr, HttpServletResponse hsr1, Object handler, Exception excptn) throws Exception {
//        logger.printf(Level.DEBUG, "Entry  in afterCompletion");
//
//    }
//}
