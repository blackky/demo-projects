/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.dao;

import com.sample.bean.User;

/**
 *
 * @author Shubhendu Shekhar
 */
public class UserDAO extends AbstractDAO {

    private UserDAO() {
        super("user", User.class);
    }
    private static UserDAO userDAO;

    public static UserDAO getInstance() {
        if (userDAO == null) {
            userDAO = new UserDAO();
        }
        return userDAO;
    }

}
