package com.example.JEE_Liquors.dao;

import com.example.JEE_Liquors.dao.Exceptions.DAOConfigurationException;
import com.example.JEE_Liquors.dao.Interfaces.IProductDao;
import com.example.JEE_Liquors.dao.Interfaces.IUserDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFactory {

    //#region Private Properties

    private String url;
    private String username;
    private String password;

    //#endregion

    //#region Constructor

    /**
     * Constructor
     * @param url url
     * @param username username
     * @param password password
     */
    DAOFactory( String url, String username, String password ) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    //#endregion

    /**
     * Load jdbc driver and establish connection
     * @return instance
     */
    public static DAOFactory getInstance() {
        try {
            Class.forName(DAOProperties.name);
        } catch (ClassNotFoundException e) {
            throw new DAOConfigurationException( "Can't find driver jdbc in classpath.", e );
        }
        return new DAOFactory(DAOProperties.path, DAOProperties.user, DAOProperties.password);
    }

    /**
     * Get Connection
     * @return connection
     * @throws SQLException sqlException
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * Get DAO User
     * @return daoFactory
     */
    public IUserDao getUserDao() {
        return new UserDao( this );
    }

    /**
     * Get DAO Product
     * @return daoFactory
     */
    public IProductDao getProductDao() {
        return new ProductDao( this );
    }
}
