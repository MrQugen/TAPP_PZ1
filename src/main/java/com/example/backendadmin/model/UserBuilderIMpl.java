package com.example.backendadmin.model;

public class UserBuilderIMpl implements UserBuilder {
    User user = new User();

    @Override
    public UserBuilder setCity(String city) {
        user.city = city;
        return this;
    }

    @Override
    public UserBuilder setTelephone(String telephone) {
        user.telephone = telephone;
        return this;
    }

    @Override
    public UserBuilder setUser_id(int user_id) {
        user.user_id = user_id;
        return this;
    }

    @Override
    public UserBuilder setName(String name) {
        user.name = name;
        return this;
    }

    @Override
    public UserBuilder setSurname(String surname) {
        user.surname = surname;
        return this;
    }

    @Override
    public UserBuilder setPatronymic(String patronymic) {
        user.patronymic = patronymic;
        return this;
    }

    @Override
    public UserBuilder setLogin(String login) {
        user.login = login;
        return this;
    }

    @Override
    public UserBuilder setPassword(String password) {
        user.password = password;
        return this;
    }

    @Override
    public UserBuilder setRole(String role) {
        user.role = role;
        return this;
    }

    @Override
    public User build() {
        return user;
    }
}
