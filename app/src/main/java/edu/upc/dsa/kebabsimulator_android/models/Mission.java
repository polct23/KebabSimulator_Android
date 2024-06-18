package edu.upc.dsa.kebabsimulator_android.models;

public class Mission {
    private int idMission;
    private int reward;
    private String description;

    public int getIdMission() {
        return idMission;
    }

    public void setIdMission(int idMission) {
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
    public Mission(int idMission, int reward) {
        this.idMission = idMission;
        this.reward = reward;
    }

    public Mission(int idMission, String description) {
        this.idMission = idMission;
        this.description = description;
    }

    public Mission(int idMission,int  reward, String description) {
        this.idMission = idMission;
        this.reward = reward;
        this.description = description;
    }
}
