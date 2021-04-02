package com.example.JEE_Liquors.Controllers;

import com.example.JEE_Liquors.Models.Command;
import com.example.JEE_Liquors.beans.SessionManager;
import com.example.JEE_Liquors.dao.DAOFactory;
import com.example.JEE_Liquors.dao.Interfaces.ICommandDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

import static com.example.JEE_Liquors.dao.DAOProperties.CONF_DAO_FACTORY;

@WebServlet(name = "CommandsController", value = "/Commands")
public class CommandsController extends HttpServlet {

    //#region Private Properties

    private ICommandDao commandDao;

    //#endregion

    //#region Constructor

    public CommandsController() { super(); }

    public void init() {
        this.commandDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getCommandDao();
    }

    //#endregion

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Delete command if exists
        SessionManager sessionManager = new SessionManager(request.getSession());
        sessionManager.DeleteSessionCommand();

        //Get all commands of user
        List<Command> commands = commandDao.AllCommandsOfUser(request);
        request.setAttribute("commands", commands);

        getServletContext().getRequestDispatcher("/WEB-INF/Commands.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int commandId = Integer.parseInt(request.getParameter("idCommand"));

        //Validate command
        if(request.getParameter("validateButton") != null) {
            SessionManager sessionManager = new SessionManager(request.getSession());
            sessionManager.CreateSessionCommand((Command) request.getAttribute("commandToPay"));

            getServletContext().getRequestDispatcher("/PayUp").forward(request, response);
        }
        //Remove from database
        else if(request.getParameter("deleteButton") != null){
            commandDao.DeleteCommand(commandId);

            request.setAttribute("deleted", "La commande " + commandId + " a bien été supprimée !");
        }

        doGet(request, response);
    }
}
