package com.example.JEE_Liquors.Controllers;

import com.example.JEE_Liquors.Models.User;
import com.example.JEE_Liquors.dao.DAOFactory;
import com.example.JEE_Liquors.dao.Interfaces.IUserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginController", value = {"/Login", "/SignIn", "/LogOut"})
public class LoginController extends HttpServlet {

    //#region Private Properties

    public static final String CONF_DAO_FACTORY = "daofactory";
    private IUserDao userDao;
    HttpSession session;

    //#endregion

    //#region Constructors

    public LoginController(){
        super();
    }

    public void init() {
        this.userDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUserDao();
    }

    //endregion

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/WEB-INF/Login.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = userDao.DataUser(request);
        if(user != null)
            request.setAttribute("user", user);
        else{
            request.setAttribute("error", "Login error");
        }

        doGet(request,response);
    }
}
