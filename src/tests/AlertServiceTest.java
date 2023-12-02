package tests;
import main.AlertSystem;
import models.Alert;
import models.AlertType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.AlertService;
import services.TopicService;
import services.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class AlertServiceTest {


    private UserService userService;
    private AlertSystem alertSystem;
    private TopicService topicService;
    private AlertService alertService;

    @BeforeEach
    public void setUp(){
        userService = new UserService();
        alertSystem  = new AlertSystem();
        topicService = new TopicService();
        alertService = new AlertService();
    }


    @Test
    public void sendAlertByTopicTest(){
        alertSystem.addUser(userService.registerNewUser(1,"User1"));
        alertSystem.addUser(userService.registerNewUser(2,"User2"));
        alertSystem.addUser(userService.registerNewUser(3,"User3"));

        alertSystem.addTopic(topicService.registerNewTopic("Programacion"));
        alertSystem.addTopic(topicService.registerNewTopic("Deportes"));
        alertSystem.addTopic(topicService.registerNewTopic("Noticias"));
        alertSystem.addTopic(topicService.registerNewTopic("Tecnologia"));

        alertSystem.getUsers().get(0).addTopic(alertSystem.getTopics().get(0));
        alertSystem.getUsers().get(0).addTopic(alertSystem.getTopics().get(3));
        alertSystem.getUsers().get(2).addTopic(alertSystem.getTopics().get(3));
        alertSystem.getUsers().get(1).addTopic(alertSystem.getTopics().get(1));


        LocalDateTime expire = LocalDateTime.parse("2023-12-08 12:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime expired = LocalDateTime.parse("2023-12-01 12:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));


        Alert alert2 = new Alert("U1", alertSystem.getTopics().get(3),expire,AlertType.URGENT);


        alertService.sendAlertByTopic(alert2, alertSystem.getUsers());

        int expectedAlerts = 1;

        Assertions.assertEquals(expectedAlerts,alertSystem.getUsers().get(0).getAlerts().size());
        Assertions.assertEquals(expectedAlerts,alertSystem.getUsers().get(2).getAlerts().size());
    }

    @Test
    public void sendAlertToAllUsers(){
        alertSystem.addUser(userService.registerNewUser(1,"User1"));
        alertSystem.addUser(userService.registerNewUser(2,"User2"));
        alertSystem.addUser(userService.registerNewUser(3,"User3"));

        alertSystem.addTopic(topicService.registerNewTopic("Programacion"));
        alertSystem.addTopic(topicService.registerNewTopic("Deportes"));
        alertSystem.addTopic(topicService.registerNewTopic("Noticias"));
        alertSystem.addTopic(topicService.registerNewTopic("Tecnologia"));

        alertSystem.getUsers().get(0).addTopic(alertSystem.getTopics().get(0));
        alertSystem.getUsers().get(0).addTopic(alertSystem.getTopics().get(2));
        alertSystem.getUsers().get(2).addTopic(alertSystem.getTopics().get(3));
        alertSystem.getUsers().get(1).addTopic(alertSystem.getTopics().get(1));


        LocalDateTime expire = LocalDateTime.parse("2023-12-08 12:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Alert alert = new Alert("I1", alertSystem.getTopics().get(0),expire,AlertType.INFORMATIVE);

        Alert alert2 = new Alert("U1", alertSystem.getTopics().get(3),expire,AlertType.URGENT);
        Alert alert3 = new Alert("I3", alertSystem.getTopics().get(2),expire,AlertType.INFORMATIVE);


        alertService.sendAlertToAllUsers(alert, alertSystem.getUsers());
        alertService.sendAlertToAllUsers(alert2, alertSystem.getUsers());

        int expectedAlerts = 1;

        Assertions.assertEquals(expectedAlerts,alertSystem.getUsers().get(0).getAlerts().size());
        Assertions.assertEquals(expectedAlerts,alertSystem.getUsers().get(2).getAlerts().size());


        alertService.sendAlertToAllUsers(alert3, alertSystem.getUsers());

        int expectedAlerts_2 = 2;
        Assertions.assertEquals(expectedAlerts_2,alertSystem.getUsers().get(0).getAlerts().size());

    }

    @Test
    public void sendAlertToUser(){
        alertSystem.addUser(userService.registerNewUser(1,"User1"));
        alertSystem.addUser(userService.registerNewUser(2,"User2"));
        alertSystem.addUser(userService.registerNewUser(3,"User3"));

        alertSystem.addTopic(topicService.registerNewTopic("Programacion"));
        alertSystem.addTopic(topicService.registerNewTopic("Deportes"));
        alertSystem.addTopic(topicService.registerNewTopic("Noticias"));
        alertSystem.addTopic(topicService.registerNewTopic("Tecnologia"));

        alertSystem.getUsers().get(0).addTopic(alertSystem.getTopics().get(0));
        alertSystem.getUsers().get(0).addTopic(alertSystem.getTopics().get(3));
        alertSystem.getUsers().get(2).addTopic(alertSystem.getTopics().get(3));
        alertSystem.getUsers().get(1).addTopic(alertSystem.getTopics().get(1));


        LocalDateTime expire = LocalDateTime.parse("2023-12-08 12:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime expired = LocalDateTime.parse("2023-12-01 12:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Alert alert = new Alert("I1", alertSystem.getTopics().get(0),expire,AlertType.INFORMATIVE);
        Alert alert2 = new Alert("U1", alertSystem.getTopics().get(3),expire,AlertType.URGENT);

        alertService.sendAlertToUser(alert,alertSystem.getUsers().get(0));

        int expectedAlerts = 1;
        Assertions.assertEquals(expectedAlerts, alertSystem.getUsers().get(0).getAlerts().size());

        int expectedAlerts_2 = 2;
        alertService.sendAlertToUser(alert2,alertSystem.getUsers().get(0));
        Assertions.assertEquals(expectedAlerts_2, alertSystem.getUsers().get(0).getAlerts().size());
    }
}
