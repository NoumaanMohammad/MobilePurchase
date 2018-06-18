package com.cg.evm.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import org.apache.log4j.Logger;

import com.cg.evm.dto.PurchaseDetailsdto;
import com.cg.evm.exception.PurchaseDetailsException;
import com.cg.evm.util.DatabaseConnection;

public class PurchaseDetailsdaoImpl {

	Logger lg=Logger.getLogger(PurchaseDetailsdaoImpl.class);
	
	public int AddPurchasedetails(PurchaseDetailsdto pur) throws PurchaseDetailsException {
	
	String sql="insert into purchasedetails(purchaseid,cname,mailid,phoneno,purchasedate,mobileid) values(PurchaseIdseq.nextval,"
			+ "?,?,?,?,?)";
	int rows=0;
	
	Connection con=DatabaseConnection.getConnection();
	try {
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, pur.getName());
		ps.setString(2,pur.getMail());
		ps.setLong(3,pur.getPhoneNum());
		LocalDate PurchasedDate=LocalDate.now();
		Date sqlPurchasedDate =Date.valueOf( PurchasedDate);
		ps.setDate(4,sqlPurchasedDate);
		ps.setInt(5,pur.getMobileId());
		 rows=ps.executeUpdate();
		String query="select PurchaseIdseq.currval from dual";
		Statement s=con.createStatement();
		ResultSet rs=s.executeQuery(query);
		lg.info("select query executed");
		
	if(rows!=0) {
		String query1="update mobiles set quantity=quantity-1 " /*b) Update the mobile quantity in mobiles table, 
		                                                           once mobile is purchased by a customer.*/
				+ "where mobileid="+pur.getMobileId();
		
				Statement s1=con.createStatement();
				s1.executeUpdate(query1);
	}
				
		while(rs.next()) {
			int PurchaseId=rs.getInt(1);
			return PurchaseId;
			
		}
	} catch (SQLException e) {
		lg.error(e.getMessage());
		throw new PurchaseDetailsException(e.getMessage());
	}
			
	return 0;
}
}
