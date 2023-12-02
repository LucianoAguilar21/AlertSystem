package tests;
import main.AlertSystem;
import models.Topic;
import models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.TopicService;
import services.UserService;

import static org.junit.jupiter.api.Assertions.*;
public class TopicTest {

    private TopicService topicService;
    private AlertSystem alertSystem;
    private UserService userService;

    @BeforeEach
    public void setUp(){
        topicService = new TopicService();
        alertSystem  = new AlertSystem();
        userService = new UserService();
    }

    @Test
    public void topicServiceTest(){
        Topic topic = new Topic("Topic Test");
        Topic topicFromService = topicService.registerNewTopic("Topic from service");
        Assertions.assertEquals(topic.getClass(), topicFromService.getClass());
    }
    @Test
    public void topicRegisterTest(){

        Topic expectedTopic = new Topic("Topic1");
        alertSystem.addTopic(topicService.registerNewTopic("Topic1"));
        Assertions.assertEquals(expectedTopic, alertSystem.getTopics().get(0));

    }

    @Test
    public void countingFiveRegisteredTopicsTest(){

        int expectedTopics = 5;

        alertSystem.addTopic(topicService.registerNewTopic("Topic1"));
        alertSystem.addTopic(topicService.registerNewTopic("Topic2"));
        alertSystem.addTopic(topicService.registerNewTopic("Topic3"));
        alertSystem.addTopic(topicService.registerNewTopic("Topic4"));
        alertSystem.addTopic(topicService.registerNewTopic("Topic5"));

        Assertions.assertEquals(expectedTopics, alertSystem.getTopics().size());

    }
    @Test
    public void countingTenRegisteredTopicsTest(){

        int expectedTopics = 10;

        alertSystem.addTopic(topicService.registerNewTopic("Topic1"));
        alertSystem.addTopic(topicService.registerNewTopic("Topic2"));
        alertSystem.addTopic(topicService.registerNewTopic("Topic3"));
        alertSystem.addTopic(topicService.registerNewTopic("Topic4"));
        alertSystem.addTopic(topicService.registerNewTopic("Topic5"));
        alertSystem.addTopic(topicService.registerNewTopic("Topic6"));
        alertSystem.addTopic(topicService.registerNewTopic("Topic7"));
        alertSystem.addTopic(topicService.registerNewTopic("Topic8"));
        alertSystem.addTopic(topicService.registerNewTopic("Topic9"));
        alertSystem.addTopic(topicService.registerNewTopic("Topic10"));

        Assertions.assertEquals(expectedTopics, alertSystem.getTopics().size());

    }

    @Test
    public void userWithEmptyTopicsTest(){
        alertSystem.addUser(userService.registerNewUser(1,"user1"));
        Assertions.assertTrue(alertSystem.getUsers().get(0).getTopics().isEmpty());
    }
    @Test
    public void addTopicToUserTest(){
        alertSystem.addUser(userService.registerNewUser(1,"user1"));
        alertSystem.addTopic(topicService.registerNewTopic("topic1"));

        Assertions.assertTrue(alertSystem.getUsers().get(0).getTopics().isEmpty());

        alertSystem.getUsers().get(0).addTopic(alertSystem.getTopics().get(0));

        Assertions.assertFalse(alertSystem.getUsers().get(0).getTopics().isEmpty());
    }
}
