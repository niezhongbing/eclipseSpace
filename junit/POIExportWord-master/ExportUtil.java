package com.JinjiCompany.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;

/**
 * POI导出word文档 无插件
 * Created by kzyuan on 2017/6/14.
 */
public class ExportUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExportUtil.class);

    public void exportWord(HttpServletRequest request, HttpServletResponse response ,String content) {

        try {
            //word内容
            content = " \n" +
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
                    " <a href=\"http://www.bhusk.com\n">http:\/\/www.bhusk.com</a>
                    "      </td>\n" +
                    "      \n" +
                    "    </tr>\n" +
                    "    <tr></tr>\n" +
                    "  </table>\n" +
                    "  </body>\n" +
                    "\n" +
                    "</html>\n";

            byte b[] = content.getBytes("utf-8");  //这里是必须要设置编码的，不然导出中文就会乱码。
            ByteArrayInputStream bais = new ByteArrayInputStream(b);//将字节数组包装到流中

            /**
             * 关键地方
             * 生成word格式
             */
            POIFSFileSystem poifs = new POIFSFileSystem();
            DirectoryEntry directory = poifs.getRoot();
            DocumentEntry documentEntry = directory.createDocument("WordDocument", bais);
            //输出文件
            String fileName = "导出word文件示例";
            request.setCharacterEncoding("utf-8");
            response.setContentType("application/msword");//导出word格式
            response.addHeader("Content-Disposition", "attachment;filename=" +
                    new String((fileName + ".doc").getBytes(),
                            "UTF-8"));

            OutputStream ostream = response.getOutputStream();
            poifs.writeFilesystem(ostream);
            bais.close();
            ostream.close();
        } catch (Exception e) {
            logger.error("导出出错：%s", e.getMessage());
        }
    }
}
