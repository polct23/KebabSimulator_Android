package edu.upc.dsa.kebabsimulator_android.models;

public class Player {

    private String idPlayer;
    private String userName;
    private String password;
    private String email;
    private int currentLevel;
    private int currentMission;
    private double money;
    public String toString() {
        return "Player{" +
                "idPlayer='" + idPlayer + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void setIdPlayer(String idPlayer) {
        this.idPlayer = idPlayer;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getCurrentMission() {
        return currentMission;
    }

    public void setCurrentMission(int currentMission) {
        this.currentMission = currentMission;
    }



    public Player(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    public Player(String idUser, String userName, String password, String email) {
        this.idPlayer = idUser;
        this.userName = userName;
        this.password = password;
    }
    public Player(String userName, String password, String email) {
        //this.idUser = idUser;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }
    public Player(String idUser, String userName, String password, String email, double money) {
        this.idPlayer = idUser;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Player(){}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getIdPlayer() {
        return idPlayer;
    }

    public Player(String idPlayer, String userName, String password, String email, int currentLevel, int currentMission, double money) {
        this.idPlayer = idPlayer;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.currentLevel = currentLevel;
        this.currentMission = currentMission;
        this.money = money;
    }

    public void setIDUser(String idUser) {
        this.idPlayer = idUser;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
