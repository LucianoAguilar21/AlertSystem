package models;

import java.time.LocalDateTime;
import java.util.*;

public class User {

    int id;
    private String name;
    private List<Topic> topics;
    private List<Alert> alerts;

    public User() {
    }

    public User(int id, String name) {
        this.name = name;
        this.topics = new ArrayList<Topic>();
        this.alerts = new ArrayList<Alert>();
    }

    public User(String name, ArrayList<Topic> topics, ArrayList<Alert> alerts) {
        this.name = name;
        this.topics = topics;
        this.alerts = alerts;
    }

    public String getName() {
        return this.name;
    }

    public List<Topic> getTopics() {
        return this.topics;
    }

    public List<Alert> getAlerts() {
        return this.alerts;
    }

    public void addTopic(Topic topic){
        this.topics.add(topic);
    }

    public void addAlert(Alert alert){
        alert.setReceivedAt(LocalDateTime.now());
        this.alerts.add(alert);
    }

    public List<Alert> getAlertsRead(){
        List<Alert> readAlerts = new ArrayList<Alert>();
        for (Alert alert: this.alerts) {
            if (alert.isRead()){
                readAlerts.add(alert);
            }
        }
        return readAlerts;
    }

    /**
     *
     * @param alert
     * Metodo para marcar Alerta como leída.
     * La Alerta debe ser obtenida del propio Usuario (lista de alertas)
     */
    public void markAlertAsRead(Alert alert){
            alert.markAsRead();
    }

    public List<Alert> getUnexpiredAndUnreadAlerts(){
        List<Alert> unexpiredAndUnreadAlerts = new ArrayList<Alert>();
        for (Alert alert: this.alerts ) {
            if(!alert.isExpire() && !alert.isRead() &&  (alert.getType().equals( AlertType.URGENT))){
                unexpiredAndUnreadAlerts.add(0,alert);
            }else if(!alert.isExpire() && !alert.isRead() &&  (alert.getType().equals( AlertType.INFORMATIVE))){
                unexpiredAndUnreadAlerts.add(alert);
            }
        }

        return unexpiredAndUnreadAlerts;
    }

    public List<Alert> getUnexpiredAlertByTopic(String topicDescription){
        List<Alert> unexpiredAlertByTopic = new ArrayList<Alert>();

        for (Alert alert: this.alerts) {
            if(alert.getTopic().getDescription().equals(topicDescription) && !alert.isExpire() && (alert.getType().equals( AlertType.URGENT))){
                unexpiredAlertByTopic.add(0,alert);
            } else if(alert.getTopic().getDescription().equals(topicDescription) && !alert.isExpire() && (alert.getType().equals( AlertType.INFORMATIVE))){
                unexpiredAlertByTopic.add(alert);
            }
        }

        return unexpiredAlertByTopic;
    }

    @Override
    public String toString() {
        return "User: " +
                "name='" + name + '\'' +
                ", topics=" + topics +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(topics, user.topics) && Objects.equals(alerts, user.alerts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, topics, alerts);
    }
}
