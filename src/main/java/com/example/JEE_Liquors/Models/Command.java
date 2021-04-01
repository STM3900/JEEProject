package com.example.JEE_Liquors.Models;

import java.util.ArrayList;

public class Command {

    private int commandId;
    private String payementMethod;
    private Double totalPrice;
    private String deliveryMethod;
    private String adress;
    private ArrayList<Product> products = new ArrayList<>();

    public Command(){}

    public Command(int _commandId){
        commandId = _commandId;
    }

    public Command(int _commandId,String _payementMethod, Double _totalPrice,String _deliveryMethod,String _adress){
        commandId = _commandId;
        payementMethod = _payementMethod;
        totalPrice =_totalPrice;
        deliveryMethod = _deliveryMethod;
        adress = _adress;
    }

    public Command(ArrayList<Product> _products){
        products = _products;
    }

    public ArrayList<Product> getProducts(){
        return products;
    }

    public void addProduct(Product _product){
        products.add(_product);
    }

    public void setProducts(ArrayList<Product> _products){
        products = _products;
    }

    public int getCommandId(){
        return  commandId;
    }
}
