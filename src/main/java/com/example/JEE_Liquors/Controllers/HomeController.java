package com.example.JEE_Liquors.Controllers;

import com.example.JEE_Liquors.Models.Product;
import com.example.JEE_Liquors.beans.SessionManager;
import com.example.JEE_Liquors.dao.Interfaces.IProductDao;
import com.example.JEE_Liquors.dao.DAOFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.JEE_Liquors.dao.DAOProperties.CONF_DAO_FACTORY;

@WebServlet(name = "HomeController", value = {"/Home", "/", ""})
public class HomeController extends HttpServlet {

    //#region Private Properties

    private IProductDao productDao;
    private ArrayList<Product> products;

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

        products = productDao.AllProducts(request);
        if(products != null)
        {
            request.setAttribute("products", products);
        }
        else{
            request.setAttribute("error", "error get product");
        }

        getServletContext().getRequestDispatcher("/WEB-INF/Home.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getParameter("addButton") != null){
            SessionManager sessionManager = new SessionManager(request.getSession());
            sessionManager.AddProductSessionCart(getProductById(Integer.parseInt(request.getParameter("idToAdd")), products));
        }
        if(products != null)
        {
            request.setAttribute("products", products);
        }
        else{
            request.setAttribute("error", "error get product");
        }

        doGet(request,response);
    }

    /**
     * Get product by id
     * @param id idProduct
     * @param products list of products
     * @return product corresponding
     */
    private static Product getProductById(Integer id, List<Product> products){
        for (Product product: products) {
            if (product.getIdProduct() == id)
                return product;
        }
        return null;
    }
}
