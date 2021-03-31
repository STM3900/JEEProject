package com.example.JEE_Liquors.dao;

import com.example.JEE_Liquors.Models.Roles;
import com.example.JEE_Liquors.Models.User;
import com.example.JEE_Liquors.dao.Exceptions.DAOException;
import com.example.JEE_Liquors.dao.Interfaces.IUserDao;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;

import static com.example.JEE_Liquors.dao.DAOUtils.SilentClosing;
import static com.example.JEE_Liquors.dao.DAOUtils.initialisationPreparedStatement;

public class UserDao implements IUserDao {

    //#region Private Properties

    private final DAOFactory daoFactory;

    //#region Requests

    private static final String SQL_SELECT_USER_BY_LOGIN_PWD = "SELECT * FROM user WHERE login = ? AND password = ?";

    private static final String SQL_INSERT_USER = "INSERT INTO `user` (`firstName`, `lastName`, `login`, `password`, `role`, `salt`) VALUES (?, ?, ?, ?, '0', 'salt')";

    //#endregion

    //#endregion

    //#region Constructor

    /**
     * Constructor
     * @param daoFactory daoFactory
     */
    public UserDao( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    //#endregion

    //#region Public Methods

    @Override
    public User DataUser(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        //Initialize variables
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            //Get connection
            connection = daoFactory.getConnection();
            preparedStatement = initialisationPreparedStatement(connection, SQL_SELECT_USER_BY_LOGIN_PWD, false, login, password);
            resultSet = preparedStatement.executeQuery();

            //Read result if exists
            if(resultSet.next()) {
                user = map(resultSet);
            }
        } catch (SQLException e){
            throw new DAOException(e);
        } finally {
            SilentClosing(resultSet, preparedStatement, connection);
        }
        return user;
    }

    @Override
    public User AddUser(HttpServletRequest request) {

        //Get parameters
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        //Initialize variables
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Get connection
            connection = daoFactory.getConnection();
            preparedStatement = initialisationPreparedStatement(connection, SQL_INSERT_USER, false,firstName, lastName, login, password);
            int t = preparedStatement.executeUpdate();
            System.out.println("======> t : " + t);

        } catch (SQLException e){
            throw new DAOException(e);
        } finally {
            SilentClosing(preparedStatement, connection);
        }
        return DataUser(request);
    }

    //#endregion

    /**
     * Convert resultSet into user
     * @param resultSet resultSet
     * @return User
     * @throws SQLException sqlException
     */
    private static User map( ResultSet resultSet ) throws SQLException {
        return new User(resultSet.getInt("idUser"),
                                resultSet.getString("firstName"),
                                resultSet.getString("lastName"),
                                resultSet.getString("login"),
                                resultSet.getString("password"),
                                resultSet.getString("salt"),
                                Roles.values()[resultSet.getInt("role")]);
    }
}
