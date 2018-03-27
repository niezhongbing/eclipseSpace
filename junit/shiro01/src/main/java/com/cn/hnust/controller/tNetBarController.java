package com.cn.hnust.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cn.hnust.pojo.TNetBar;
import com.cn.hnust.service.TNetBarService;

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
	
	@RequestMapping("/refuse")
	public  String refuse(){
		return "refuse";
	}
}
