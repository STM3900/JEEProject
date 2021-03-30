package com.example.JEE_Liquors.dao.Interfaces;

import com.example.JEE_Liquors.Models.Product;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public interface IProductDao {

    /**
     * Convert database result in usable product
     * @param request request
     * @return product
     */
    Product DataProduct(HttpServletRequest request);

    /**
     * Convert database result in usable product
     * @param request request
     * @return products
     */
    ArrayList<Product> AllProducts(HttpServletRequest request);

    /**
     * Insert object in database
     * @param request request
     * @return void
     */
    void InsertProduct(HttpServletRequest request);
}
