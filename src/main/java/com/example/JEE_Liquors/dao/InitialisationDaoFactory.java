package com.example.JEE_Liquors.dao;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitialisationDaoFactory implements ServletContextListener {

    private static final String ATT_DAO_FACTORY = "daofactory";

    @Override
    public void contextInitialized( ServletContextEvent event ) {
        ServletContext servletContext = event.getServletContext();

        servletContext.setAttribute( ATT_DAO_FACTORY, DAOFactory.getInstance() );
    }

    @Override
    public void contextDestroyed( ServletContextEvent event ) {
        // Nothing
    }
}
