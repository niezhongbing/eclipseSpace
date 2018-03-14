package poi_1.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

/**
 * 根据模板导出word文件工具类
 * @author lmb
 * @date 2017-3-14
 */
public class XwpfUtil {
	
	private static final Logger logger = Logger.getLogger(XwpfUtil.class);

	/**
	 * 导出word文件
	 * @param params
	 * @param is
	 * @param request
	 * @param response
	 * @param xwpfUtil
	 */
	public void exportWord(Map<String, Object> params, InputStream is,
			HttpServletRequest request, HttpServletResponse response,
			XwpfUtil xwpfUtil) {
		
		XWPFDocument doc = new XWPFDocument();
		xwpfUtil.replaceInPara(doc,params);
		xwpfUtil.replaceInTable(doc,params);
		try {
			OutputStream os = response.getOutputStream();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition","attachment;filename=exportWord.docx");//文件名中文不显示
			//把构造好的文档写入输出流
			doc.write(os);
			//关闭流
			xwpfUtil.close(os);
			xwpfUtil.close(is);
			os.flush();
			os.close();
		} catch (IOException e) {
			logger.error("文件导出错误");
		}
	}

	/**
	 * 替换word模板文档段落中的变量
	 * @param doc 要替换的文档
	 * @param params 参数
	 */
	public void replaceInPara(XWPFDocument doc, Map<String, Object> params) {
		Iterator<XWPFParagraph> iterator = doc.getParagraphsIterator();
		XWPFParagraph para;
		while(iterator.hasNext()){
			para = iterator.next();
			this.replaceInPara(para,params);
		}
	}

	/**
	 * 替换段落中的变量
	 * @param para 要替换的段落
	 * @param params 替换参数
	 */
	public void replaceInPara(XWPFParagraph para, Map<String, Object> params) {
		List<XWPFRun> runs;
		if (((Matcher) this.matcher(para.getParagraphText())).find()) {
			runs = para.getRuns();
			int start = -1;
			int end = -1;
			String str = "";
			for (int i = 0; i < runs.size(); i++) {
				XWPFRun run = runs.get(i);
				String runText = run.toString().trim();
				if (StringUtils.isNotBlank(runText)&&'$' == runText.charAt(0)&&'{' == runText.charAt(1)) {
					start = i;
				}
				if (StringUtils.isNotBlank(runText)&&(start != -1)) {
                    str += runText;
                }
				if (StringUtils.isNotBlank(runText)&&'}' == runText.charAt(runText.length() - 1)) {
                    if (start != -1) {
                        end = i;
                        break;
                    }
                }
			}
			for (int i = start; i <= end; i++) {
                para.removeRun(i);
                i--;
                end--;
                System.out.println("remove i="+i);
            }
            if(StringUtils.isBlank(str)){
            	String temp = para.getParagraphText();
        		str = temp.trim().substring(temp.indexOf("${"),temp.indexOf("}")+1);
            }
            for (String key : params.keySet()) {
                if (str.equals(key)) {
                    para.createRun().setText(String.valueOf(params.get(key)) );
                    break;
                }
            }
		}
	}

	/**
	 * 替换word模板文档表格中的变量
	 * @param doc 要替换的文档
	 * @param params 参数
	 */
	public void replaceInTable(XWPFDocument doc, Map<String, Object> params) {
		Iterator<XWPFTable> iterator = doc.getTablesIterator();
		XWPFTable table;
		List<XWPFTableRow> rows;
		List<XWPFTableCell> cells;
		List<XWPFParagraph> paras;
		while (iterator.hasNext()) {
            table = iterator.next();
            rows = table.getRows();
            for (XWPFTableRow row : rows) {
                cells = row.getTableCells();
                for (XWPFTableCell cell : cells) {
                    paras = cell.getParagraphs();
                    for (XWPFParagraph para : paras) {
                        this.replaceInPara(para, params);
                    }
                }
            }
        }
	}
	
	/**
	 * 正则匹配字符串
	 * @param paragraphText
	 * @return
	 */
	public Object matcher(String str) {
		Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(str);
		return null;
	}
	
	/**
	 * 关闭输入流
	 * @param is
	 */
	public void close(InputStream is) {
		if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}

	/**
	 * 关闭输出流
	 * @param is
	 */
	public void close(OutputStream os) {
		if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
}