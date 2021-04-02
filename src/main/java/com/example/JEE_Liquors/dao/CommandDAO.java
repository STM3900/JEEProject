package com.example.JEE_Liquors.dao;

import com.example.JEE_Liquors.Models.Command;
import com.example.JEE_Liquors.Models.Product;
import com.example.JEE_Liquors.beans.CartService;
import com.example.JEE_Liquors.dao.Exceptions.DAOException;
import com.example.JEE_Liquors.dao.Interfaces.ICommandDao;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.JEE_Liquors.dao.DAOUtils.SilentClosing;
import static com.example.JEE_Liquors.dao.DAOUtils.initialisationPreparedStatement;

public class CommandDAO implements ICommandDao {

    //#region Private Properties

    private final DAOFactory daoFactory;

    //#region Requests

    private static final String SQL_CREATE_NEW_COMMAND = "INSERT INTO `command` (`idUser`, `paymentMethod`, `totalPrice`, `deliveryMethod`, `address`) VALUES (?,?,?,?,?); ";
    private static final String SQL_ADD_COMMAND_PRODUCT = "INSERT INTO `commandproduct`(`idCommand`, `idProduct`) VALUES (?,?); ";
    private static final String SQL_GET_LAST_COMMAND = "select idCommand from command ORDER BY idCommand DESC LIMIT 1";

    private static  final String SQL_GET_ALL_COMMANDS_USER = "select * from command where idUser = ?";
    private static  final String SQL_GET_COMMAND_PRODUCT_USER = "select * from commandproduct where idCommand = ?";

    private static final String SQL_DELETE_COMMAND_PRODUCT = "DELETE FROM `commandproduct` WHERE idCommand = ?";
    private static final String SQL_DELETE_COMMAND = "DELETE FROM `command` WHERE idCommand = ?";

    private static final String SQL_PAY_COMMAND = "UPDATE `command` SET `paymentMethod` = ? WHERE `idCommand` = ?";
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
        ResultSet productsIdsResult = null;
        ArrayList<Command> commands = new ArrayList<>();

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

                preparedStatement = initialisationPreparedStatement(connection, SQL_GET_COMMAND_PRODUCT_USER, false,cmd.getCommandId());
                productsIdsResult = preparedStatement.executeQuery();
                while(productsIdsResult.next()){
                    request.setAttribute("idProduct", productsIdsResult.getInt("idProduct"));
                    Product product =  daoFactory.getProductDao().DataProduct(request);
                    cmd.addProduct(product);
                }
                commands.add(cmd);
            }
        } catch (SQLException e){
            throw new DAOException(e);
        } finally {
            SilentClosing(productsIdsResult);
            SilentClosing(resultSet, preparedStatement, connection);
        }
        return commands;
    }

    @Override
    public void NewCommand(HttpServletRequest request) {

        int userId = (int)request.getSession().getAttribute("idUserChartreuse");
        String paymentMethod = "";
        String deliveryMethod = request.getParameter("deliveryMethod");
        if(deliveryMethod == null)
            deliveryMethod = "Relais";
        String address = request.getParameter("userAddress");
        if(address == null)
            address = "17 rue de la republique";
        Double totalPrice = CartService.getTotalPrice(request.getSession());
        ArrayList<Integer> ids = new ArrayList<>() ;
        List<Product> products = (List<Product>) request.getSession().getAttribute("cartChartreuse");
        for (Object obj:products) {
            ids.add(((Product)obj).getIdProduct());
        }


        //SQL_ADD_COMMAND_PRODUCT
        //Initialize variables
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            //Get connection
            connection = daoFactory.getConnection();
            preparedStatement = initialisationPreparedStatement(connection, SQL_CREATE_NEW_COMMAND, false,userId,paymentMethod,totalPrice,deliveryMethod,address);
            preparedStatement.executeUpdate();

            //Get Command created
            preparedStatement = initialisationPreparedStatement(connection, SQL_GET_LAST_COMMAND, false);
            preparedStatement.executeQuery().next();
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
        String paymentMethod = "CB";

        //SQL_ADD_COMMAND_PRODUCT
        //Initialize variables
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //Get connection
            connection = daoFactory.getConnection();
            preparedStatement = initialisationPreparedStatement(connection, SQL_PAY_COMMAND, false,paymentMethod, commandId);
            preparedStatement.executeUpdate();

        } catch (SQLException e){
            throw new DAOException(e);
        } finally {
            SilentClosing(preparedStatement, connection);
        }
    }
    //#endregion

}
