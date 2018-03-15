package excel_poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;  
  
public class testPOI07 {  
    public void writeExcel07() throws IOException{  
        //创建工作簿  
        XSSFWorkbook workBook = new XSSFWorkbook();  
        //创建工作表  
        XSSFSheet sheet = workBook.createSheet("helloWorld");  
        //创建行  
        XSSFRow row = sheet.createRow(0);  
        //创建单元格，操作第三行第三列  
        XSSFCell cell = row.createCell(2, CellType.STRING);  
        cell.setCellValue("helloWorld");  
          
        FileOutputStream outputStream = new FileOutputStream(new File("d:\\测试.xlsx"));  
        workBook.write(outputStream);  
          
        workBook.close();//记得关闭工作簿  
    }  
      
    public void readExcel07() throws IOException{  
        FileInputStream inputStream = new FileInputStream(new File("d:\\测试.xlsx"));  
        //读取工作簿  
        XSSFWorkbook workBook = new XSSFWorkbook(inputStream);  
        //读取工作表  
        XSSFSheet sheet = workBook.getSheetAt(0);  
        //读取行  
        XSSFRow row = sheet.getRow(0);  
        //读取单元格  
        XSSFCell cell = row.getCell(2);  
        String value = cell.getStringCellValue();  
          
        System.out.println(value);  
          
        inputStream.close();//关闭工作簿  
        workBook.close();  
    }  
    
    @Test  
    public void testExcelStyle() throws IOException{  
        //1.创建工作簿  
        HSSFWorkbook workBook = new HSSFWorkbook();  
          
        //创建合并单元格对象  
        CellRangeAddress rangeAddress = new CellRangeAddress(2, 2, 2, 4);  
        //创建样式  
        HSSFCellStyle style = workBook.createCellStyle();  
        style.setAlignment(HorizontalAlignment.CENTER);  
        style.setVerticalAlignment(VerticalAlignment.CENTER);  
        //创建字体  
        HSSFFont font = workBook.createFont();  
        font.setFontHeightInPoints((short) 16);  
        font.setFontHeight((short)320);// 效果和上面一样。用这个方法设置大小，值要设置为字体大小*20倍，具体看API文档  
        font.setColor(HSSFColor.GREEN.index);  
        font.setBold(true);  
        style.setFont(font);  
        //设置背景  
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
        style.setFillForegroundColor(HSSFColor.RED.index);  
          
        //2.创建工作表  
        HSSFSheet sheet = workBook.createSheet("我是测试");  
        //添加合并区域  
        sheet.addMergedRegion(rangeAddress);  
          
        //3.创建行  
        HSSFRow row = sheet.createRow(1);  
        //4.创建单元格  
        HSSFCell cell = row.createCell(2);  
        cell.setCellValue("helloWorld");  
        cell.setCellStyle(style);  
        
          
        //输出  
        FileOutputStream outputStream = new FileOutputStream(new File("d:\\测试1.xls"));  
        workBook.write(outputStream);  
          
        workBook.close();  
        outputStream.close();  
    }  
}  
