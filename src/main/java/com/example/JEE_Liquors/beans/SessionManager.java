package com.example.JEE_Liquors.beans;

import com.example.JEE_Liquors.Models.Product;
import com.example.JEE_Liquors.Models.User;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Manage Session and data
 */
public class SessionManager {

    //#region Private Properties

    private final static String idUserLabel =  "idUserChartreuse";
    private final static String roleUserLabel =  "roleUserChartreuse";
    private final static String cartLabel =  "cartChartreuse";
    private HttpSession session;

    //#endregion

    //#region Constructor

    /**
     * Constructor
     * @param session session http
     */
    public SessionManager(HttpSession session){
        this.session = session;
    }

    //#endregion

    //#region Public Methods

    /**
     * Create Session data for user
     * @param user user
     */
    public void CreateSessionUser(User user) {
        session.setAttribute(idUserLabel, user.getIdUser());
        session.setAttribute(roleUserLabel, user.getRole());
        System.out.println("id user : " + session.getAttribute(idUserLabel) + " role : " + session.getAttribute(roleUserLabel));
    }

    /**
     * Create Session data for cart
     * @param products list products in cart
     */
    public void CreateSessionCart(List<Product> products) {
        session.setAttribute(cartLabel, products);
    }

    /**
     * Add product to Session data for cart
     */
    public void AddProductSessionCart(Product product) {
        List<Product> products = (List<Product>) session.getAttribute(cartLabel);
        products.add(product);
        session.setAttribute(cartLabel, products);
    }

    /**
     * Add products to Session data for cart
     */
    public void AddProductSessionCart(List<Product> products) {
        List<Product> allproducts = (List<Product>) session.getAttribute(cartLabel);
        for (Product product : products) {
            allproducts.add(product);
        }
        session.setAttribute(cartLabel, products);
    }

    /**
     * Remove Session data for cart
     */
    public void DeleteSessionCart() {
        session.removeAttribute(cartLabel);
    }

    /**
     * Destroy Session data
     */
    public void DestroySession() {
        session.removeAttribute(idUserLabel);
        session.removeAttribute(cartLabel);
        session.invalidate();
    }

    //#endregion

}
