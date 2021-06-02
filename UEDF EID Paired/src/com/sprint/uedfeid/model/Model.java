package com.sprint.uedfeid.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Model {
	private Connection conn;
	private PreparedStatement statement;
	private ResultSet rs;
	
	public Model() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
							"", 
							"", 
							""
					);
		
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Driver error: " + e.getMessage());
			
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "SQL error: " + e1.getMessage());
			
		}
	}
	
	public List<ExcelData> getExcelData(String serialStr) {
		ExcelData tempExcelData;
		List<ExcelData> excelData = new ArrayList<>();
		String skuStr = "";
		String tempSkuName = "";
		

		try {
			statement = conn.prepareStatement(
					"SELECT DISTINCT MANF_NMS_LOCATION_ID, MANF_NMS_NAME, " + 
					"EQUIP_NETWORK_TYPE, SUBSID_LCK_CD1_MSL, " + 
					"SUBSID_LCK_CD2_OTKSL, PRODUCT_TYPE, " + 
					"IMEI_DEC, TRANSCEIVER_SKU, CSN_OR_EID from EDF.EDF_DET_SERIALIZED_INVENTORY WHERE IMEI_DEC = ?");
			statement.setString(1, serialStr);
			rs = statement.executeQuery();
			
			while (rs.next()) {
				tempExcelData = new ExcelData();
				tempExcelData.setLocationId(rs.getString(1));
				tempExcelData.setManfName(rs.getString(2));
				tempExcelData.setNetworkType(rs.getString(3));
				tempExcelData.setLockcode1(rs.getString(4));
				tempExcelData.setLockcode2(rs.getString(5));
				tempExcelData.setProductType(rs.getString(6));
				tempExcelData.setImeiDec(rs.getString(7));
				tempExcelData.setTransceiverSku(rs.getString(8));
				tempExcelData.setCsnOrEid(rs.getString(9));
				
				excelData.add(tempExcelData);
			}
			
			
			for (int ctr = 0; ctr < excelData.size(); ctr++) {
				statement = conn.prepareStatement("SELECT SKU FROM EDF.EDF_SKU_COMBINATIONS WHERE TRANSCEIVER_SKU = ?");
				statement.setString(1, excelData.get(ctr).getTransceiverSku());
				rs = statement.executeQuery();
				
				while (rs.next()) {
					skuStr = rs.getString(1);
				}
				
				statement = conn.prepareStatement("SELECT DISTINCT SKU, SKU_NAME FROM EDF.EDF_DETAILS_STG_INT WHERE 1=1 AND SKU = ?");
				statement.setString(1, skuStr);
				rs = statement.executeQuery();
				
				while (rs.next()) {
					skuStr = rs.getString(1);
					tempSkuName = tempSkuName.isEmpty() ? rs.getString(2)
							: (tempSkuName.length() < rs.getString(2).length()) ? tempSkuName : rs.getString(2);
				}
				
				excelData.get(ctr).setModelNo(skuStr);
				excelData.get(ctr).setModelName(tempSkuName);
				
				skuStr = "";
				tempSkuName = "";
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "SQL execution error: " + e.getMessage());
			
		}
		
		return excelData;
	}
	
	
	
	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Connection closing error: " + e.getMessage());
			
		}
	}
	
}
