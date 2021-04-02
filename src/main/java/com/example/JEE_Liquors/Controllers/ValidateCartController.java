package com.example.JEE_Liquors.Controllers;

import com.example.JEE_Liquors.Models.Command;
import com.example.JEE_Liquors.beans.SessionManager;
import com.example.JEE_Liquors.dao.DAOFactory;
import com.example.JEE_Liquors.dao.Interfaces.ICommandDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static com.example.JEE_Liquors.beans.CartService.getTotalPrice;
import static com.example.JEE_Liquors.dao.DAOProperties.CONF_DAO_FACTORY;

@WebServlet(name = "ValidateCartController", value = "/ValidateCart")
public class ValidateCartController extends HttpServlet {

    //#region Private Properties

    private ICommandDao commandDao;

    //#endregion

    //#region Constructor

    public ValidateCartController() { super(); }

    public void init() {
        this.commandDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getCommandDao();
    }

    //#endregion

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/WEB-INF/ValidateCart.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("totalPrice", getTotalPrice(request.getSession()));
        Command command = commandDao.NewCommand(request);
        //Command ok
        if(command !=null){
            //Add command to session
            SessionManager sessionManager = new SessionManager(request.getSession());
            sessionManager.CreateSessionCommand(command);
            //Delete session cart
            sessionManager.DeleteSessionCart();
        }
        else{
            request.setAttribute("error", "Une erreur est survenu lors de l'enregistrement de la commande.");
        }

        doGet(request, response);
    }
}
