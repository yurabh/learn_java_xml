package com.domain;

import java.util.Objects;

public class User {

    private String userName;
    private int age;
    private City city;
    private String surName;

    public User() {
    }

    public User(String userName, int age, City city, String surName) {
        this.userName = userName;
        this.age = age;
        this.city = city;
        this.surName = surName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
                Objects.equals(userName, user.userName) &&
                city == user.city &&
                Objects.equals(surName, user.surName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, age, city, surName);
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                ", city=" + city +
                ", surName='" + surName + '\'' +
                '}';
    }
}
