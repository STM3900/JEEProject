package com.example.JEE_Liquors.beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOUtils {

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
}
