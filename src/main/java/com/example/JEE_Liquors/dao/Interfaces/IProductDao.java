package com.example.JEE_Liquors.dao.Interfaces;

import com.example.JEE_Liquors.Models.Product;

import javax.servlet.http.HttpServletRequest;

public interface IProductDao {

    /**
     * Convert database result in usable product
     * @param request request
     * @return product
     */
    Product DataProduct(HttpServletRequest request);
}
