package services;

import models.Alert;
import models.User;

import java.util.List;

public class AlertService {

    /**
     *
     * @param alert
     * @param users
     * 4. Se puede enviar una alerta sobre un tema y lo reciben todos los usuarios que han optado recibir alertas de ese tema.
     */
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

    /**
     *
     * @param alert
     * @param user
     * 5. Se puede enviar una alerta sobre un tema a un usuario específico, solo lo recibe ese único usuario.
     */
    public void sendAlertToUser(Alert alert, User user){
        Alert alert_clone = alert.clone();
        user.addAlert(alert_clone);
    }

}
