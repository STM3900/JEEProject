package com.example.JEE_Liquors.beans;

import com.example.JEE_Liquors.Models.Roles;
import com.example.JEE_Liquors.Models.User;
import com.example.JEE_Liquors.dao.DAOFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;

import static com.example.JEE_Liquors.beans.DAOUtils.SilentClosing;

public class UserDao implements  IUserDao{

    //#region Private Properties

    private DAOFactory daoFactory;

    //#region Requests

    private static final String SQL_SELECT_PAR_LOGIN_PWD = "SELECT * FROM user WHERE login = ? AND pwd = ?";

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
        String pwd = request.getParameter("pwd");

        //Initialize variables
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            //Get connection
            connection = daoFactory.getConnection();
            preparedStatement = initialisationPreparedStatement(connection, SQL_SELECT_PAR_LOGIN_PWD, false, login, pwd);
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

    public static PreparedStatement initialisationPreparedStatement( Connection connection, String sql, boolean returnGeneratedKeys, Object... objets ) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement( sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS );
        for ( int i = 0; i < objets.length; i++ ) {
            preparedStatement.setObject( i + 1, objets[i] );
        }
        return preparedStatement;
    }

    /**
     * Convert resultSet into user
     * @param resultSet resultSet
     * @return User
     * @throws SQLException sqlException
     */
    private static User map( ResultSet resultSet ) throws SQLException {
        User user = new User(resultSet.getInt("idUser"),resultSet.getString("firstName"),resultSet.getString("lastName"),resultSet.getString("login"), resultSet.getString("password"), resultSet.getString("salt"), Roles.valueOf(resultSet.getString("role")));
        return user;
    }
}
