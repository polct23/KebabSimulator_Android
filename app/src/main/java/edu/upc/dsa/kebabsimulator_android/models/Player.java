package edu.upc.dsa.kebabsimulator_android.models;

public class Player {

    private String idUser;
    private String userName;
    private String password;
    private String email;

    private double money;

    public Player(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    public Player(String idUser, String userName, String password, String email) {
        this.idUser = idUser;
        this.userName = userName;
        this.password = password;
    }
    public Player(String userName, String password, String email) {
        //this.idUser = idUser;
        this.email = email;
        this.userName = userName;
        this.password = password;
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
    public String getIdUser() {
        return idUser;
    }
    public void setIDUser(String idUser) {
        this.idUser = idUser;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
