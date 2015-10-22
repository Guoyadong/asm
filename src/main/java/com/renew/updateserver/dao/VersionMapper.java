package com.renew.updateserver.dao;

import com.renew.updateserver.entity.Version;

import java.util.List;
import java.util.Map;

public interface VersionMapper
{
    /**
     * 添加app
     * 
     * @param version
     */
    public void insert(Version version);

    /**
     * 更新app
     * 
     * @param version
     */
    public void update(Version version);

    /**
     * 获得最新可用的版本
     * 
     * @param id
     *            当前version的id
     * @param app_id
     *            当前version的app_id
     * @return
     */
    public Version selectLatest(int id, int app_id);

    /**
     * 根据app_id,version查询
     * 
     * @return
     */
    public Version select(int app_id, long version);

    /**
     * 根据appid,大于version，enable=1查询 update_force=1的个数
     * 
     * @param version
     * @return
     */
    public int selectUpdateForce(int app_id, long version);

    /**
     * 设置enable属性
     * 
     * @param version
     */
    public void delete(int id);
    
    
    /**
     * 查询版本列表
     * 
     * @param condition
     * @return
     */
    public List<Version> selectVersionByCondition(Map<String, Object> condition);

    /**
     * 查询符合该条件的版本的个数
     * 
     * @param condition
     * @return
     */
    int countVersionByCondition(Map<String, Object> condition);
    
    public List<Version> getAll();
    
    public Version getById(int id);
 
}
