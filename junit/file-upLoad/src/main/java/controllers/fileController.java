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
	 * �ļ��ϴ�
	 * 
	 * @author��tuzongxun
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
		// �ж� request �Ƿ����ļ��ϴ�,���ಿ������
		if (multipartResolver.isMultipart(request)) {
			// ת���ɶಿ��request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// ȡ��request�е������ļ���
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// ȡ���ϴ��ļ�
				MultipartFile f = multiRequest.getFile(iter.next());
				if (f != null) {
					// ȡ�õ�ǰ�ϴ��ļ����ļ�����
					String myFileName = f.getOriginalFilename();
					// ������Ʋ�Ϊ����,˵�����ļ����ڣ�����˵�����ļ�������
					if (myFileName.trim() != "") {
						// �����ϴ�·��
						String path = "D:\\file\\upload\\"
								+ myFileName;
						File localFile = new File(path);
						f.transferTo(localFile);
					}
				}
			}
		}
		return "�ϴ��ɹ�";
	}
	
	 @RequestMapping("/fileDownLoad")
	    public ResponseEntity<byte[]> fileDownLoad(HttpServletRequest request) throws Exception{
	     
	      String fileName="test.docx";
	      String realPath = "D:\\file\\upload\\nzb.doc";//�õ��ļ�����λ��
	        InputStream in=new FileInputStream(new File(realPath));//�����ļ����뵽������֮��
	         byte[] body=null;
	         body=new byte[in.available()];// ������һ�ζԴ����������õķ������Բ��������شӴ���������ȡ�����������Ĺ���ʣ���ֽ���
	         in.read(body);//���뵽����������
	        
	        fileName=new String(fileName.getBytes("gbk"),"iso8859-1");//��ֹ��������
	        HttpHeaders headers=new HttpHeaders();//������Ӧͷ
	        headers.add("Content-Disposition", "attachment;filename="+fileName);
	        HttpStatus statusCode = HttpStatus.OK;//������Ӧ��
	        ResponseEntity<byte[]> response=new ResponseEntity<byte[]>(body, headers, statusCode);
	        return response;

	        //public ResponseEntity��T  body��
	        //                       MultiValueMap < String��String > headers��
	        //                       HttpStatus  statusCode��
	        //HttpEntityʹ�ø��������ģ������״̬���봴��һ���µġ�
	        //������
	        //body - ʵ�����
	        //headers - ʵ��ͷ
	        //statusCode - ״̬��
	    }
}