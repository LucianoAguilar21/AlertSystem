package services;

import models.Topic;

public class TopicService {

    public Topic registerNewTopic(String description){
        return new Topic(description);
    }
}
