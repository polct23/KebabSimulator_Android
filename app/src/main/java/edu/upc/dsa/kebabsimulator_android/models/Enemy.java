package edu.upc.dsa.kebabsimulator_android.models;

public class Enemy {

    private String idEnemy;
    private String name;
    private int meat;
    private int speed;

    public Enemy() {

    }

    private String description;

    public String getIdEnemy() {
        return idEnemy;
    }

    public void setIdEnemy(String idEnemy) {
        this.idEnemy = idEnemy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMeat() {
        return meat;
    }

    public void setMeat(int meat) {
        this.meat = meat;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Enemy(String name, int meat, int speed, String description) {

        this.name = name;
        this.meat = meat;
        this.speed = speed;
        this.description = description;
    }
}
