package com.example.JEE_Liquors.dao;

import com.example.JEE_Liquors.Models.Product;
import com.example.JEE_Liquors.dao.Exceptions.DAOException;
import com.example.JEE_Liquors.dao.Interfaces.IProductDao;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static com.example.JEE_Liquors.dao.DAOUtils.SilentClosing;
import static com.example.JEE_Liquors.dao.DAOUtils.initialisationPreparedStatement;

public class ProductDao implements IProductDao {

    //#region Private Properties

    private final DAOFactory daoFactory;

    //#region Requests

    private static final String SQL_SELECT_PRODUCT_BY_ID = "SELECT * FROM product WHERE idProduct = ?";

    private static final String SQL_SELECT_PRODUCTS = "SELECT * FROM product";

    private static final String SQL_SELECT_LAST_PRODUCT_ADDED = "select * from product ORDER BY idProduct DESC LIMIT 1";

    private static final String SQL_ADD_PRODUCT ="INSERT INTO `product` (`image`, `limitDate`, `name`, `quantity`, `price`) VALUES (?, ?, ?, ?, ?);";

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

    @Override
    public Product InsertProduct(HttpServletRequest request) {

        String img = request.getParameter("image");
        Date date = Date.valueOf(request.getParameter("limitDate"));
        Timestamp limitDate = new Timestamp(date.getTime());
        String name = request.getParameter("name");
        Double quantity = Double.parseDouble(request.getParameter("quantity"));
        Double price = Double.parseDouble(request.getParameter("price"));

        //Initialize variables
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Product product = null;

        try {
            //Get connection
            connection = daoFactory.getConnection();
            preparedStatement = initialisationPreparedStatement(connection, SQL_ADD_PRODUCT, false,img,limitDate,name,quantity,price);
            int t = preparedStatement.executeUpdate();

            //Get Product created
            preparedStatement = initialisationPreparedStatement(connection, SQL_SELECT_LAST_PRODUCT_ADDED, false);
            resultSet = preparedStatement.executeQuery();

            //Read result if exists
            if(resultSet.next()) {
                product = map(resultSet);
            }

        } catch (SQLException e){
            throw new DAOException(e);
        } finally {
            SilentClosing(resultSet,preparedStatement, connection);
        }
        return product;
    }

    //#endregion

    /**
     * Convert resultSet into product
     * @param resultSet resultSet
     * @return Product
     * @throws SQLException sqlException
     */
    private static Product map( ResultSet resultSet ) throws SQLException {
        return new Product(resultSet.getInt("idProduct"),
                resultSet.getString("name"),
                resultSet.getDouble("price"),
                resultSet.getString("image"),
                resultSet.getTimestamp("limitDate"),
                resultSet.getDouble("quantity"));
    }

}
