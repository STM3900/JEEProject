package com.example.JEE_Liquors.Controllers;

import com.example.JEE_Liquors.Models.Command;
import com.example.JEE_Liquors.beans.SessionManager;
import com.example.JEE_Liquors.dao.DAOFactory;
import com.example.JEE_Liquors.dao.Interfaces.ICommandDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static com.example.JEE_Liquors.dao.DAOProperties.CONF_DAO_FACTORY;

@WebServlet(name = "PayUpController", value = "/PayUp")
public class PayUpController extends HttpServlet {

    //#region Private Properties

    private ICommandDao commandDao;

    //#endregion

    //#region Constructor

    public PayUpController() { super(); }

    public void init() {
        this.commandDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getCommandDao();
    }

    //#endregion

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/WEB-INF/PayUp.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = commandDao.PayCommand(request);
        if(command != null){
            System.out.println("command created !");
            request.setAttribute("commandPayed", command);
            //delete session command
            SessionManager sessionManager = new SessionManager(request.getSession());
            sessionManager.DeleteSessionCommand();
        }
        else {
            System.out.println("command not created !");
        }

        doGet(request,response);
    }
}
