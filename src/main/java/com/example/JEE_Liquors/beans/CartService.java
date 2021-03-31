package com.example.JEE_Liquors.beans;

import com.example.JEE_Liquors.Models.Product;

import javax.servlet.http.HttpSession;
import java.util.List;

public class CartService {

    /**
     * Return total price of cart
     * @param session http session
     * @return total price
     */
    public static double getTotalPrice(HttpSession session){
        List<Product> cartProducts = (List<Product>)session.getAttribute("cartChartreuse");
        double total = 0;

        for (Product product: cartProducts) {
            total += product.getPrice();
        }
        return total;
    }
}
