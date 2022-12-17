package com.example.backendadmin.DAO;

import com.example.backendadmin.model.User;

import java.util.List;

public interface UserDAO {
    public List<User> index();
    public User show(String login);
    public void save(User NewUser);
    public void update(String login, User updateUser);
    public void update_user_info(String login, User updateUser);
    public void update_user_security(String login, User updateUser);
    public void delete(int id);

}
