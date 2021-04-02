package com.example.JEE_Liquors.Controllers;

import com.example.JEE_Liquors.Models.Product;
import com.example.JEE_Liquors.dao.DAOFactory;
import com.example.JEE_Liquors.dao.Interfaces.IProductDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static com.example.JEE_Liquors.dao.DAOProperties.CONF_DAO_FACTORY;

@WebServlet(name = "AddProductController", value = "/AddProduct")
public class AddProductController extends HttpServlet {

    //#region Private Properties

    private IProductDao productDao;

    //#endregion

    //#region Constructors

    public AddProductController(){
        super();
    }

    public void init() {
        this.productDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getProductDao();
    }

    //endregion

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/AddProduct").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Product product = productDao.InsertProduct(request);
        if(product != null){

            request.setAttribute("product", product);
        }
        else{
            request.setAttribute("error", "Login error");
        }
        doGet(request, response);
    }
}
