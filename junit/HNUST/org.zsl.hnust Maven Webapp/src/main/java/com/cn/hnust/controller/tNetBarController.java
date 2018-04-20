package com.cn.hnust.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cn.hnust.pojo.TNetBar;
import com.cn.hnust.service.TNetBarService;
import com.cn.hnust.util.PagedResult;
import com.mysql.fabric.xmlrpc.base.Value;

@Controller
@RequestMapping("Bar")
public class tNetBarController {

	@Autowired
	private TNetBarService tNetBarService;
	@RequestMapping("/select")
	public @ResponseBody TNetBar selectByPrimaryKey(@RequestParam(value="id",required=false) String id){
		TNetBar selectByPrimaryKey = tNetBarService.selectByPrimaryKey(id);
		System.out.println(selectByPrimaryKey);
		return selectByPrimaryKey;
	}
	@ResponseBody
	@RequestMapping(value = "/selectAll")
	public  TNetBar selectAll(@RequestParam Map<String, String> map){
		Integer pageNo =Integer.parseInt(map.get("pageNo")) ;
		Integer pageSize = Integer.parseInt(map.get("pageSize"));
		PagedResult<TNetBar> listNetBar = tNetBarService.selectAll(pageNo,pageSize);
		System.out.println(listNetBar.getDataList().get(0));
		System.out.println(listNetBar.getDataList().size() + ":" + listNetBar.getPageNo() + ":" + listNetBar.getPages() + ":" + listNetBar.getPageSize()  + ":" + listNetBar.getTotal());
		return listNetBar.getDataList().get(0);
	}
	
	/*@ResponseBody
	@RequestMapping(value = "/selectAll")
	public  List<TNetBar> selectAll(@RequestParam Map<String, String> map){
		Integer pageNo =Integer.parseInt(map.get("pageNo")) ;
		Integer pageSize = Integer.parseInt(map.get("pageSize"));
		List<TNetBar>listNetBar = tNetBarService.selectAll(pageNo,pageSize);
		System.out.println(listNetBar.size());
		return listNetBar;
	}*/
}
