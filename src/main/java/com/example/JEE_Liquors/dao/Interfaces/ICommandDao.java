package com.example.JEE_Liquors.dao.Interfaces;

import com.example.JEE_Liquors.Models.Command;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public interface ICommandDao {


    /**
     * Convert database result in usable product
     * @param request request
     * @return products
     */
    ArrayList<Command>  AllCommandsOfUser(HttpServletRequest request);

    /**
     * Insert object in database
     * @param request request
     * @return Command created
     */
    Command NewCommand(HttpServletRequest request);

     /**
     * Insert object in database
     * @param request request
     */
     void DeleteCommand(HttpServletRequest request);

    /**
     * Insert object in database
     * @param request request
     * @return Command edited
     */
    Command PayCommand(HttpServletRequest request);
}
