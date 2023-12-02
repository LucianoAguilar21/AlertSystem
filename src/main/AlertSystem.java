package main;

import models.Alert;
import models.AlertType;
import models.Topic;
import models.User;
import services.AlertService;
import services.TopicService;
import services.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AlertSystem {
    private  List<User> users;
    private  List<Topic> topics;

    public AlertSystem() {
        this.users = new ArrayList<User>();
        this.topics = new ArrayList<Topic>();
    }

    public List<User> getUsers(){
        return this.users;
    }
    public List<Topic> getTopics(){
        return this.topics;
    }

    public void addUser(User user){
        this.users.add(user);
    }

    public void addTopic(Topic topic){
        this.topics.add(topic);
    }



    public static void main(String[] args) {

        AlertSystem alertSystem = new AlertSystem();
        UserService userService = new UserService();
        TopicService topicService = new TopicService();
        AlertService alertService = new AlertService();
        /* Registro de Usuarios */

        alertSystem.addUser(userService.registerNewUser(1,"User1"));
        alertSystem.addUser(userService.registerNewUser(2,"User2"));
        alertSystem.addUser(userService.registerNewUser(3,"User3"));


        /* Registro de Temas*/
        alertSystem.addTopic(topicService.registerNewTopic("Programacion"));
        alertSystem.addTopic(topicService.registerNewTopic("Deportes"));
        alertSystem.addTopic(topicService.registerNewTopic("Noticias"));
        alertSystem.addTopic(topicService.registerNewTopic("Tecnologia"));

        alertSystem.getUsers().get(0).addTopic(alertSystem.getTopics().get(0));
        alertSystem.getUsers().get(0).addTopic(alertSystem.getTopics().get(3));
        alertSystem.getUsers().get(2).addTopic(alertSystem.getTopics().get(3));


        LocalDateTime expire = LocalDateTime.parse("2023-12-03 12:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime expired = LocalDateTime.parse("2023-12-01 12:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Alert alert = new Alert("I1", alertSystem.getTopics().get(0),expire,AlertType.INFORMATIVE);
        Alert alert_expired = new Alert("I2", alertSystem.getTopics().get(0),expired,AlertType.INFORMATIVE);
        Alert alert2 = new Alert("U1", alertSystem.getTopics().get(3),expire,AlertType.URGENT);
        Alert alert3 = new Alert("I3", alertSystem.getTopics().get(2),expire,AlertType.INFORMATIVE);
        Alert alert4 = new Alert("U2", alertSystem.getTopics().get(2),expire,AlertType.URGENT);
        Alert alert5 = new Alert("I4", alertSystem.getTopics().get(2),expire,AlertType.INFORMATIVE);







    }
}