package com.example.backendadmin.model;

import com.example.backendadmin.observer_pattern.Observer;

import java.util.List;

public class User implements Observer {
    int user_id;
    String name, surname, patronymic, login, password, role, telephone, city;

    public User(){}

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", telephone='" + telephone + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    @Override
    public void handleEvent(String type, Product product) {
        if(type.equals("add"))
        {
            System.out.println("Dear " + name + "\nWe have a new product:\n" + product +
                    "\n======================================================\n");
        }
        else if(type.equals("update"))
        {
            System.out.println("Dear " + name + "\nWe have some changes in product:\n" + product +
                    "\n======================================================\n");
        }
        else if(type.equals("delete")){
            System.out.println("Dear " + name + "\nWe no longer have the product:\n" + product +
                    "\n======================================================\n");
        }
        else{
            System.out.println("Dear " + name + "\nNo changes.");
        }

    }
}
