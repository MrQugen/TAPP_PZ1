package com.example.backendadmin.model;

public interface UserBuilder {

    UserBuilder setCity(String city);
    UserBuilder setTelephone(String telephone);
    UserBuilder setUser_id(int user_id);
    UserBuilder setName(String name);
    UserBuilder setSurname(String surname);
    UserBuilder setPatronymic(String patronymic);
    UserBuilder setLogin(String login);
    UserBuilder setPassword(String password);
    UserBuilder setRole(String role);

    User build();
}
