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
    public void InsertProduct(HttpServletRequest request) {

        //TODO UNCOMMENT AND SET THE PARAMETERS NAME AS THE SAME THAN THE FORM
        /*String img = request.getParameter("productImg");
        Date date = Date.valueOf(request.getParameter("productDate"));
        Timestamp limitDate = new Timestamp(date.getTime());
        String name = request.getParameter("productName");
        Double quantity = Double.parseDouble(request.getParameter("productQuantity"));
        Double price = Double.parseDouble(request.getParameter("productPrice"));*/

        //(`idProduct`, `image`, `limitDate`, `name`, `quantity`, `price`) VALUES (NULL, '', '2021-03-30 22:13:59', 'vodka', '1', '5.20')

        //TEST PURPOSE
        //TODO REMOVE
        String img = "";
        Timestamp limitDate = new Timestamp(Date.valueOf("2021-03-30").getTime());
        String name = "vodka";
        Double quantity = 1.0;
        Double price = 5.20;

        //Initialize variables
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Get connection
            connection = daoFactory.getConnection();
            preparedStatement = initialisationPreparedStatement(connection, SQL_ADD_PRODUCT, false,img,limitDate,name,quantity,price);
            int t = preparedStatement.executeUpdate();

        } catch (SQLException e){
            throw new DAOException(e);
        } finally {
            SilentClosing(preparedStatement, connection);
        }
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
