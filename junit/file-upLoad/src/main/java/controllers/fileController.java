package controllers;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


@Controller
public class fileController {

	@Autowired 
	private ServletContext servletContext;
	/**
	 * 文件上传
	 * 
	 * @author：tuzongxun
	 * @Title: upLoadFile
	 * @param @param file
	 * @param @param request
	 * @param @throws IllegalStateException
	 * @param @throws IOException
	 * @return void
	 * @date Apr 28, 2016 1:22:00 PM
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/upLoadFile.do", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	public String upLoadFile(HttpServletRequest request)
			throws IllegalStateException, IOException {
		// @RequestParam("file") MultipartFile file,
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 取得上传文件
				MultipartFile f = multiRequest.getFile(iter.next());
				if (f != null) {
					// 取得当前上传文件的文件名称
					String myFileName = f.getOriginalFilename();
					// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if (myFileName.trim() != "") {
						// 定义上传路径
						String path = "D:\\file\\upload\\"
								+ myFileName;
						File localFile = new File(path);
						f.transferTo(localFile);
					}
				}
			}
		}
		return "上传成功";
	}
	
	 @RequestMapping("/fileDownLoad")
	    public ResponseEntity<byte[]> fileDownLoad(HttpServletRequest request) throws Exception{
	     
	      String fileName="test.docx";
	      String realPath = "D:\\file\\upload\\nzb.doc";//得到文件所在位置
	        InputStream in=new FileInputStream(new File(realPath));//将该文件加入到输入流之中
	         byte[] body=null;
	         body=new byte[in.available()];// 返回下一次对此输入流调用的方法可以不受阻塞地从此输入流读取（或跳过）的估计剩余字节数
	         in.read(body);//读入到输入流里面
	        
	        fileName=new String(fileName.getBytes("gbk"),"iso8859-1");//防止中文乱码
	        HttpHeaders headers=new HttpHeaders();//设置响应头
	        headers.add("Content-Disposition", "attachment;filename="+fileName);
	        HttpStatus statusCode = HttpStatus.OK;//设置响应吗
	        ResponseEntity<byte[]> response=new ResponseEntity<byte[]>(body, headers, statusCode);
	        return response;

	        //public ResponseEntity（T  body，
	        //                       MultiValueMap < String，String > headers，
	        //                       HttpStatus  statusCode）
	        //HttpEntity使用给定的正文，标题和状态代码创建一个新的。
	        //参数：
	        //body - 实体机构
	        //headers - 实体头
	        //statusCode - 状态码
	    }
}