package models;

import java.util.Objects;

public class Topic {
    private String description;

    public Topic(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Topic:" +
                "description='" + description + '\'' ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic = (Topic) o;
        return Objects.equals(description, topic.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
