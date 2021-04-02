package com.example.JEE_Liquors.Models;

import java.util.ArrayList;

public class Command {

    //#region Private Properties

    private int commandId;
    private String paymentMethod;
    private Double totalPrice;
    private String deliveryMethod;
    private String address;
    private ArrayList<Product> products = new ArrayList<>();

    //#endregion

    //#region Constructors

    /**
     * Constructor (empty)
     */
    public Command(){}

    /**
     * Constructor
     * @param _commandId id command
     */
    public Command(int _commandId){
        commandId = _commandId;
    }

    /**
     * Constructor
     * @param _commandId command id
     * @param _totalPrice total price
     * @param _deliveryMethod delivery method
     * @param _address address
     */
    public Command(int _commandId,Double _totalPrice,String _deliveryMethod,String _address){
        commandId = _commandId;
        totalPrice =_totalPrice;
        deliveryMethod = _deliveryMethod;
        address = _address;
    }

    /**
     * Constructor
     * @param _commandId command id
     * @param _paymentMethod payment method
     * @param _totalPrice total price
     * @param _deliveryMethod delivery method
     * @param _address address
     */
    public Command(int _commandId,String _paymentMethod, Double _totalPrice,String _deliveryMethod,String _address){
        commandId = _commandId;
        paymentMethod = _paymentMethod;
        totalPrice =_totalPrice;
        deliveryMethod = _deliveryMethod;
        address = _address;
    }

    /**
     * Constructor
     * @param _products product id list
     */
    public Command(ArrayList<Product> _products){
        products = _products;
    }

    //#endregion

    //#region Getter & Setter

    public ArrayList<Product> getProducts(){
        return products;
    }

    public void addProduct(Product _product){
        products.add(_product);
    }

    public int getCommandId(){
        return  commandId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public String getAddress() {
        return address;
    }

    //#endregion
}
