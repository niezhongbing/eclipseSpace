package com.cn.hnust.dao;

import com.cn.hnust.pojo.TNetBar;

public interface TNetBarDao {
    int deleteByPrimaryKey(String id);

    int insert(TNetBar record);

    int insertSelective(TNetBar record);

    TNetBar selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TNetBar record);

    int updateByPrimaryKey(TNetBar record);
}