package pl.coderslab.warsztaty3.model;

public class Solution {

    private int id;
    private String created;
    private String updated;
    private String description;
    private int exercise_id;
    private int user_id;

    public Solution(String description, int exercise_id, int user_id) {
        this.description = description;
        this.exercise_id = exercise_id;
        this.user_id = user_id;
    }

    public Solution() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(int exercise_id) {
        this.exercise_id = exercise_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "id=" + id +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                ", description='" + description + '\'' +
                ", exercise_id=" + exercise_id +
                ", user_id=" + user_id +
                '}';
    }
}
