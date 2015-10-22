package com.renew.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;

public class LoginServlet extends HttpServlet
{
    public static String LOGIN_USER = "admin";
    
    private String LOGIN_PWD = "!QAZ@WSX";

    public static String COOKIE = "abc_qukan_def";


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException
    {
        String username = (String)request.getParameter("username");
        String password = (String)request.getParameter("password");
        if(LOGIN_USER.equals(username) && password.equals(LOGIN_PWD))
        {
            request.getSession().setAttribute("username", username);

            Cookie cookie = new Cookie(COOKIE, LOGIN_USER);
            cookie.setMaxAge(30 * 24 * 60 * 60);
            cookie.setPath("/");
            response.addCookie(cookie);

            response.setCharacterEncoding("utf-8");
            PrintWriter pw = response.getWriter();
            pw.write("{\"value\":\"登陆成功\",\"result\":\"RESULT_OK\"}");
            pw.close();
        }
        else
        {
            request.getSession().setAttribute("username", username);
            response.setCharacterEncoding("utf-8");
            PrintWriter pw = response.getWriter();
            pw.write("{\"value\":\"用户名或密码错误\",\"result\":\"RESULT_PARAM_ERR\"}");
            pw.close();
        }
    }

}
