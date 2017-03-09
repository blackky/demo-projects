/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.servlet;

import com.sample.bean.User;
import com.sample.manager.UserManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author 890747
 */
public class AddUserServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json"); // most important step
        PrintWriter writer = response.getWriter();
        JSONObject respObj = new JSONObject();
        try {
            String message = "Unable to create a new user. Please try again later.";
            User user = new User();
            user.setEmail(request.getParameter("email"));
            user.setFirst_name(request.getParameter("first_name"));
            user.setLast_name(request.getParameter("last_name"));
            user.setRole(request.getParameter("role"));
            char[] pwd = request.getParameter("password").toCharArray();
            /**
             * Perform server side validations too. Implementation necessary but
             * left at user discretion.
             */

            boolean flag = UserManager.getInstance().createUser(user, pwd);
            if (flag) {
                message = "New User Created.";
            }
            respObj.put("flag", flag);
            respObj.put("msg", message);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writer.write(respObj.toString());
            writer.close();
        }
    }
}
