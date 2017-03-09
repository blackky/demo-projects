/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 890747
 */
public class JSPFilter implements Filter {

    private static final boolean debug = true;
    private HttpServletRequest httpRequest;
    private HttpServletResponse httpResponse;
    private String requestURL;
    private boolean isSessionActive = false;
    private FilterConfig filterConfig = null;
    private final List<String> userPages = new ArrayList<>();
    private final List<String> guestPages = new ArrayList<>();

    public JSPFilter() {
        guestPages.add("/");
        guestPages.add("/index.jsp");
        userPages.add("/welcome.jsp");
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("LoginFilter:DoBeforeProcessing");
        }
        this.httpRequest = (HttpServletRequest) request;
        this.httpResponse = (HttpServletResponse) response;
        String email = null;
        try {
            email = (String) httpRequest.getSession(false).getAttribute("email");

        } catch (Exception ex) {
            log("No email field present in session.");
        }
        requestURL = httpRequest.getServletPath();
        if (email != null) {
            this.isSessionActive = !email.equals("");
        }
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        if (debug) {
            log("LoginFilter:doFilter()");
        }

        doBeforeProcessing(request, response);
        /*
        if(this.requestURL.startsWith("/static/")){
            chain.doFilter(request, response);
        }
        else 
         */
        if (this.isSessionActive && !userPages.contains(this.requestURL)) {
            this.httpRequest.getRequestDispatcher("/Mongo/welcome.jsp").forward(request, response);
        } else if (!this.isSessionActive && !guestPages.contains(httpRequest.getServletPath())) {
            this.httpRequest.getRequestDispatcher("/Mongo/").forward(request, response);
        }

        chain.doFilter(request, response);

    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("LoginFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("LoginFilter()");
        }
        StringBuffer sb = new StringBuffer("LoginFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
