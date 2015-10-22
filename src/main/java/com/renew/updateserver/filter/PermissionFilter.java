package com.renew.updateserver.filter;

import com.renew.servlet.LoginServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PermissionFilter implements Filter
{
    private String local_ip = "";
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        InetAddress addr;
        try
        {
            addr = InetAddress.getLocalHost();
            local_ip =addr.getHostAddress();
        }
        catch (UnknownHostException e)
        {
        }
        
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
            ServletException
    {
//        String clientIp = req.getRemoteAddr();
//        HttpServletRequest request = (HttpServletRequest) req;
//        String username = (String)request.getSession().getAttribute("username");
//        if ((username != null && "admin".equals(username)) || cookieLogged(request))
//        {
            chain.doFilter(req, res);
//        }
//        else
//        {
//            res.setCharacterEncoding("utf-8");
//            PrintWriter pw = res.getWriter();
//            pw.write("{\"value\":\"请登录后进行操作\",\"result\":\"NO_PERM\"}");
//            pw.close();
//        }

    }

    @Override
    public void destroy()
    {
    }

    private boolean cookieLogged(HttpServletRequest request)
    {
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
        {
            for (Cookie cookie : cookies)
            {
                if (LoginServlet.COOKIE.equals(cookie.getName()))
                {
                    return LoginServlet.LOGIN_USER.equals(cookie.getValue());
                }
            }
        }
        return false;
    }
}
