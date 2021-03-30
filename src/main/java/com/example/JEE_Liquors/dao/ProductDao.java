package com.example.JEE_Liquors.dao;

import com.example.JEE_Liquors.Models.Product;
import com.example.JEE_Liquors.dao.Exceptions.DAOException;
import com.example.JEE_Liquors.dao.Interfaces.IProductDao;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;

import static com.example.JEE_Liquors.dao.DAOUtils.SilentClosing;

public class ProductDao implements IProductDao {

    //#region Private Properties

    private final DAOFactory daoFactory;

    //#region Requests

    private static final String SQL_SELECT_PRODUCT_BY_ID = "SELECT * FROM product WHERE idProduct = ?";

    private static final String SQL_SELECT_PRODUCTS = "SELECT * FROM product";

    //#endregion

    //#endregion

    //#region Constructor

    /**
     * Constructor
     * @param daoFactory daoFactory
     */
    public ProductDao( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    //#endregion

    //#region Public Methods

    @Override
    public Product DataProduct(HttpServletRequest request) {
        String idProduct = request.getParameter("idProduct");

        //Initialize variables
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Product product = null;

        try {
            //Get connection
            connection = daoFactory.getConnection();
            preparedStatement = initialisationPreparedStatement(connection, SQL_SELECT_PRODUCT_BY_ID, false, idProduct);
            resultSet = preparedStatement.executeQuery();

            //Read result if exists
            if(resultSet.next()) {
                product = map(resultSet);
                System.out.println("===========> Product :");
                System.out.println(product.getName());
            }
        } catch (SQLException e){
            throw new DAOException(e);
        } finally {
            SilentClosing(resultSet, preparedStatement, connection);
        }
        return product;
    }

    public static PreparedStatement initialisationPreparedStatement( Connection connection, String sql, boolean returnGeneratedKeys, Object... objets ) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement( sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS );
        for ( int i = 0; i < objets.length; i++ ) {
            preparedStatement.setObject( i + 1, objets[i] );
        }
        return preparedStatement;
    }

    @Override
    public ArrayList<Product> AllProducts(HttpServletRequest request) {

        //Initialize variables
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Product> products = new ArrayList<Product>();

        try {
            //Get connection
            connection = daoFactory.getConnection();
            preparedStatement = initialisationPreparedStatement(connection, SQL_SELECT_PRODUCTS, false);
            resultSet = preparedStatement.executeQuery();

            //Read result if exists
            while(resultSet.next()) {
                Product pr = new Product(resultSet.getInt("idProduct"),
                    resultSet.getString("name"),
                    resultSet.getDouble("price"),
                    resultSet.getString("image"),
                    resultSet.getTimestamp("limitDate"),
                    resultSet.getDouble("quantity"));
                //Product pr =
                products.add(pr);
                System.out.println("===========> Product :");
                System.out.println(products.get(products.size()-1).getName());

            }
        } catch (SQLException e){
            throw new DAOException(e);
        } finally {
            SilentClosing(resultSet, preparedStatement, connection);
        }
        return products;
    }


    /**
     * Convert resultSet into user
     * @param resultSet resultSet
     * @return User
     * @throws SQLException sqlException
     */
    private static Product map( ResultSet resultSet ) throws SQLException {
        Product product = new Product(resultSet.getInt("idProduct"),
                resultSet.getString("name"),
                resultSet.getDouble("price"),
                resultSet.getString("image"),
                resultSet.getTimestamp("limitDate"),
                resultSet.getDouble("quantity"));
        return product;
    }


}
