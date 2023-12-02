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

    /**
     *
     * @param topic
     * 3. Los usuarios pueden optar sobre cuales temas quieren recibir alertas.
     */
    public void addTopic(Topic topic){
        this.topics.add(topic);
    }

    public void addAlert(Alert alert){

        if(this.getTopics().contains(alert.getTopic())){
            alert.setReceivedAt(LocalDateTime.now());
            this.alerts.add(alert);
        }

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
     * 8. Un usuario puede marcar una alerta como leída.
     * Metodo para marcar Alerta como leída.
     * La Alerta debe ser obtenida del propio Usuario (lista de alertas)
     *
     */
    public void markAlertAsRead(Alert alert){
            alert.markAsRead();
    }


    /**
     *
     * @return List<Alert>
     * 9. Se pueden obtener todas las alertas no expiradas de un usuario que aún no ha leído.
     */

    public List<Alert> getUnexpired_And_UnreadAlerts(){
        List<Alert> unexpired_And_UnreadAlerts = new ArrayList<Alert>();
        for (Alert alert: this.alerts ) {
            if(!alert.isExpired() && !alert.isRead() &&  (alert.getType().equals( AlertType.URGENT))){
                unexpired_And_UnreadAlerts.add(0,alert);
            }else if(!alert.isExpired() && !alert.isRead() &&  (alert.getType().equals( AlertType.INFORMATIVE))){
                unexpired_And_UnreadAlerts.add(alert);
            }
        }

        return unexpired_And_UnreadAlerts;
    }

    /**
     *
     * @param topicDescription
     * @return List<Alert>
     * 10. Se pueden obtener todas las alertas no expiradas para un tema.
     */
    public List<Alert> getUnexpiredAlert_ByTopic(String topicDescription){
        List<Alert> unexpiredAlertByTopic = new ArrayList<Alert>();

        for (Alert alert: this.alerts) {
            if(alert.getTopic().getDescription().equals(topicDescription) && !alert.isExpired() && (alert.getType().equals( AlertType.URGENT))){
                unexpiredAlertByTopic.add(0,alert);
            } else if(alert.getTopic().getDescription().equals(topicDescription) && !alert.isExpired() && (alert.getType().equals( AlertType.INFORMATIVE))){
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
