package com.example.cashregisterapplication;

import java.util.Date;

public class productModel {
    String name;
    int quantity;
    double price;

    Date pur;
    public productModel(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public productModel(String name, int quantity, double price, Date pur) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.pur = pur;
    }


}
