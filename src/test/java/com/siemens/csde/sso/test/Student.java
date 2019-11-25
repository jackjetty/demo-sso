package com.siemens.csde.sso.test;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Student extends Person{
    private String grade;
    public Student(String name,Integer age){
        super(name,age);
    }
}