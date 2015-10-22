package com.renew.updateserver.controller;

import com.renew.config.GlobalSetting;
import com.renew.result.ResultError;
import com.renew.result.ResultObject;
import com.renew.updateserver.entity.App;
import com.renew.updateserver.entity.Version;
import com.renew.updateserver.service.AppService;
import com.renew.updateserver.service.VersionService;
import com.renew.utils.CheckedException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("services/version")
public class OpenController {

    @Autowired
    private VersionService versionService;
    @Autowired
    private AppService appService;
    @Autowired
    private GlobalSetting globalSetting;

    private Map<String, Version> cache = new ConcurrentHashMap<String, Version>(10);
    private volatile long lastVisitTime = 0;

    private int expireTime = 5 * 60 * 1000;

    @Data
    public static class GetLatestVersionRequest
    {
        String app_name;
        String version;
    }

    @RequestMapping(value = "get_latest", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject versionDel(HttpServletRequest req, @RequestBody GetLatestVersionRequest request)
    {
        String downloadUrlPrefix = globalSetting.getDownloadUrlPrefix();

        try {
            long nowTimeStamp = System.currentTimeMillis();
            if ((nowTimeStamp - lastVisitTime) < expireTime)
            {
                Version version = cache.get(request.getApp_name());
                if(null != version)
                {
                    return new ResultObject(ResultError.RESULT_OK, version);
                }
            }

            Version version = versionService.getLatest(request.app_name, request.version);
            if (version != null && "".equals(version.getDownload_url().trim())) {
                App app = appService.getById(version.getApp_id());
                if (null != app) {
                    String fileName = globalSetting.getAppFileNameMap().get(app.getName());
                    if ((null == fileName || "".equals(fileName))) {
                        fileName = app.getName();
                    }
                    version.setDownload_url(downloadUrlPrefix + fileName);
                }
            }
            lastVisitTime = nowTimeStamp;
            cache.put(request.getApp_name(), version);
            return new ResultObject(ResultError.RESULT_OK, version);
        }
        catch (CheckedException e)
        {
            return new ResultObject(e.getResult(), e.getMessage());
        }
    }

    @RequestMapping(value = "refresh")
    @ResponseBody
    public ResultObject refresh(HttpServletRequest req)
    {
        lastVisitTime = 0L;
        return new ResultObject(ResultError.RESULT_OK);
    }
}
