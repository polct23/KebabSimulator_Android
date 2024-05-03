package edu.upc.dsa.kebabsimulator_android.models;

public class Weapon {
    private String weaponName;
    private String description;
    private int damage;
    private double price;

    public Weapon(String nombre, String descripcion) {
        this.weaponName = nombre;
        this.description = descripcion;
    }
    public Weapon(String nombre, String descripcion, int damage, double price) {
        this.weaponName = nombre;
        this.description = descripcion;
        this.damage = damage;
        this.price = price;
    }

    public String getNombre() {
        return weaponName;
    }

    public void setNombre(String nombre) {
        this.weaponName = nombre;
    }

    public String getDescripcion() {
        return description;
    }

    public void setDescripcion(String descripcion) {
        this.description = descripcion;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
