package com.example.JEE_Liquors.beans;

import com.example.JEE_Liquors.Models.Product;
import com.example.JEE_Liquors.Models.User;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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

    //#region User

    /**
     * Create Session data for user
     * @param user user
     */
    public void CreateSessionUser(User user) {
        session.setAttribute(idUserLabel, user.getIdUser());
        session.setAttribute(roleUserLabel, user.getRole());
        System.out.println("id user : " + session.getAttribute(idUserLabel) + " role : " + session.getAttribute(roleUserLabel));
    }

    //#endregion

    //#region Cart

    /**
     * Add product to Session data for cart
     * Create an param in session if not exists
     */
    public void AddProductSessionCart(Product product) {
        List<Product> products = (session.getAttribute(cartLabel) != null) ? (List<Product>) session.getAttribute(cartLabel) : new ArrayList<Product>() {};
        products.add(product);
        session.setAttribute(cartLabel, products);
    }

    /**
     * Remove product from Session data for cart
     * @param id id of product
     */
    public void RemoveProductSessionCart(Integer id){
        List<Product> allproducts = (List<Product>) session.getAttribute(cartLabel);
        for (int i = 0; i < allproducts.size()-1; i++) {
            if(allproducts.get(i).getIdProduct() == id) {
                allproducts.remove(i);
                break;
            }
        }
    }

    /**
     * Remove Session data for cart
     */
    public void DeleteSessionCart() {
        session.removeAttribute(cartLabel);
    }

    //#endregion

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
