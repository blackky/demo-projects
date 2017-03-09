/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.servlet;

import com.sample.manager.UserManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

/**
 *
 * @author Shubhendu Shekhar
 */
public class LoginServlet extends HttpServlet {

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
            String message = "Incorrect email or password.";
            String email = request.getParameter("email");
            char[] pwd = request.getParameter("password").toCharArray();
            /**
             * Perform server side validations too. Implementation necessary but
             * left at user discretion.
             */

            boolean flag = UserManager.getInstance().doLogin(email, pwd);
            if (flag) {
                message = "Login Successful";
                HttpSession session = request.getSession(true);
                session.setAttribute("email", email);
                session.setAttribute("name", UserManager.getInstance().getDetails(email).getFirst_name());
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
