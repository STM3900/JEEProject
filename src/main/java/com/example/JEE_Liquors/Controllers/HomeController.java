package com.example.JEE_Liquors.Controllers;

import com.example.JEE_Liquors.Models.Product;
import com.example.JEE_Liquors.Models.User;
import com.example.JEE_Liquors.dao.Interfaces.IProductDao;
import com.example.JEE_Liquors.dao.Interfaces.IUserDao;
import com.example.JEE_Liquors.dao.DAOFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "HomeController", value = {"/Home", "/"})
public class HomeController extends HttpServlet {

    //#region Private Properties

    public static final String CONF_DAO_FACTORY = "daofactory";
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

        getServletContext().getRequestDispatcher("/WEB-INF/Home.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Product product = productDao.DataProduct(request);
        if(product != null)
        {
            request.setAttribute("product", product);
        }
        else{
            request.setAttribute("error", "error get product");
        }

        doGet(request,response);
    }
}
