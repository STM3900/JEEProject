package com.example.JEE_Liquors.dao;

public class DAOProperties {

    //DATA Database
    public final static String path = "jdbc:mysql://localhost:3306/chartreuse?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
    public final static String user = "root";
    public final static String password = "";
    public final static String name = "com.mysql.cj.jdbc.Driver";

    public static final String CONF_DAO_FACTORY = "daofactory";

}
