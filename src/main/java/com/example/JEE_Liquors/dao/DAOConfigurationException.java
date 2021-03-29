package com.example.JEE_Liquors.dao;

public class DAOConfigurationException extends RuntimeException {

    private static final long serialVersionUID = 1047828793942551100L;

    public DAOConfigurationException( String message ) {
        super( message );
    }

    public DAOConfigurationException( String message, Throwable cause ) {
        super( message, cause );
    }

    public DAOConfigurationException( Throwable cause ) {
        super( cause );
    }
}
