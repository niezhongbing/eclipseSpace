package com.bhusk.controller;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bhusk.utils.ExportUtil;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.PictureRenderData;
import com.deepoove.poi.data.RenderData;
import com.deepoove.poi.data.TableRenderData;
import com.deepoove.poi.data.TextRenderData;

@Controller
@RequestMapping(value = "/")
public class ExportController {

    private static Logger logger = LoggerFactory.getLogger(ExportController.class);

    @RequestMapping("index")
    public String index(HttpServletRequest request, HttpServletResponse response) {

        String context = " \n" +
                "\n" +
                "<html>\n" +
                "  <head>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "  </head>\n" +
                "  <body>\n" +
                "  <table border=\"2\" align=\"center\" height=\"900px\" width=\"600px\" bordercolor=\"black\">\n" +
                "    <tr class=\"table\" style=\"height: 200px;\">\n" +
                "      <td style=\"width: 50%;\" align=\"center\"> \n" +
                "        <h1>分  析  报  告</h1>\n" +
                "        <h5>Failure Analysis Report</h5>\n" +
                "        <h5>名称：黑壳网</h5>\n" +
                "<a href=\"http://www.bhusk.com\">http:\\\\www.bhusk.com</a>" +
                "      </td>\n" +
                "      \n" +
                "    </tr>\n" +
                "    <tr></tr>\n" +
                "  </table>\n" +
                "  </body>\n" +
                "\n" +
                "</html>\n";

        ExportUtil exportUtil = new ExportUtil();

        exportUtil.exportWord(request, response, context);

        return "index";
    }
    
    @RequestMapping("imageWord")
    public void poiImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	final String urlImage = this.getClass().getClassLoader().getResource("emblem.png").getPath();
    	final String urlWord = this.getClass().getClassLoader().getResource("实验报告模板.docx").getPath();
    	Map<String, Object> datas = new HashMap<String, Object>(){{
			put("score", new TextRenderData("df2d4f", "张三"));
			put("emblem", new PictureRenderData(150, 150, "" + urlImage));
			put("college", "自动化");
			put("profession", "自动化技术");
			put("class", "2017级一(1)班");
			put("studentId", "001715");
			put("studentName", "约翰史密斯");
			put("teacher", "汤姆汉克斯");
			put("date", "2017-06-01");
			put("changeLog", new TableRenderData(new ArrayList<RenderData>(){{
				add(new TextRenderData("d0d0d0", ""));
				add(new TextRenderData("d0d0d0", ""));
				add(new TextRenderData("d0d0d0", ""));
				add(new TextRenderData("d0d0d0", "introduce"));
			}},new ArrayList<Object>(){{
				add("1;add new # gramer;1;1");
				add("2;support insert table;1;1");
				add("3;support more style;1;1");
			}}, "no datas", 10000));
		}};


        XWPFTemplate template = XWPFTemplate.compile(urlWord).render(datas);;

        FileOutputStream out = null;
        String fileName = "D:\\out_实验报告模板_1.docx";
		
		try {
			out = new FileOutputStream(fileName);
			template.write(out);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				out.flush();
				out.close();
				template.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    response.setCharacterEncoding("gbk");
	    File file = new File("D:/out_实验报告模板_1.docx");
	    response.setContentType("application/ms-winword");  
	    response.setContentLength((int)file.length());
	    
	    response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gbk"), "iso-8859-1"));
	    response.setHeader("Content-Disposition", "attachment;filename="  
	            +new String(fileName.getBytes("gbk"), "8859_1")  
	            + ".docx");  
	    response.addHeader("Pargam", "no-cache");  
	    response.addHeader("Cache-Control", "no-cache");  
	    FileInputStream fis = new FileInputStream(file);
	    BufferedInputStream buff = new BufferedInputStream(fis);
	    
	    byte[] b = new byte[1024];
	    
	    long k = 0L;
	    

	    OutputStream myout = response.getOutputStream();
	    while (k < file.length())
	    {
	      int j = buff.read(b, 0, 1024);
	      k += j;
	      
	      myout.write(b, 0, j);
	    }
	    myout.flush();
    }

}
