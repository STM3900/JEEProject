package com.example.JEE_Liquors.dao;

import com.example.JEE_Liquors.Models.Command;
import com.example.JEE_Liquors.Models.Product;
import com.example.JEE_Liquors.beans.CartService;
import com.example.JEE_Liquors.dao.Exceptions.DAOException;
import com.example.JEE_Liquors.dao.Interfaces.ICommandDao;
import com.example.JEE_Liquors.dao.Interfaces.IProductDao;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.JEE_Liquors.dao.DAOUtils.SilentClosing;
import static com.example.JEE_Liquors.dao.DAOUtils.initialisationPreparedStatement;

public class CommandDAO implements ICommandDao {

    //#region Private Properties

    private final DAOFactory daoFactory;

    //#region Requests

    private static final String SQL_CREATE_NEW_COMMAND = "INSERT INTO `command` (`idUser`, `payementMethod`, `totalPrice`, `deliveryMethod`, `adress`) VALUES (?,?,?,?,?); ";
    private static final String SQL_ADD_COMMAND_PRODUCT = "INSERT INTO `commandproduct`(`idCommand`, `idProduct`) VALUES (?,?); ";
    private static final String SQL_GET_LAST_COMMAND = "select idCommand from command ORDER BY idCommand DESC LIMIT 1";

    private static  final String SQL_GET_ALL_COMMANDS_USER = "select * from command where idUser = ?";
    private static  final String SQL_GET_COMMAND_PRODUCT_USER = "select * from commandproduct where idCommand = ?";

    private static final String SQL_DELETE_COMMAND_PRODUCT = "DELETE FROM `commandproduct` WHERE idCommand = ?";
    private static final String SQL_DELETE_COMMAND = "DELETE FROM `command` WHERE idCommand = ?";

    private static final String SQL_PAY_COMMAND = "UPDATE `command` SET `payementMethod` = ? WHERE `idCommand` = ?";
    //#endregion

    //#endregion

    //#region Constructor

    /**
     * Constructor
     * @param daoFactory daoFactory
     */
    public CommandDAO(DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }

    //#endregion

    //#region Public Methods

    @Override
    public ArrayList<Command> AllCommandsOfUser(HttpServletRequest request) {

        //TODO GET REAL VALUES

        //TODO REMOVE TEST PURPOSE
        int userId = 1;

        //Initialize variables
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Command> commands = new ArrayList<Command>();

        try {
            //Get connection
            connection = daoFactory.getConnection();
            preparedStatement = initialisationPreparedStatement(connection, SQL_GET_ALL_COMMANDS_USER, false,userId);
            resultSet = preparedStatement.executeQuery();

            //Read result if exists
            while(resultSet.next()) {
                Command cmd = new Command(resultSet.getInt(1),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getString(5),
                        resultSet.getString(6));


                ArrayList<Integer> productsIds = new ArrayList<Integer>();
                ResultSet productsIdsResult = null;

                preparedStatement = initialisationPreparedStatement(connection, SQL_GET_COMMAND_PRODUCT_USER, false,cmd.getCommandId());
                productsIdsResult = preparedStatement.executeQuery();
                while(productsIdsResult.next()){
                    Product product =  daoFactory.getProductDao().DataProduct(request,productsIdsResult.getInt("idProduct"));
                    cmd.addProduct(product);
                }
                commands.add(cmd);
            }
        } catch (SQLException e){
            throw new DAOException(e);
        } finally {
            SilentClosing(resultSet, preparedStatement, connection);
        }
        return commands;
    }

    @Override
    public void NewCommand(HttpServletRequest request) {

        Object cart = request.getAttribute("cart");
        int userId = (int)request.getSession().getAttribute("idUserChartreuse");
        String payementMethod = "";
        String deliveryMethod = (String)request.getAttribute("deliveryMethod");
        String adress = (String)request.getAttribute("userAdress");
        Double totalPrice = CartService.getTotalPrice(request.getSession());
        List<Object> cartAsList = null;
        ArrayList<Integer> ids = new ArrayList<Integer>() ;
        cartAsList = Arrays.asList((Object[])cart);
        for (Object obj:cartAsList) {
            ids.add(((Product)obj).getIdProduct());
        }


        //SQL_ADD_COMMAND_PRODUCT
        //Initialize variables
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Product product = null;

        try {
            //Get connection
            connection = daoFactory.getConnection();
            preparedStatement = initialisationPreparedStatement(connection, SQL_CREATE_NEW_COMMAND, false,userId,payementMethod,totalPrice,deliveryMethod,adress);
            preparedStatement.executeUpdate();

            //Get Product created
            preparedStatement = initialisationPreparedStatement(connection, SQL_GET_LAST_COMMAND, false);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            for (int id: ids) {
                preparedStatement = initialisationPreparedStatement(connection, SQL_ADD_COMMAND_PRODUCT, false,resultSet.getInt(1),id);
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e){
            throw new DAOException(e);
        } finally {
            SilentClosing(preparedStatement, connection);
        }
    }

    @Override
    public void DeleteCommand(HttpServletRequest request) {

        //TODO GET THE REAL VALUE

        //TODO REMOVE TEST PURPOSE
        int commandId = 1;

        //SQL_ADD_COMMAND_PRODUCT
        //Initialize variables
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            //Get connection
            connection = daoFactory.getConnection();
            preparedStatement = initialisationPreparedStatement(connection, SQL_DELETE_COMMAND_PRODUCT, false,commandId);
            preparedStatement.executeUpdate();

            preparedStatement = initialisationPreparedStatement(connection, SQL_DELETE_COMMAND, false,commandId);
            preparedStatement.executeUpdate();

        } catch (SQLException e){
            throw new DAOException(e);
        } finally {
            SilentClosing(preparedStatement, connection);
        }
    }


    @Override
    public void PayCommand(HttpServletRequest request) {

        //TODO GET THE REAL VALUE

        //TODO REMOVE TEST PURPOSE
        int commandId = 2;
        String payementMethod = "CB";

        //SQL_ADD_COMMAND_PRODUCT
        //Initialize variables
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            //Get connection
            connection = daoFactory.getConnection();
            preparedStatement = initialisationPreparedStatement(connection, SQL_PAY_COMMAND, false,payementMethod, commandId);
            preparedStatement.executeUpdate();

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
