package tests;
import main.AlertSystem;
import models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.UserService;

import static org.junit.jupiter.api.Assertions.*;
public class UserTest {

    private UserService userService;
    @BeforeEach
    public void setUp(){
        userService = new UserService();
    }

    @Test
    public void userRegisterTest(){
        AlertSystem alertSystem  = new AlertSystem();

        User expectedUser = new User(1,"User1");
        alertSystem.addUser(userService.registerNewUser(1,"User1"));
        Assertions.assertEquals(expectedUser, alertSystem.getUsers().get(0));

    }
}
