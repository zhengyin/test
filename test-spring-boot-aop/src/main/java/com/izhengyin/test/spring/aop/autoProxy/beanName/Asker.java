package com.izhengyin.test.spring.aop.advisor.composablePointcut;

/**
 * Created by zhengyin on 2017/8/29 下午12:05.
 * Email  <zhengyin.name@gmail.com> .
 */
public class Asker {

    private Person person;

    public void ask(){
        person.sayFromWhichCountry();
        person.sayFromCity();
    }

    public void setPerson(Person person){
        this.person = person;
    }
}
