package com.caroline.recyclerview02.model;

import java.io.Serializable;

public class User implements Serializable {

    private String name;
    private String price;
    private String calories;
    private boolean isVegetarian;

    public User(){

    }

    public User(String name, String price, String calories, boolean isVegetarian) {
        this.name = name;
        this.price = price;
        this.calories = calories;
        this.isVegetarian = isVegetarian;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getPrice() { return price; }

    public void setPrice(String price) { this.price = price; }

    public String getCalories() { return calories; }

    public void setCalories(String calories) { this.calories = calories; }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        isVegetarian = vegetarian;
    }

}
