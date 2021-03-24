package com.example.JEE_Liquors.Models;

public class User {
    //#region Private Properties

    private Integer idUser;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String salt;
    private Roles role;

    //#endregion

    //#region Constructor

    public User(){}

    /**
     * Constructor
     * @param idUser ID User
     * @param firstName First Name
     * @param lastName Last Name
     * @param login Login
     * @param password Password
     * @param salt Salt
     * @param role Role
     */
    public User(Integer idUser, String firstName, String lastName, String login, String password, String salt, Roles role) {
        super();
        this.idUser = idUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.salt = salt;
        this.role = role;
    }

    //#endregion

    //#region Getter & Setter

    /**
     * @return the idUser
     */
    public Integer getIdUser() {
        return idUser;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * @return the role
     */
    public Roles getRole() {
        return role;
    }

    //#endregion
}
