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


}