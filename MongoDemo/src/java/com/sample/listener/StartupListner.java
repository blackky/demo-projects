/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.listener;

import com.sample.dao.HashDAO;
import com.sample.db.Database;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author Shubhendu Shekhar
 */
public class StartupListner implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
       HashDAO.getInstance().populateDB();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Database.closeConnection();
        }
}
