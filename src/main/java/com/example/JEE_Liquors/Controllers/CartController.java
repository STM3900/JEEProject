package com.example.JEE_Liquors.Controllers;

import com.example.JEE_Liquors.beans.SessionManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static com.example.JEE_Liquors.beans.CartService.getTotalPrice;

@WebServlet(name = "CartController", value = "/Cart")
public class CartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Add total price of cart
        request.setAttribute("total", getTotalPrice(request.getSession()));

        getServletContext().getRequestDispatcher("/WEB-INF/Cart.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Validate command
        if(request.getParameter("validateButton") != null) {
            getServletContext().getRequestDispatcher("/ValidateCart").forward(request, response);
        }
        //Remove from cart
        else if(request.getParameter("deleteButton") != null){
            SessionManager sessionManager = new SessionManager(request.getSession());
            sessionManager.RemoveProductSessionCart(Integer.parseInt(request.getParameter("idToDelete")));
        }

        doGet(request, response);
    }
}
