package com.cn.hnust.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.TNetBarDao;
import com.cn.hnust.pojo.TNetBar;
import com.cn.hnust.service.TNetBarService;
import com.cn.hnust.util.BeanUtil;
import com.cn.hnust.util.PagedResult;
import com.github.pagehelper.PageHelper;

@Service("tNetBarServiceImpl")
public class TNetBarServiceImpl implements TNetBarService {

	@Autowired
	private TNetBarDao tNetBarDao;
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(TNetBar record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insertSelective(TNetBar record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public TNetBar selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return tNetBarDao.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(TNetBar record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(TNetBar record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public PagedResult<TNetBar> selectAll(Integer pageNo,Integer pageSize) {
		PageHelper.startPage(pageNo,pageSize);
		PagedResult<TNetBar> pagedResult = BeanUtil.toPagedResult(tNetBarDao.selectAll());
		return pagedResult;
	}

	public List<TNetBar> selectAll() {
		// TODO Auto-generated method stub
		return tNetBarDao.selectAll();
	}
	

}
