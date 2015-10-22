package com.renew.config;

import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@ToString
public class GlobalSetting
{
    //同 uploadDir
    public static String AppUploadDir = "";
    //上传app目录
    private String uploadDir = "";
    //下载地址为：downloadUrlPrefix + appFileName
    private String downloadUrlPrefix = "";
    //key:appName value:fileName
    private Map<String, String> appFileNameMap;

    private List<String> remoteServers;

    public void setUploadDir(String uploadDir)
    {
        this.uploadDir = uploadDir;
        AppUploadDir = uploadDir;
    }

    public void setAppFileNameMap(Map<String, String> appFileNameMap)
    {
        this.appFileNameMap = appFileNameMap;
    }

    public Map<String, String> getAppFileNameMap()
    {
        if(null == appFileNameMap)
        {
            appFileNameMap = new HashMap<String, String>(0);
        }
        return appFileNameMap;
    }

}
