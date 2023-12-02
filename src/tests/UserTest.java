package tests;
import main.AlertSystem;
import models.Alert;
import models.AlertType;
import models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.AlertService;
import services.TopicService;
import services.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
public class UserTest {

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
    public void userServiceTest(){
        User user = new User(1,"User Test");
        User userFromService = userService.registerNewUser(2,"User from service");
        Assertions.assertEquals(user.getClass(), userFromService.getClass());
    }
    @Test
    public void userRegisterTest(){

        User expectedUser = new User(1,"User1");
        alertSystem.addUser(userService.registerNewUser(1,"User1"));
        Assertions.assertEquals(expectedUser, alertSystem.getUsers().get(0));

    }

    @Test
    public void alertCannotBeAdded(){
        LocalDateTime expire = LocalDateTime.parse("2023-12-08 12:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        alertSystem.addUser(userService.registerNewUser(1,"user1"));
        alertSystem.addUser(userService.registerNewUser(2,"user2"));
        alertSystem.addTopic(topicService.registerNewTopic("topic1"));
        alertSystem.addTopic(topicService.registerNewTopic("topic2"));

        alertSystem.getUsers().get(0).addTopic(alertSystem.getTopics().get(0));
        alertSystem.getUsers().get(1).addTopic(alertSystem.getTopics().get(0));


        Alert alert = new Alert("I1", alertSystem.getTopics().get(1),expire, AlertType.INFORMATIVE);

        // try adding the alert
        alertService.sendAlertToUser(alert,alertSystem.getUsers().get(0));

        // The alert list is empty even though you tried to add an alert
        Assertions.assertTrue(alertSystem.getUsers().get(0).getAlerts().isEmpty());
    }
    @Test
    public void countingFiveRegisteredUsersTest(){

        int expectedUsers = 5;

        alertSystem.addUser(userService.registerNewUser(1,"User1"));
        alertSystem.addUser(userService.registerNewUser(2,"User2"));
        alertSystem.addUser(userService.registerNewUser(3,"User3"));
        alertSystem.addUser(userService.registerNewUser(1,"User4"));
        alertSystem.addUser(userService.registerNewUser(2,"User5"));

        Assertions.assertEquals(expectedUsers, alertSystem.getUsers().size());

    }
    @Test
    public void countingTenRegisteredUsersTest(){

        int expectedUsers = 10;

        alertSystem.addUser(userService.registerNewUser(1,"User1"));
        alertSystem.addUser(userService.registerNewUser(2,"User2"));
        alertSystem.addUser(userService.registerNewUser(3,"User3"));
        alertSystem.addUser(userService.registerNewUser(1,"User4"));
        alertSystem.addUser(userService.registerNewUser(2,"User5"));
        alertSystem.addUser(userService.registerNewUser(1,"User6"));
        alertSystem.addUser(userService.registerNewUser(2,"User7"));
        alertSystem.addUser(userService.registerNewUser(3,"User8"));
        alertSystem.addUser(userService.registerNewUser(1,"User9"));
        alertSystem.addUser(userService.registerNewUser(2,"User10"));

        Assertions.assertEquals(expectedUsers, alertSystem.getUsers().size());

    }

    @Test
    public void getTopicsTest(){
        alertSystem.addUser(userService.registerNewUser(1,"user1"));
        alertSystem.addTopic(topicService.registerNewTopic("topic1"));

        // the topics list must be empty
        Assertions.assertTrue(alertSystem.getUsers().get(0).getTopics().isEmpty());

        alertSystem.getUsers().get(0).addTopic(alertSystem.getTopics().get(0));

        // topics list is not empty
        Assertions.assertFalse(alertSystem.getUsers().get(0).getTopics().isEmpty());

    }

    @Test
    public void getAlertsIsEmptyTest(){
        alertSystem.addUser(userService.registerNewUser(1,"user1"));

        // the alert list must be empty
        Assertions.assertTrue(alertSystem.getUsers().get(0).getAlerts().isEmpty());
    }

    @Test
    public void getAlertTest(){
        LocalDateTime expire = LocalDateTime.parse("2023-12-08 12:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        alertSystem.addUser(userService.registerNewUser(1,"user1"));
        alertSystem.addTopic(topicService.registerNewTopic("topic1"));

        alertSystem.getUsers().get(0).addTopic(alertSystem.getTopics().get(0));


        Alert alert = new Alert("I1", alertSystem.getTopics().get(0),expire, AlertType.INFORMATIVE);

        // the alert list must be empty
        Assertions.assertTrue(alertSystem.getUsers().get(0).getAlerts().isEmpty());

        // the alert is added to the user
        alertService.sendAlertToUser(alert,alertSystem.getUsers().get(0));

        // alert list is not empty
        Assertions.assertFalse(alertSystem.getUsers().get(0).getAlerts().isEmpty());

        // "alert" instance description is equal to the description of the alert that was added to the user
        Assertions.assertEquals(alert.getDescription(), alertSystem.getUsers().get(0).getAlerts().get(0).getDescription());

    }

    @Test
    public void addTopicsTest(){
        alertSystem.addUser(userService.registerNewUser(1,"user1"));
        alertSystem.addTopic(topicService.registerNewTopic("topic1"));

        Assertions.assertTrue(alertSystem.getUsers().get(0).getTopics().isEmpty());

        alertSystem.getUsers().get(0).addTopic(alertSystem.getTopics().get(0));

        Assertions.assertFalse(alertSystem.getUsers().get(0).getTopics().isEmpty());
    }

    @Test
    public void addAlertTest(){
        LocalDateTime expire = LocalDateTime.parse("2023-12-08 12:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        alertSystem.addUser(userService.registerNewUser(1,"user1"));
        alertSystem.addUser(userService.registerNewUser(2,"user2"));
        alertSystem.addTopic(topicService.registerNewTopic("topic1"));

        alertSystem.getUsers().get(0).addTopic(alertSystem.getTopics().get(0));
        alertSystem.getUsers().get(1).addTopic(alertSystem.getTopics().get(0));
        Alert alert = new Alert("I1", alertSystem.getTopics().get(0),expire, AlertType.INFORMATIVE);

        // the alert is added to the user
        alertService.sendAlertToUser(alert,alertSystem.getUsers().get(1));

        // user1 must have empty alert list
        Assertions.assertTrue(alertSystem.getUsers().get(0).getAlerts().isEmpty());

        // user2 should not have empty alert list
        Assertions.assertFalse(alertSystem.getUsers().get(1).getAlerts().isEmpty());


    }

    @Test
    public void getAlertsReadTest(){
        LocalDateTime expire = LocalDateTime.parse("2023-12-08 12:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        alertSystem.addUser(userService.registerNewUser(1,"user1"));
        alertSystem.addUser(userService.registerNewUser(2,"user2"));
        alertSystem.addTopic(topicService.registerNewTopic("topic1"));

        alertSystem.getUsers().get(0).addTopic(alertSystem.getTopics().get(0));
        alertSystem.getUsers().get(1).addTopic(alertSystem.getTopics().get(0));
        Alert alert = new Alert("I1", alertSystem.getTopics().get(0),expire, AlertType.INFORMATIVE);

        // the alert is added to the user
        alertService.sendAlertToUser(alert,alertSystem.getUsers().get(1));

        // user1 must have empty alert list
        Assertions.assertTrue(alertSystem.getUsers().get(0).getAlerts().isEmpty());

        // user2 should not have empty alert list
        Assertions.assertFalse(alertSystem.getUsers().get(1).getAlerts().isEmpty());

        // the alert is not read
        Assertions.assertFalse(alertSystem.getUsers().get(1).getAlerts().get(0).isRead());

        Assertions.assertEquals(0, alertSystem.getUsers().get(1).getAlertsRead().size());
        // mark alert as read
        alertSystem.getUsers().get(1).markAlertAsRead(alertSystem.getUsers().get(1).getAlerts().get(0));

        Assertions.assertEquals(1,alertSystem.getUsers().get(1).getAlertsRead().size());
    }

    @Test
    public void markAlertAsRead(){
        LocalDateTime expire = LocalDateTime.parse("2023-12-08 12:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        alertSystem.addUser(userService.registerNewUser(1,"user1"));

        alertSystem.addUser(userService.registerNewUser(2,"user2"));

        alertSystem.addTopic(topicService.registerNewTopic("topic1"));

        alertSystem.getUsers().get(0).addTopic(alertSystem.getTopics().get(0));
        alertSystem.getUsers().get(1).addTopic(alertSystem.getTopics().get(0));

        Alert alert = new Alert("I1", alertSystem.getTopics().get(0),expire, AlertType.INFORMATIVE);

        // the alert is added to the user
        alertService.sendAlertToUser(alert,alertSystem.getUsers().get(1));

        // user1 must have empty alert list
        Assertions.assertTrue(alertSystem.getUsers().get(0).getAlerts().isEmpty());

        // user2 should not have empty alert list
        Assertions.assertFalse(alertSystem.getUsers().get(1).getAlerts().isEmpty());

        // the alert is not read
        Assertions.assertFalse(alertSystem.getUsers().get(1).getAlerts().get(0).isRead());

        // mark alert as read
        alertSystem.getUsers().get(1).markAlertAsRead(alertSystem.getUsers().get(1).getAlerts().get(0));

        // the alert is marked as read
        Assertions.assertTrue(alertSystem.getUsers().get(1).getAlerts().get(0).isRead());
    }

    @Test
    public void getUnexpired_And_UnreadAlerts(){
        alertSystem.addUser(userService.registerNewUser(1,"user1"));

        alertSystem.addTopic(topicService.registerNewTopic("topic1"));
        alertSystem.addTopic(topicService.registerNewTopic("topic"));

        alertSystem.getUsers().get(0).addTopic(alertSystem.getTopics().get(0));
        alertSystem.getUsers().get(0).addTopic(alertSystem.getTopics().get(1));

        LocalDateTime expire = LocalDateTime.parse("2023-12-08 12:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime expired = LocalDateTime.parse("2023-12-01 12:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Alert alert = new Alert("I1", alertSystem.getTopics().get(0),expire,AlertType.INFORMATIVE);
        Alert alert_expired = new Alert("I2", alertSystem.getTopics().get(0),expired,AlertType.INFORMATIVE);
        Alert alert2 = new Alert("U1", alertSystem.getTopics().get(1),expire,AlertType.URGENT);
        Alert alert3 = new Alert("I3", alertSystem.getTopics().get(0),expire,AlertType.INFORMATIVE);
        Alert alert4 = new Alert("U2", alertSystem.getTopics().get(0),expire,AlertType.URGENT);
        Alert alert5 = new Alert("I4", alertSystem.getTopics().get(1),expire,AlertType.INFORMATIVE);

        alertService.sendAlertToUser(alert,alertSystem.getUsers().get(0));
        alertService.sendAlertToUser(alert_expired,alertSystem.getUsers().get(0)); //expired
        alertService.sendAlertToUser(alert2,alertSystem.getUsers().get(0));
        alertService.sendAlertToUser(alert3,alertSystem.getUsers().get(0));
        alertService.sendAlertToUser(alert4,alertSystem.getUsers().get(0));
        alertService.sendAlertToUser(alert5,alertSystem.getUsers().get(0));

        int expected = 5;

        Assertions.assertEquals(expected, alertSystem.getUsers().get(0).getUnexpired_And_UnreadAlerts().size());

    }

    @Test
    public void getUnexpiredAlertByTopic(){
        alertSystem.addUser(userService.registerNewUser(1,"user1"));

        alertSystem.addTopic(topicService.registerNewTopic("topic1"));
        alertSystem.addTopic(topicService.registerNewTopic("topic2"));

        alertSystem.getUsers().get(0).addTopic(alertSystem.getTopics().get(0));

        LocalDateTime expire = LocalDateTime.parse("2023-12-08 12:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime expired = LocalDateTime.parse("2023-12-01 12:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Alert alert = new Alert("I1", alertSystem.getTopics().get(0),expire,AlertType.INFORMATIVE);
        Alert alert_expired = new Alert("I2", alertSystem.getTopics().get(0),expired,AlertType.INFORMATIVE);
        Alert alert2 = new Alert("U1", alertSystem.getTopics().get(1),expire,AlertType.URGENT);
        Alert alert3 = new Alert("I3", alertSystem.getTopics().get(0),expire,AlertType.INFORMATIVE);
        Alert alert4 = new Alert("U2", alertSystem.getTopics().get(0),expire,AlertType.URGENT);
        Alert alert5 = new Alert("I4", alertSystem.getTopics().get(1),expire,AlertType.INFORMATIVE);

        alertService.sendAlertToUser(alert,alertSystem.getUsers().get(0));
        alertService.sendAlertToUser(alert_expired,alertSystem.getUsers().get(0)); //expired
        alertService.sendAlertToUser(alert2,alertSystem.getUsers().get(0));
        alertService.sendAlertToUser(alert3,alertSystem.getUsers().get(0));
        alertService.sendAlertToUser(alert4,alertSystem.getUsers().get(0));
        alertService.sendAlertToUser(alert5,alertSystem.getUsers().get(0));

        int expected = 3;

        Assertions.assertEquals(expected, alertSystem.getUsers().get(0).getUnexpiredAlert_ByTopic("topic1").size());
    }
}
