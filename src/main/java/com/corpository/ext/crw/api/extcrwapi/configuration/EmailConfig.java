package com.corpository.ext.crw.api.extcrwapi.configuration;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@ComponentScan(basePackages = "com.bluevine.server")
@PropertySource("classpath:emailconfig.properties")
public class EmailConfig {
	
	@Autowired
	private Environment env;
    //Put Other Application configuration here.
     
    @Bean
    public JavaMailSender getMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        //Using gmail     
        mailSender.setHost(env.getProperty("email.config.host"));
        mailSender.setPort(Integer.parseInt(env.getProperty("email.config.port")));
        mailSender.setUsername(env.getProperty("email.config.username"));
        mailSender.setPassword(env.getProperty("email.config.password"));
         
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.starttls.enable",env.getProperty("mail.smtp.starttls.enable.val"));
        javaMailProperties.put("mail.smtp.auth",env.getProperty("mail.smtp.auth.val"));
        javaMailProperties.put("mail.transport.protocol",env.getProperty("mail.transport.protocol.val"));
        javaMailProperties.put("mail.debug",env.getProperty("mail.debug.val"));
         
         
        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
    }
}
