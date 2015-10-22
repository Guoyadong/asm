package com.renew.updateserver.service.admin.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Administrator {
    private Integer id;

    private String name;

    private String password;

    private String remark;

    private Date createTime;

}