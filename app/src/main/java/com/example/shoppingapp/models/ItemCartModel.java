package com.example.shoppingapp.models;

public class ItemCartModel
{
    private int id;
    private String name;
    private int qty;
    private double price;
    private int image;

    public int getImage()
    {
        return image;
    }

    public ItemCartModel(int id, String name, int qty, double price)
    {
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.image = image;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public int getQty()
    {
        return qty;
    }

    public double getPrice()
    {
        return price;
    }
}
