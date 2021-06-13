package com.example.starkbuck_mvc.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class starbuck {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public int id;

    public String name;
    public String category;
    public String avatar;
    public int price;
    public int sale;

    public starbuck() {}

    public starbuck(String name, String category, String avatar, int price, int sale) {
        this.name = name;
        this.category = category;
        this.avatar = avatar;
        this.price = price;
        this.sale = sale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

//    public starbuck(int id, String name, String category, String avatar, int price, int sale) {
//        this.id = id;
//        this.name = name;
//        this.category = category;
//        this.avatar = avatar;
//        this.price = price;
//        this.sale = sale;
//    }
}
