package com.example.JEE_Liquors.Models;

import java.sql.Timestamp;

public class Product {

    //#region Private Properties

    private Integer idProduct;
    private String name;
    private double price;
    private String image;
    private Timestamp limitDate;
    private double quantity;

    //#endregion

    //#region Constructors

    /**
     * Constructor (empty)
     */
    public Product(){}

    /**
     * Constructor
     * @param idProduct ID Product
     * @param name Name Product
     * @param price Price
     * @param image Image
     * @param limitDate Date Limit
     * @param quantity Quantity
     */
    public Product(Integer idProduct, String name, double price, String image, Timestamp limitDate, double quantity) {
        super();
        this.idProduct = idProduct;
        this.name = name;
        this.price = price;
        this.image = image;
        this.limitDate = limitDate;
        this.quantity = quantity;
    }

    /**
     * Constructor
     * @param name Name Product
     * @param price Price
     * @param image Image
     * @param limitDate Date Limit
     * @param quantity Quantity
     */
    public Product(String name, double price, String image, Timestamp limitDate, double quantity) {
        super();
        this.name = name;
        this.price = price;
        this.image = image;
        this.limitDate = limitDate;
        this.quantity = quantity;
    }

    //#endregion

    //#region Getter & Setter

    /**
     * @return the idProduct
     */
    public Integer getIdProduct() {
        return idProduct;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @return the limit date
     */
    public Timestamp getLimitDate() {
        return limitDate;
    }

    /**
     * @return the quantity
     */
    public double getQuantity() {
        return quantity;
    }

    //#endregion

}
