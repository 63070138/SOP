package com.example.lab3;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;
@Repository
public class Customer {
    private String ID;
    private String name;
    private String sex;
    private Integer age;
    public Customer(){
        this.ID = "";
        this.name = null;
        this.sex = "female";
        this.age = 0;
    }
    public Customer(String ID, String n, String s, int a){
        this.ID = ID;
        this.name = n;
        this.sex = s;
        if(a <= 0){
            this.age = 0;
        } else {
            this.age = a;
        }
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
