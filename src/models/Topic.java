package models;

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
}
