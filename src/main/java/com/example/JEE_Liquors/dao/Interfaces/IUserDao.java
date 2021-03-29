package com.example.JEE_Liquors.dao.Interfaces;

import com.example.JEE_Liquors.Models.User;

import javax.servlet.http.HttpServletRequest;

public interface IUserDao {

    /**
     * Convert database result in usable user
     * @param request request
     * @return user
     */
    User DataUser(HttpServletRequest request);
}
