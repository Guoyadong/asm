package com.renew.updateserver.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@ToString
public class App implements Serializable
{
    public static int APP_ENABLED = 1;
    
    public static int APP_UNABLED =0;
    
    private int id = 0;

    private String name = "";

    private String description = "";
    /**
     * app是否开启，1：已开启 0：未开启
     */
    private int enable = APP_ENABLED;

    private Timestamp create_time = null;

    public void setName(String name)
    {
        this.name = name;
    }

}
