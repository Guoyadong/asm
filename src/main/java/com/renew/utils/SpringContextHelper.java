package com.renew.utils;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@Log4j
public class SpringContextHelper implements ApplicationContextAware
{
    private ApplicationContext _applicationctx = null;

    private static SpringContextHelper _instance = null;

    private SpringContextHelper()
    {
        log.info("SpringContextHelper init()");
        _instance = this;
    }

    @Override
    public void setApplicationContext(ApplicationContext arg0) throws BeansException
    {
        log.info("setApplicationContext");
        _applicationctx = arg0;
    }

    public static ApplicationContext getApplicationContext()
    {
        if (null == _instance)
        {
            log.error("getApplicationContext Failed._instance is null.");
            return null;
        }

        return _instance._applicationctx;
    }

    // 获取spring初始化的bean
    public static <T> T getBean(Class<T> clazz)
    {
        if (null == _instance || null == _instance._applicationctx)
        {
            log.error("getBean Failed.Instance and Applicationctx is null.");
            return null;
        }
        return _instance._applicationctx.getBean(clazz);
    }
}
