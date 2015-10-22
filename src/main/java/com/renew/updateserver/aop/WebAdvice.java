package com.renew.updateserver.aop;

import com.renew.result.ResultError;
import com.renew.result.ResultObject;
import com.renew.updateserver.controller.LoginController;
import com.renew.updateserver.service.admin.entity.Administrator;
import com.renew.updateserver.service.admin.service.AdminService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/31 0031.
 */
@Aspect
public class WebAdvice {

    @Autowired
    public AdminService adminService;

    @Around("execution(* com.renew.updateserver.controller.AdminController.*(..))")
    public Object adminAround(ProceedingJoinPoint joinPoint) throws Exception
    {
        Object[] args = joinPoint.getArgs();
        HttpServletRequest req = null;
        for (Object arg : args) {
            if (arg instanceof HttpServletRequest)
            {
                req = (HttpServletRequest) arg;
                break;
            }
        }
        if (req == null)
        {
            throw new Exception("controller的方法参数中必须包含HttpServletRequest");
        }

        // 从用户会话中获取当前管理员用户信息
        Administrator admin = (Administrator)req.getSession().getAttribute(LoginController.LOGIN_ADMIN);
        if (null == admin) {
            return new ResultObject(ResultError.RESULT_PARAM_ERR,"请登录后操作");
        }


        // 那么将用户信息设置到线程本地存储和req中
        req.setAttribute(LoginController.LOGIN_ADMIN, admin);


        Object retObj = null;
        try {



            retObj = joinPoint.proceed(args);

            // 如果是页面的代码,那么添加这些对象处理,方便页面调用
            if (retObj instanceof ModelAndView) {

            }

        }
        catch (Throwable e)
        {
            e.printStackTrace();
        }
        return retObj;
    }
}
