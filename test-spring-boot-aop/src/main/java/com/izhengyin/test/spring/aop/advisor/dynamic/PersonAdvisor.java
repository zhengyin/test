package com.izhengyin.test.spring.aop.advisor.dynamic;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhengyin on 2017/8/29 上午10:53.
 * Email  <zhengyin.name@gmail.com> .
 */
public class PersonAdvisor extends DynamicMethodMatcherPointcut{
    private static List<String> adviceMethods = new ArrayList<>();
    static {
        adviceMethods.add("sayFromWhichCountry");
        adviceMethods.add("sayFromCity");
    }

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

    @Override
    public boolean matches(Method method, Class<?> aClass, Object... objects) {
        return adviceMethods.contains(method.getName());
    }
}
