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

import static org.junit.jupiter.api.Assertions.*;

public class AlertTest {
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
    public void isReadTest_And_markAsReadTest(){
        alertSystem.addTopic(topicService.registerNewTopic("topic1"));
        LocalDateTime expire = LocalDateTime.parse("2023-12-08 12:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Alert alert = new Alert("I1", alertSystem.getTopics().get(0),expire, AlertType.INFORMATIVE);

        // alert must be not read
        Assertions.assertFalse(alert.isRead());

        // mark alert as read
        alert.markAsRead();

        // the alert should be marked
        Assertions.assertTrue(alert.isRead());


    }

    @Test
    public void getTypeTest(){
        alertSystem.addTopic(topicService.registerNewTopic("topic1"));

        LocalDateTime expire = LocalDateTime.parse("2023-12-08 12:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));


        Alert alert = new Alert("I1", alertSystem.getTopics().get(0),expire, AlertType.INFORMATIVE);

        String expected = "INFORMATIVE";

        Assertions.assertEquals(expected,alert.getType().name());
    }

    @Test
    public void isExpiredTest(){
        alertSystem.addTopic(topicService.registerNewTopic("topic1"));

        LocalDateTime expire = LocalDateTime.parse("2023-12-08 12:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime expired = LocalDateTime.parse("2023-12-01 12:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Alert alert = new Alert("I1", alertSystem.getTopics().get(0),expire, AlertType.INFORMATIVE);
        Alert alert_expired = new Alert("I2", alertSystem.getTopics().get(0),expired,AlertType.INFORMATIVE);

        // the alert did not expire
        Assertions.assertFalse(alert.isExpired());

        // the alert has expired
        Assertions.assertTrue(alert_expired.isExpired());
    }



}
