package services;

import models.Alert;
import models.User;

import java.util.List;

public class AlertService {

    public void sendAlertByTopic(Alert alert, List<User> users){

        for (User user: users) {
            Alert alert_clone = alert.clone();
            if(user.getTopics().contains(alert_clone.getTopic())){
                user.addAlert(alert_clone);
            }
        }
    }

    public void sendAlertToAllUsers(Alert alert,List<User> users){
        for (User user: users) {
            Alert alert_clone = alert.clone();
            user.addAlert(alert_clone);
        }
    }

    public void sendAlertToUser(Alert alert, User user){
        Alert alert_clone = alert.clone();
        user.addAlert(alert_clone);
    }

}
