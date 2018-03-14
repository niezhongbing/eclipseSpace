package com.word.poi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/********************************************************
* 类名：Test
* 功能：测试类  指定一些标记的参数，图片路径，文件模板位置，文件输出位置
* 作者：qiucx 2017年4月12日
* 修改记录：
*          日期                 修改人                 修改说明
********************************************************/
public class Test {
	
	//指定一些标记的参数，图片路径，文件模板位置，文件输出位置
	public static void main(String[] args) throws Exception {  
        //文字标记
        Map<String, Object> param = new HashMap<String, Object>();  
        param.put("${name}", "qiucx");  
        param.put("${zhuanye}", "计算机科学与技术");  
        param.put("${sex}", "男");  
        param.put("${school_name}", "福建农林大学");  
        param.put("${date}", new Date().toString());  
        
        //图片标记，路径，宽高
        Map<String,Object> header = new HashMap<String, Object>();  
        header.put("width", 150);  
        header.put("height", 150);  
        header.put("type", "jpg");  
        header.put("content", WordUtil.inputStream2ByteArray(new FileInputStream("D:\\icon\\smile.jpg"), true));  
        param.put("${header}",header);  
 
        //原文件  
        CustomXWPFDocument doc = WordUtil.generateWord(param, "D:\\icon\\tess.docx"); 
        //输出文件
        FileOutputStream fopts = new FileOutputStream("D:\\icon\\tess2.docx"); 
        doc.write(fopts);  
        fopts.close();  
    }  
}
