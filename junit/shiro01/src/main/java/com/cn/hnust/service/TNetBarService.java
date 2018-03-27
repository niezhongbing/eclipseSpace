package com.cn.hnust.service;

import com.cn.hnust.pojo.TNetBar;

public interface TNetBarService {
	int deleteByPrimaryKey(String id);

    int insert(TNetBar record);

    int insertSelective(TNetBar record);

    TNetBar selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TNetBar record);

    int updateByPrimaryKey(TNetBar record);
}
