package com.izhengyin.test.mybatis.springboot.annotation;

import com.izhengyin.test.mybatis.springboot.annotation.beans.RepeatBean;
import com.izhengyin.test.mybatis.springboot.annotation.beans.MyBean;
import com.izhengyin.test.mybatis.springboot.annotation.beans.MyBean1;
import com.izhengyin.test.mybatis.springboot.annotation.beans.MyBean2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Created by zhengyin on 2017/8/26 下午1:56.
 * Email  <zhengyin.name@gmail.com> .
 */
@Configuration
public class BeanConfiguation {

    @Bean
    public MyBean myBean1(){
        return new MyBean1();
    }
    @Bean
    public MyBean myBean2(){
        return new MyBean2();
    }

    // @Primary  //@Primary 注解的作用，在一个容器中有两个相同的实例时，Spring不知道如何选，所以会抛出异常 #1 . 当使用 @Primary 时相当于告诉Spring选择标注的这一个，这样异常就会消失。
    // 但是当你注入时，无论使用何种Bean名都会注入由@Primary标注的这个实例。  #2
    /**
     * #1  Error Description:
            Parameter 0 of constructor in com.izhengyin.test.mybatis.springboot.annotation.Application$TestRepeatBean required a single bean, but 2 were found:
            - myRepeatBean1: defined by method 'myRepeatBean1' in class path resource [com/izhengyin/test/mybatis/springboot/annotation/BeanConfiguation.class]
            - myRepeatBean2: defined by method 'myRepeatBean2' in class path resource [com/izhengyin/test/mybatis/springboot/annotation/BeanConfiguation.class]
     */

    /**
     * #2
     *      repeatBean1 Hello: My name is myDataSourceBean1
            repeatBean2 Hello: My name is myDataSourceBean1
     */
    @Primary
    @Qualifier("myRepeatBean1")
    @Bean
    public RepeatBean myRepeatBean1(){
        RepeatBean dataSourceBean = new RepeatBean();
        dataSourceBean.setName("myRepeatBean1");
        return dataSourceBean;
    }

    @Qualifier("myRepeatBean2")
    @Bean
    public RepeatBean myRepeatBean2(){
        RepeatBean dataSourceBean = new RepeatBean();
        dataSourceBean.setName("myRepeatBean2");
        return dataSourceBean;
    }

}
