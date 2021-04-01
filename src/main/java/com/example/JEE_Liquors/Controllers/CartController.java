package com.example.JEE_Liquors.Controllers;

import com.example.JEE_Liquors.Models.Product;
import com.example.JEE_Liquors.beans.SessionManager;
import com.example.JEE_Liquors.dao.DAOFactory;
import com.example.JEE_Liquors.dao.Interfaces.IProductDao;
import com.example.JEE_Liquors.dao.Interfaces.IUserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.JEE_Liquors.beans.CartService.getTotalPrice;
import static com.example.JEE_Liquors.dao.DAOProperties.CONF_DAO_FACTORY;

@WebServlet(name = "CartController", value = "/Cart")
public class CartController extends HttpServlet {

    //#region Private Properties

    private SessionManager sessionManager;

    //endregion

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //TODO : test to remove
        /*sessionManager = new SessionManager(request.getSession());
        List<Product> products = new ArrayList<>();
        products.add(new Product(3, "Genepi", 23.5, "https://s1.qwant.com/thumbr/0x380/b/a/c2f8f59fbfae7d6337cac7710bb58fcc3362b6c8f6f1fdd6db416664563296/m54251.jpg?u=https%3A%2F%2Fwww.whisky.fr%2Fmedia%2Fcatalog%2Fproduct%2Fcache%2F4%2Fimage%2F9df78eab33525d08d6e5fb8d27136e95%2Fm%2F5%2Fm54251.jpg&q=0&b=1&p=0&a=1", new Timestamp(new Date().getTime()), 1.00));
        products.add(new Product(4, "Absinthe", 42.25, "https://s1.qwant.com/thumbr/0x380/2/5/9eeb0002d97c5e84398eb866a1283726fcaaafce02b02957c5d32a48d6dc26/absinthe-absente-70cl_bois-0007-2_bis1.jpg?u=https%3A%2F%2Fwww.planete-sfactory.com%2Fmedia%2Fcatalog%2Fproduct%2Fcache%2F3%2Fimage%2F1100x%2F9df78eab33525d08d6e5fb8d27136e95%2Fa%2Fb%2Fabsinthe-absente-70cl_bois-0007-2_bis1.jpg&q=0&b=1&p=0&a=1", new Timestamp(new Date().getTime()), 1.00));
        sessionManager.CreateSessionCart(products);*/
        //

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
            sessionManager = new SessionManager(request.getSession());
            sessionManager.RemoveProductSessionCart(Integer.parseInt(request.getParameter("idToDelete")));
        }

        doGet(request, response);
    }
}
