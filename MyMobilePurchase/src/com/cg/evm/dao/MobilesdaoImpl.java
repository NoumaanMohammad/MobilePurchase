package com.cg.evm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cg.evm.dto.Mobilesdto;
import com.cg.evm.exception.MobilesException;
import com.cg.evm.util.DatabaseConnection;

public class MobilesdaoImpl {
	
	
	 public List<Mobilesdto> displayMobiles() throws MobilesException {
		 List<Mobilesdto> list=new ArrayList<Mobilesdto>();
		 String sql="select * from mobiles";
		 Connection con=DatabaseConnection.getConnection();
		 try {
			Statement s=con.createStatement();
			ResultSet rs=s.executeQuery(sql);
			while(rs.next()) {
				int MobileId=rs.getInt("mobileid");
				String Name=rs.getString("name");
				int Price=rs.getInt("price");
				int Quantity=rs.getInt("quantity");
				
				Mobilesdto mb=new Mobilesdto();
		mb.setMobileId(MobileId);
		mb.setMobileName(Name);
		mb.setPrice(Price);
		mb.setQuantity(Quantity);
		list.add(mb);
		
			}
		} catch (SQLException e) {
			throw new MobilesException("");
		}
		 return list;
	 }

	 
	 public boolean removeMobiledetails(int MobileId) throws MobilesException  {
		 
		 String sql2="delete from purchasedetails where mobileid="+MobileId;
		 
		 String sql="delete from mobiles where mobileid="+MobileId;
		 Connection con=DatabaseConnection.getConnection();

		 int row=0,row1=0;
			try {
				Statement s=con.createStatement();
				Statement s1=con.createStatement();
				row1=s1.executeUpdate(sql2);
				row=s.executeUpdate(sql);
				
				if(row==0)
					throw new MobilesException("record not found");
				
			} catch (SQLException e) {
				throw new MobilesException("Invalid Mobile Id");
			}
			if (row==1) {
				return true;
			}
			else 
				return false;
	 }
	 public List<Mobilesdto> SearchmobilesinRange(int min,int max) throws MobilesException {
		 String sql="select * from mobiles where price between ? and ?";
		 List<Mobilesdto> list1=new ArrayList<Mobilesdto>();
		 Connection con=DatabaseConnection.getConnection();
		 PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1,min);
			ps.setInt(2, max);
			 ResultSet rs=ps.executeQuery();
			 while(rs.next()) {
				 int MobileId=rs.getInt(1);
				 String Name=rs.getString(2);
				 int Price=rs.getInt(3);
				 int Quantity=rs.getInt(4);
				 Mobilesdto mb=new Mobilesdto();
				 mb.setMobileId(MobileId);
				 mb.setMobileName(Name);
				 mb.setPrice(Price);
				 mb.setQuantity(Quantity);
				list1.add(mb);
				
			 }
		} catch (SQLException e) {
			throw new MobilesException("Entered Wrong data ");
		}
	
		return list1;
	 }


}
