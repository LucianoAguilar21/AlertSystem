package services;

import models.User;

public class UserService {

    public User registerNewUser(int id,String name){
        return new User(id,name);
    }



}
