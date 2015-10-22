package com.renew.updateserver.resource;

import com.renew.config.GlobalSetting;
import com.renew.result.ResultError;
import com.renew.updateserver.entity.App;
import com.renew.updateserver.entity.Version;
import com.renew.updateserver.service.AppService;
import com.renew.updateserver.service.CacheService;
import com.renew.updateserver.service.VersionService;
import com.renew.utils.CheckedException;
import com.renew.utils.PublicUtils;
import com.renew.utils.SpringContextHelper;
import lombok.Data;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

@Log4j
@Path("open")
@Produces("text/plain")
@Consumes("text/plain")
public class OpenResource
{
    private CacheService cacheService =  SpringContextHelper.getBean(CacheService.class);

    @Path("cacheClear")
    @GET
    public void cacheClear()
    {
        cacheService.cacheClear();
    }
}
