package com.example.JEE_Liquors.beans;

import com.example.JEE_Liquors.Models.Product;
import com.example.JEE_Liquors.Models.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Manage Session and data
 */
public class SessionManager {

    //#region Private Properties

    private final String idUserLabel =  "idUserChartreuse";
    private final String roleUserLabel =  "roleUserChartreuse";
    private final String cartLabel =  "cartChartreuse";

    //#endregion

    //#region Public Methods

    /**
     * Create Session data for user
     * @param request request
     * @param user user
     */
    public void CreateSessionUser(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute(idUserLabel, user.getIdUser());
        session.setAttribute(roleUserLabel, user.getRole());
    }

    /**
     * Create Session data for cart
     * @param request request
     * @param products list products in cart
     */
    public void CreateSessionCart(HttpServletRequest request, List<Product> products) {
        HttpSession session = request.getSession();
        session.setAttribute(cartLabel, products);
    }

    /**
     * Destroy Session data
     * @param request request
     */
    public void DestroySession(HttpServletRequest request) {
        HttpSession session = request.getSession();

        session.removeAttribute(idUserLabel);
        session.removeAttribute(cartLabel);

        session.invalidate();
    }

    //#endregion

}
