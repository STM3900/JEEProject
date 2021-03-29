package com.example.JEE_Liquors.dao;

public class DAOException extends RuntimeException {

    private static final long serialVersionUID = -6766320787209749020L;

    public DAOException( String message ) {
        super( message );
    }

    public DAOException(String message, Throwable cause) {
        super( message, cause );
    }

    public DAOException( Throwable cause ) {
        super( cause );
    }
}
