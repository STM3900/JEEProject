package com.example.JEE_Liquors.Controllers;

import com.example.JEE_Liquors.Models.User;
import com.example.JEE_Liquors.dao.IUserDao;
import com.example.JEE_Liquors.dao.DAOFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "HomeController", value = "/Home")
public class HomeController extends HttpServlet {

    public static final String CONF_DAO_FACTORY = "daofactory";
    private IUserDao userDao;
    public HttpSession session;

    public HomeController(){
        super();
    }

    public void init() {
        this.userDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUserDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/WEB-INF/Home.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userDao.DataUser(request);
        if(user != null)
            request.setAttribute("user", user);

        doGet(request,response);
    }
}
