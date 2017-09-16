package com.izhengyin.test.spring.aspectJ.aop.advice;

/**
 * Created by zhengyin on 2017/8/29 下午12:05.
 * Email  <zhengyin.name@gmail.com> .
 */
public class Asker {

    private Person person;

    public void ask(){
        person.sayFromWhichCountry();
    }

    public void setPerson(Person person){
        this.person = person;
    }
}
