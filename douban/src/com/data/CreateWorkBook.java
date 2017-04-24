package com.data;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.*;

import com.model.Drama;
public class CreateWorkBook 
{
	public static void write(List<Drama> cells)throws Exception 
	{
		//Create Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook(); 
		//Create file system using specific name
		FileOutputStream out = new FileOutputStream(
				new File("createworkbook.xlsx"));
		//write operation workbook using file out object 
		Sheet sheet = workbook.createSheet();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		int i = 0;
		if (i==0) {
			Row row= sheet.createRow(i);
			int k=0;
			row.createCell(k).setCellValue("Douban_id");
			k++;row.createCell(k).setCellValue("name");
			k++;row.createCell(k).setCellValue("nation");
			k++;row.createCell(k).setCellValue("rate");
			k++;row.createCell(k).setCellValue("date");
			k++;row.createCell(k).setCellValue("url");
			i++;
		}
		for (Drama item : cells) {
			Row row= sheet.createRow(i);
			int k=0;
			row.createCell(k).setCellValue(item.getId());
			k++;row.createCell(k).setCellValue(item.getName());
			k++;row.createCell(k).setCellValue(item.getNation());
			k++;row.createCell(k).setCellValue(item.getRate());
//			k++;row.createCell(k).setCellValue(item.getDate());
			k++;
			Cell cell= row.createCell(k);
			cell.setCellValue(format.format(item.getDate()));
			cell.setCellType(CellType.NUMERIC);
			k++;
			cell= row.createCell(k);
			cell.setCellFormula("HYPERLINK(\"" + item.getURL()+ "\")");
			
			i++;
		}
		

		workbook.write(out);
		out.close();
		System.out.println("createworkbook.xlsx written successfully");
	}
	
}