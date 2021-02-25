package com.izhengyin.test.spring.hook;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhengyin zhengyinit@outlook.com
 * Created on 2021-02-23 14:42
 */
public class LifeCycleBean implements BeanNameAware, BeanFactoryAware, BeanClassLoaderAware, BeanPostProcessor,
        InitializingBean, DisposableBean {
    private static final AtomicInteger order = new AtomicInteger(1);
    private String test;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        System.out.println("#"+order.getAndIncrement()+" 属性注入....");
        this.test = test;
    }

    public LifeCycleBean(){ // 构造方法
        System.out.println("#"+order.getAndIncrement()+" 构造函数调用...");
    }

    public void display(){
        System.out.println("#"+order.getAndIncrement()+" display 方法调用...");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("#"+order.getAndIncrement()+" BeanFactoryAware 被调用...");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("#"+order.getAndIncrement()+" BeanNameAware 被调用...");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("#"+order.getAndIncrement()+" BeanClassLoaderAware 被调用...");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("#"+order.getAndIncrement()+" BeanPostProcessor postProcessBeforeInitialization 被调用...");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("#"+order.getAndIncrement()+" BeanPostProcessor postProcessAfterInitialization 被调用...");
        return bean;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("#"+order.getAndIncrement()+" DisposableBean destroy 被调动...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("#"+order.getAndIncrement()+" InitializingBean afterPropertiesSet 被调动...");
    }

    public void initMethod(){
        System.out.println("#"+order.getAndIncrement()+" init-method 被调用...");
    }

    public void destroyMethdo(){
        System.out.println("#"+order.getAndIncrement()+" destroy-method 被调用...");
    }

    public static void main(String[] args) {
        ClassPathResource resource = new ClassPathResource("lifeBean.xml");
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(resource);
        factory.addBeanPostProcessor(new LifeCycleBean());
        //注册到 BeanFactory
        LifeCycleBean lifeCycleBean = (LifeCycleBean) factory.getBean("lifeCycle");
        lifeCycleBean.display();
    }

}