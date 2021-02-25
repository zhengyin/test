package com.izhengyin.test.spring;

import com.izhengyin.test.spring.service.UserService;
import com.izhengyin.test.spring.service.UserServiceImpl;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.*;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2021-02-19 11:17
 */
public class Application {
    public static void main(String[] args) throws Exception{
        ClassPathResource resource = new ClassPathResource("beans.xml");
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(resource);

        UserService userServiceImpl = (UserService) factory.getBean("userServiceImpl");
        System.out.println(userServiceImpl.getName());

        UserService userServiceProxy = (UserService)  factory.getBean("fbUserService");
        System.out.println(userServiceProxy.getName());

        MyFactoryBean myFactoryBean = (MyFactoryBean) factory.getBean("&fbUserService");
        System.out.println(myFactoryBean.toString());


    //    ApplicationContext context = new ClassPathXmlApplicationContext("services.xml", "daos.xml");
    }
}
