package com.izhengyin.test.spring.aop.advisor.staticMethod;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import java.lang.reflect.Method;

/**
 * Created by zhengyin on 2017/8/29 上午10:53.
 * Email  <zhengyin.name@gmail.com> .
 */
public class PersonAdvisor extends StaticMethodMatcherPointcutAdvisor{
    @Override
    public boolean matches(Method method, Class<?> aClass) {
        return "sayFromWhichCountry".equals(method.getName());
    }

    @Override
    public ClassFilter getClassFilter() {
        return new ClassFilter() {
            @Override
            public boolean matches(Class<?> aClass) {
                return Test.Jack.class.isAssignableFrom(aClass);
            }
        };
    }
}
