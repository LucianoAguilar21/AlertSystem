package models;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

/** Clase Alert para crear las alertas
 *
 */
public class Alert implements Cloneable,Comparable<Alert> {

    private String description;
    private Topic topic;
    private boolean read;
    private LocalDateTime expire;
    private AlertType type;

    private LocalDateTime receivedAt;

    public Alert( String description, Topic topic, LocalDateTime expire, AlertType type) {

        this.description = description;
        this.topic = topic;
        this.read = false;
        this.expire = expire;
        this.type = type;
        receivedAt = null;
    }

    public Alert( String description, Topic topic, AlertType type) {

        this.description = description;
        this.topic = topic;
        this.read = false;
        this.expire = null;
        this.type = type;
        receivedAt = null;
    }



    public String getDescription() {
        return this.description;
    }
    public Topic getTopic() {
        return this.topic;
    }
    public boolean isRead() {
        return this.read;
    }

    public void markAsRead(){
        this.read = true;
    }

    public void markAsUnread(){
        this.read = false;
    }

    public LocalDateTime getExpire() {
        return this.expire;
    }

    public AlertType getType() {
        return this.type;
    }

    public LocalDateTime getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(LocalDateTime receivedAt) {
        this.receivedAt = receivedAt;
    }

    public void setExpire(LocalDateTime expire){
        this.expire = expire;
    }

    public boolean isExpire(){
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(this.getExpire());
    }

    @Override
    public String toString() {
        return "Alert{" +
                "description='" + description + '\'' +
                ", topic=" + topic +
                ", expire=" + expire +
                ", isExpire=" + isExpire()+
                ", type=" + type +
                ", receivedAt="+receivedAt+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alert alert = (Alert) o;
        return read == alert.read && Objects.equals(description, alert.description) && Objects.equals(topic, alert.topic) && Objects.equals(expire, alert.expire) && type == alert.type && Objects.equals(receivedAt, alert.receivedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, topic, read, expire, type, receivedAt);
    }

    @Override
    public Alert clone() {
        try {
            return (Alert) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }


    @Override
    public int compareTo(Alert alert) {
        if(alert.getReceivedAt().isAfter(this.receivedAt)){
            return -1;
        }else if(alert.getReceivedAt().isBefore(this.receivedAt)){
            return 1;
        }else {
            return 0;
        }
    }
}
