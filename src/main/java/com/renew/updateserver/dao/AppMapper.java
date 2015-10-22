package com.renew.updateserver.dao;

import java.util.List;

import com.renew.updateserver.entity.App;

public interface AppMapper
{
    /**
     * 添加app
     * 
     * @param app
     */
    public void insert(App app);

    /**
     * 更新app
     * 
     * @param app
     */
    public void update(App app);

    /**
     * 获得所有enable的app
     * 
     * @return
     */
    public List<App> selectAll();

    public App selectByName(String name);

    public App selectById(int id);

    /**
     * 设置enable属性
     * 
     * @param app
     */
    public void updateState(App app);
    
    //删除app
    public void delete(int id);
}
