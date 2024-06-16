package edu.upc.dsa.kebabsimulator_android.models;

public class Mission {
    private String idMission;
    private int reward;
    private String description;

    public String getIdMission() {
        return idMission;
    }

    public void setIdMission(String idMission) {
        this.idMission = idMission;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Mission() {

    }

    public Mission(int reward, String description) {

        this.reward = reward;
        this.description = description;
    }
}
