package edu.upc.dsa.kebabsimulator_android.models;

public class Ability {
    private String idAbility;
    private String abilityName;
    private String description;
    private int value;

    private String imageURL;

    private double price;

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
    public Ability() {
    }
    public Ability(String nombre, String descripcion, int value, String imageURL, double price) {
        this.abilityName = nombre;
        this.description = descripcion;
        this.value = value;
        this.imageURL = imageURL;
        this.price = price;
    }

    public Ability(String nombre, String descripcion) {
        this.abilityName = nombre;
        this.description = descripcion;
    }
    public Ability(String nombre, String descripcion, double price) {
        this.abilityName = nombre;
        this.description = descripcion;
        this.price = price;
    }
    public Ability(String idAbility, String abilityName, String description, int value, double price) {
        this.idAbility = idAbility;
        this.abilityName = abilityName;
        this.description = description;
        this.value = value;
        this.price = price;
    }

    public String getIdAbility() {
        return idAbility;
    }

    public void setIdAbility(String idAbility) {
        this.idAbility = idAbility;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getNombre() {
        return abilityName;
    }

    public void setNombre(String nombre) {
        this.abilityName = nombre;
    }

    public String getDescripcion() {
        return description;
    }

    public void setDescripcion(String descripcion) {
        this.description = descripcion;
    }



    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
