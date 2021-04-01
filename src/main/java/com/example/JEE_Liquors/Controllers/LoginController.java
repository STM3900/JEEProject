package com.example.JEE_Liquors.Controllers;

import com.example.JEE_Liquors.Models.User;
import com.example.JEE_Liquors.beans.SessionManager;
import com.example.JEE_Liquors.dao.DAOFactory;
import com.example.JEE_Liquors.dao.Interfaces.IUserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static com.example.JEE_Liquors.dao.DAOProperties.CONF_DAO_FACTORY;

@WebServlet(name = "LoginController", value = {"/Login", "/SignIn"})
public class LoginController extends HttpServlet {

    //#region Private Properties

    private IUserDao userDao;
    private SessionManager sessionManager;

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

        HttpSession session = request.getSession();

        //Login
        if(request.getParameter("loginButton") != null)
        {
            User user = userDao.DataUser(request);
            if(user != null){
                sessionManager = new SessionManager(session);
                sessionManager.CreateSessionUser(user);
                getServletContext().getRequestDispatcher("/Home").forward(request,response);
            }
            else{
                request.setAttribute("error", "Login error");
            }
        }
        //Sign In
        else{
            User user = userDao.AddUser(request);
            if(user != null){
                sessionManager = new SessionManager(session);
                sessionManager.CreateSessionUser(user);
                getServletContext().getRequestDispatcher("/Home").forward(request,response);
            }
            else{
                request.setAttribute("error", "Sign in error");
            }
        }

        doGet(request,response);
    }
}
