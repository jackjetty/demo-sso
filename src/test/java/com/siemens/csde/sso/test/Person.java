package com.siemens.csde.sso.test;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Person{
    private String name;
    private Integer age;

    public Person(String name,Integer age){
        this.age=age;
        this.name=name;
    }
}
