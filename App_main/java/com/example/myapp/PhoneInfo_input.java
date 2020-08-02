package com.example.myapp;

public class PhoneInfo_input {
    private  int id;
    private String name;
    private String number;
    private String number_2;


    public PhoneInfo_input(String name, String number,String number_2){
        setName(name);
        setNumber(number);
        setNumber_2(number_2);
    }

    public String getNumber_2() {
        return number_2;
    }

    public void setNumber_2(String number_2) {
        this.number_2 = number_2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
