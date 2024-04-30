package com.example.myapplication1.API.github;

public class Usuario {

    private String idUser;
    private String userName;
    private String password;

    public Usuario() {
    }
    public Usuario(String nombre, String contraseña) {
        this.idUser = "idUser";
        this.userName = nombre;
        this.password = contraseña;
    }

    public String getNombre() {
        return userName;
    }

    public String getContraseña() {
        return password;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

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
}
