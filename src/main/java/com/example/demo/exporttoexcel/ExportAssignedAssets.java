package com.example.demo.exporttoexcel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.demo.models.AssignedAssets;

import jakarta.servlet.http.HttpServletResponse;

public class ExportAssignedAssets {

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<AssignedAssets> assignedAssets;
	
	public ExportAssignedAssets(List<AssignedAssets> assignedAssets) {
		this.assignedAssets = assignedAssets;
		workbook = new XSSFWorkbook();
	}
	
	private void writeHeaderLine()
	{
		sheet = workbook.createSheet("AssignedAssets");
		Row row = sheet.createRow(0);
		
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(12);
		
		style.setFont(font);
	
		createCell(row,0,"Sr No.",style);
		createCell(row,1,"Employee",style);
		createCell(row,2,"Assets",style);
		createCell(row,3,"Asset Type",style);
		createCell(row,4,"Model",style);
		createCell(row,5,"Assign Date",style);
		createCell(row,6,"Assign Time",style);
		
		createCell(row,7,"Email",style);
		createCell(row,8,"Department",style);
		createCell(row,9,"Company",style);
	}
	
	private void createCell(Row row,int columnCount,Object value,CellStyle style)
	{
		  sheet.autoSizeColumn(columnCount);
	        Cell cell = row.createCell(columnCount);
	        if (value instanceof Integer) {
	            cell.setCellValue((Integer) value);
	        } else if (value instanceof Boolean) {
	            cell.setCellValue((Boolean) value);
	        }
	        else if (value instanceof Long) {
	            cell.setCellValue((Long) value);
	        }
	        else {
	            cell.setCellValue((String) value);
	        }
	        cell.setCellStyle(style);
	}
	
	private void writeDataLines()
	{
		int rowCount = 1;
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		int sr=1;
		for(AssignedAssets asset : assignedAssets)
		{
			Row row = sheet.createRow(rowCount++);
			
			int columnCount = 0;
			
			createCell(row,columnCount++, sr++ ,style);
			createCell(row,columnCount++, asset.getEmployee().getEmp_name() ,style);
			createCell(row,columnCount++, asset.getAssigned() ,style);
			createCell(row,columnCount++, asset.getAssigned_types() ,style);
			createCell(row,columnCount++, asset.getModel_numbers() ,style);
			createCell(row,columnCount++, asset.getAssign_date() ,style);
			createCell(row,columnCount++, asset.getAssign_time() ,style);
			createCell(row,columnCount++, asset.getEmployee().getEmp_email() ,style);
			createCell(row,columnCount++, asset.getEmployee().getDepartment().getDept_name(), style);
			createCell(row,columnCount++, asset.getEmployee().getDepartment().getCompany().getComp_name(), style);
		}
	}
	
	public byte[] export(HttpServletResponse resp)throws IOException
	{
		writeHeaderLine();
		writeDataLines();
		
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();

		workbook.write(bos);
		workbook.close();
		bos.close();
		return bos.toByteArray();
	}
}
