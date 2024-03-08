package com.example.shoppingapp.models;

public class ItemModel
{
    private int id;
    private String itemName;
    private int stocks;
    private double price;
    private int image;

    public ItemModel(int id, String itemName, int stocks, double price, int image)
    {
        this.id = id;
        this.itemName = itemName;
        this.stocks = stocks;
        this.price = price;
        this.image = image;
    }

    public int getId()
    {
        return id;
    }

    public String getItemName()
    {
        return itemName;
    }

    public int getStocks()
    {
        return stocks;
    }

    public double getPrice()
    {
        return price;
    }

    public int getImage()
    {
        return image;
    }
}
