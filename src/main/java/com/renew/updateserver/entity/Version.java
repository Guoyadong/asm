package com.renew.updateserver.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;

import org.codehaus.jackson.annotate.JsonIgnore;

@Data
@ToString
public class Version implements Serializable
{
    
    public static String VERSION_STORE_DIR = "/version_store";
    
    // 要求强制更新
    public static int UPDATE_FORCE_OK = 1;

    public static int UPDATE_FORCE_NO = 0;

    private int id = 0;

    private int app_id;

    private long version;

    private String version_string;

    private int update_force;
    
    /**
     * 当前版本是否已是最新版本 0：不是；1：是
     */
    private int is_current = 0;

    /**
     * app是否开启，1：已开启 0：未开启
     */
    private int enable = 1;
    /**
     * app是否审核通过，1：通过审核 0：未通过审核
     */
    private int audited = 1;

    private String server_url = "";

    private String download_url = "";
    
    private String file_name ="";

    private String remark ="";//版本描述

    private Timestamp create_time = null;
    
    public void setVersion(long version)
    {
        this.version = version;
    }
    
    @JsonIgnore
    public long getVersion()
    {
        return this.version;
    }
    
    @JsonIgnore
    public Timestamp getCreate_time()
    {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time)
    {
        this.create_time = create_time;
    }

}
