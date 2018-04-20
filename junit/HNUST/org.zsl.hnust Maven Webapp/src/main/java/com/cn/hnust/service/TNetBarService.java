package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.TNetBar;
import com.cn.hnust.util.PagedResult;

public interface TNetBarService {
	int deleteByPrimaryKey(String id);

    int insert(TNetBar record);

    int insertSelective(TNetBar record);

    TNetBar selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TNetBar record);

    int updateByPrimaryKey(TNetBar record);
    List<TNetBar> selectAll();
    PagedResult<TNetBar> selectAll(Integer pageNo,Integer pageSize);
}
