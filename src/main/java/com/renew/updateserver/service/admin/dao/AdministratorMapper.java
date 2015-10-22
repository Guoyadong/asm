package com.renew.updateserver.service.admin.dao;

import com.renew.updateserver.service.admin.entity.Administrator;

import java.util.List;

public interface AdministratorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Administrator record);

    int insertSelective(Administrator record);

    Administrator selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Administrator record);

    int updateByPrimaryKey(Administrator record);

    List<Administrator> selectTopTwo();

    Administrator selectByName(String name);
}