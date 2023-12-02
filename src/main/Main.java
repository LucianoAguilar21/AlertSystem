package main;

import models.Alert;
import models.AlertType;
import services.AlertService;
import services.TopicService;
import services.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
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

        /* Se añaden los temas que prefieren los usuarios */
        alertSystem.getUsers().get(0).addTopic(alertSystem.getTopics().get(0));
        alertSystem.getUsers().get(0).addTopic(alertSystem.getTopics().get(3));
        alertSystem.getUsers().get(1).addTopic(alertSystem.getTopics().get(3));
        alertSystem.getUsers().get(1).addTopic(alertSystem.getTopics().get(1));
        alertSystem.getUsers().get(2).addTopic(alertSystem.getTopics().get(3));
        alertSystem.getUsers().get(2).addTopic(alertSystem.getTopics().get(2));


        LocalDateTime expire = LocalDateTime.parse("2023-12-08 12:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime expired = LocalDateTime.parse("2023-12-01 12:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Alert alert = new Alert("I1", alertSystem.getTopics().get(0),expire, AlertType.INFORMATIVE);
        Alert alert_expired = new Alert("I2", alertSystem.getTopics().get(0),expired,AlertType.INFORMATIVE);
        Alert alert2 = new Alert("U1", alertSystem.getTopics().get(3),expire,AlertType.URGENT);
        Alert alert3 = new Alert("I3", alertSystem.getTopics().get(1),expire,AlertType.INFORMATIVE);
        Alert alert4 = new Alert("U2", alertSystem.getTopics().get(2),expire,AlertType.URGENT);
        Alert alert5 = new Alert("I4", alertSystem.getTopics().get(2),expire,AlertType.INFORMATIVE);

        alertService.sendAlertToUser(alert,alertSystem.getUsers().get(0));

        alertService.sendAlertByTopic(alert4,alertSystem.getUsers());

        alertService.sendAlertToAllUsers(alert2,alertSystem.getUsers());
        alertService.sendAlertToAllUsers(alert3,alertSystem.getUsers());
        alertService.sendAlertToAllUsers(alert5,alertSystem.getUsers());

        /*
        System.out.println(alertSystem.getUsers());
        System.out.println(alertSystem.getTopics());

        System.out.println(alertSystem.getUsers().get(0).getTopics());
        System.out.println(alertSystem.getUsers().get(0).getAlerts());
        System.out.println(alertSystem.getUsers().get(0).getUnexpiredAlert_ByTopic("Programacion"));
        System.out.println(alertSystem.getUsers().get(0).getUnexpired_And_UnreadAlerts());

        // Marcar alerta como leida
        alertSystem.getUsers().get(0).markAlertAsRead(alertSystem.getUsers().get(0).getUnexpired_And_UnreadAlerts().get(0));

        System.out.println(alertSystem.getUsers().get(0).getUnexpired_And_UnreadAlerts());
        */


    }
}
