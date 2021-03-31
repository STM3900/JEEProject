package com.example.JEE_Liquors.dao;

import java.sql.*;

public class DAOUtils {

    //#region Close Connection

    /**
     * Silent closing ResultSet
     * @param resultSet resultSet
     */
    public static void SilentClosing( ResultSet resultSet ) {
        if ( resultSet != null ) {
            try {
                resultSet.close();
            } catch ( SQLException e ) {
                System.out.println( "Failed to close ResultSet : " + e.getMessage() );
            }
        }
    }

    /**
     * Silent closing Statement
     * @param statement Statement
     */
    public static void SilentClosing( Statement statement ) {
        if ( statement != null ) {
            try {
                statement.close();
            } catch ( SQLException e ) {
                System.out.println( "FAiled to close Statement : " + e.getMessage() );
            }
        }
    }

    /**
     * Silent closing Connection
     * @param connection connection
     */
    public static void SilentClosing( Connection connection ) {
        if ( connection != null ) {
            try {
                connection.close();
            } catch ( SQLException e ) {
                System.out.println( "Failed to close connection : " + e.getMessage() );
            }
        }
    }

    /**
     * Silent closing Statement then Connection
     * @param statement statement
     * @param connection connection
     */
    public static void SilentClosing( Statement statement, Connection connection ) {
        SilentClosing( statement );
        SilentClosing( connection );
    }

    /**
     * Silent closing ResultSet, Statement, Connection
     * @param resultSet ResultSet
     * @param statement Statement
     * @param connection Connection
     */
    public static void SilentClosing( ResultSet resultSet, Statement statement, Connection connection ) {
        SilentClosing( resultSet );
        SilentClosing(statement, connection);
    }

    //#endregion

    /**
     * Prepare statement
     * @param connection connection
     * @param sql sql request
     * @param returnGeneratedKeys generate key
     * @param objets parameters
     * @return Prepare statement
     * @throws SQLException sql exception
     */
    public static PreparedStatement initialisationPreparedStatement(Connection connection, String sql, boolean returnGeneratedKeys, Object... objets ) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement( sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS );
        for ( int i = 0; i < objets.length; i++ ) {
            preparedStatement.setObject( i + 1, objets[i] );
        }
        return preparedStatement;
    }
}
