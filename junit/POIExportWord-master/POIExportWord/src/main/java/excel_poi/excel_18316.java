package excel_poi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;

public class excel_18316 {

	@Test
	public void testExcel_1() throws Exception {
		// 创建HSSFworkbook对象
		HSSFWorkbook wb = new HSSFWorkbook();

		// 创建HSSFSheet
		HSSFSheet sheet = wb.createSheet("我是第一页");

		HSSFRow row = sheet.createRow(0);

		HSSFCell cell = row.createCell(0);

		cell.setCellValue("单元格中文");

		// 设置单元格样式
		HSSFCellStyle style = wb.createCellStyle();

		cell.setCellStyle(style);

		FileOutputStream out = new FileOutputStream("D:/test_1.xls");

		wb.write(out);

		out.flush();
	}

	@Test
	public void testExcel_2() throws Exception {

		// 创建HSSFWorkbook对象(excel的文档对象)
		HSSFWorkbook wb = new HSSFWorkbook();
		// 建立新的sheet对象（excel的表单）
		HSSFSheet sheet = wb.createSheet("成绩表");
		// 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row1 = sheet.createRow(0);
		// 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
		HSSFCell cell = row1.createCell(0);

		HSSFCellStyle style = wb.createCellStyle();

		//设置字体样式
		HSSFFont fontStyle = wb.createFont();
		fontStyle.setFontName("宋体");
		fontStyle.setColor(HSSFColor.RED.index);

		//单元格边框
		//设置边框样式
	     style.setBorderTop(HSSFCellStyle.BORDER_THIN);
	     style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	     style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	     style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	     
	     style.setBottomBorderColor(HSSFColor.DARK_RED.index);  
	     style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中  
		//将设置好的字体样式给单元格
		style.setFont(fontStyle);
		
		// 设置单元格内容
		cell.setCellValue("学员考试成绩一览表");
		//row1.createCell(4).setCellValue("2222");
		//cell.setCellStyle(style);
		row1.setRowStyle(style);
		//row1.createCell(4).setCellStyle(style);
		
		// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
		// 在sheet里创建第二行
		HSSFRow row2 = sheet.createRow(1);

		// 创建单元格并设置单元格内容
		
		row2.createCell(0).setCellValue("姓名");
		row2.createCell(1).setCellValue("班级");
		row2.createCell(2).setCellValue("笔试成绩");
		row2.createCell(3).setCellValue("机试成绩");
		
		// 在sheet里创建第三行
		HSSFRow row3 = sheet.createRow(2);

		row3.createCell(0).setCellValue("李明");
		row3.createCell(1).setCellValue("As178");
		row3.createCell(2).setCellValue(87);
		row3.createCell(3).setCellValue(78);
		// .....省略部分代码
		FileOutputStream out = new FileOutputStream("D:/test_2.xls");

		wb.write(out);

		out.flush();

	}
}
