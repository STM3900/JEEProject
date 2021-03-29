package com.example.JEE_Liquors.dao;

import com.example.JEE_Liquors.Models.User;

import javax.servlet.http.HttpServletRequest;

public interface IUserDao {

    /**
     * Convert databse result in usable user
     * @param request request
     * @return user
     */
    User DataUser(HttpServletRequest request);
}
