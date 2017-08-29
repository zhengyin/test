package com.izhengyin.test.spring.aop.advisor.composablePointcut;

import org.springframework.aop.Pointcut;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.NameMatchMethodPointcut;

/**
 * Created by zhengyin on 2017/8/29 下午1:57.
 * Email  <zhengyin.name@gmail.com> .
 */
public class PersonAdvisor{



    // 交集 , 必须符合 ControlFlowPointcut 流程切面 ，且还是 NameMatchMethodPointcut 中匹配的方法
    public Pointcut getIntersectionPointcut(){
        ComposablePointcut cp = new ComposablePointcut();
        Pointcut pt1 = new ControlFlowPointcut(Asker.class,"ask");
        NameMatchMethodPointcut pt2 = new NameMatchMethodPointcut();
        pt2.addMethodName("sayName");
        pt2.addMethodName("sayFromCity");
        pt2.addMethodName("sayFromWhichCountry");
        return cp.intersection((Pointcut) pt2).intersection(pt1);
    }

    public Pointcut getUnionPointcut(){
        return new ComposablePointcut();        // All Method
    }

}
