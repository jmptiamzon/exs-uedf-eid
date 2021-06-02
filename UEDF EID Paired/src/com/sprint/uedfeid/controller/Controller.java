package com.sprint.uedfeid.controller;

//import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipInputStream;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sprint.uedfeid.model.ExcelData;
import com.sprint.uedfeid.model.Model;

public class Controller {
	private Model model;
	//private static final int BUFFER_SIZE = 4096;
	
	public Controller() {
		model = new Model();
	}
	
	public void runApp(String serialStr) {
		List<ExcelData> excelData = model.getExcelData(serialStr);
		
		if (excelData.size() == 0) {
			JOptionPane.showMessageDialog(null, "Serial not found.");
		
		} else {
			/*File dir = new File(System.getProperty("user.home") + "\\Documents\\uedf-eid");
			
			if (!dir.isDirectory()) {
				
				try {
					Files.createDirectories(Paths.get(System.getProperty("user.home") + "\\Documents\\uedf-eid"));
					unzip(System.getProperty("user.dir") + "//myzip.zip", System.getProperty("user.home") + "\\Documents\\uedf-eid");
					
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Error creating / extracting folder / template: " + e.getMessage());
					
				}
			}*/
			
			writeExcelData(excelData);
			JOptionPane.showMessageDialog(null, "File successfully generated.");
		} 
	}
	/*
    public void unzip(String zipFilePath, String destDirectory) throws IOException {
        File destDir = new File(destDirectory);
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
        ZipEntry entry = zipIn.getNextEntry();
        // iterates over entries in the zip file
        while (entry != null) {
            String filePath = destDirectory + File.separator + entry.getName();
            System.out.println(entry.getName());
            if (!entry.isDirectory()) {
                // if the entry is a file, extracts it
                extractFile(zipIn, filePath);
            } else {
                // if the entry is a directory, make the directory
                File dir = new File(filePath);
                dir.mkdirs();
            }
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
        }
        zipIn.close();
    }
    
    private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[BUFFER_SIZE];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }*/
	
	public void writeExcelData(List<ExcelData> excelData) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd_HH-mm-ss");
		LocalDateTime now = LocalDateTime.now();
		String filepath = System.getProperty("user.home") + "\\Documents\\uedf-eid\\UEDF_Template.xlsx";
		String outputFile = System.getProperty("user.home") + "\\Documents\\uedf-eid\\UEDF_Template_" + dtf.format(now) + ".xlsx";
		
		try {
			FileInputStream xlsxFile = new FileInputStream(new File(filepath));
			Workbook workbook = new XSSFWorkbook(xlsxFile);
			Sheet sheet = workbook.getSheetAt(0);
			
			for (int ctr = 0; ctr < excelData.size(); ctr++) {
				Row row = sheet.createRow(ctr + 1);
				
				Cell cell = row.createCell(2);
				cell.setCellValue(excelData.get(ctr).getLocationId());

				cell = row.createCell(3);
				cell.setCellValue(excelData.get(ctr).getManfName());
				
				cell = row.createCell(4);
				cell.setCellValue(excelData.get(ctr).getModelNo());
				
				cell = row.createCell(5);
				cell.setCellValue(excelData.get(ctr).getModelName());
				
				cell = row.createCell(6);
				cell.setCellValue(excelData.get(ctr).getNetworkType());
				
				cell = row.createCell(11);
				cell.setCellValue(excelData.get(ctr).getLockcode1());
			
				cell = row.createCell(12);
				cell.setCellValue(excelData.get(ctr).getLockcode2());
				
				cell = row.createCell(14);
				cell.setCellValue(excelData.get(ctr).getSoftwareNo());
				
				cell = row.createCell(20);
				cell.setCellValue(excelData.get(ctr).getProductType());
				
				cell = row.createCell(25);
				cell.setCellValue(excelData.get(ctr).getImeiDec());
				
				cell = row.createCell(52);
				cell.setCellValue(excelData.get(ctr).getCsnOrEid());
				
			}
			
			xlsxFile.close();
			FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
			workbook.write(fileOutputStream);
			workbook.close();
			fileOutputStream.close();
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "IO Error: " + e.getMessage());
			
		}
	}
	
}
