package com.example.JEE_Liquors.Controllers;

import com.example.JEE_Liquors.Models.Product;
import com.example.JEE_Liquors.dao.Interfaces.IProductDao;
import com.example.JEE_Liquors.dao.DAOFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

import static com.example.JEE_Liquors.dao.DAOProperties.CONF_DAO_FACTORY;

@WebServlet(name = "HomeController", value = {"/Home", "/", ""})
public class HomeController extends HttpServlet {

    //#region Private Properties

    private IProductDao productDao;

    //#endregion

    //#region Constructors

    public HomeController(){
        super();
    }

    public void init() {
        this.productDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getProductDao();
    }

    //#endregion

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<Product> products = productDao.AllProducts(request);
        if(products != null)
        {
            request.setAttribute("products", products);
        }
        else{
            request.setAttribute("error", "error get product");
        }

        //TODO Faire la variable de session pour check si l'user est log ou pas

        getServletContext().getRequestDispatcher("/WEB-INF/Home.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // productDao.InsertProduct(request);
        ArrayList<Product> products = productDao.AllProducts(request);
        if(products != null)
        {
            request.setAttribute("products", products);
        }
        else{
            request.setAttribute("error", "error get product");
        }

        doGet(request,response);
    }
}
