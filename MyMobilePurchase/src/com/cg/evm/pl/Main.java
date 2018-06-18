package com.cg.evm.pl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.evm.dto.Mobilesdto;
import com.cg.evm.dto.PurchaseDetailsdto;
import com.cg.evm.exception.MobilesException;
import com.cg.evm.exception.PurchaseDetailsException;
import com.cg.evm.service.MobilesServiceImpl;
import com.cg.evm.service.PurchaseDetailsServiceImpl;

public class Main {
	
	static Logger lg=Logger.getLogger(Main.class);
	
	 static BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
	 
	public static int AddCustomerDetails() throws IOException {
			
		lg.debug("adding customer");
		
		System.out.println("Enter name:");
		String Name=in.readLine();
		System.out.println("Enter mailId");
		String Mail=in.readLine();
		System.out.println("Enter Phone Number");
		long PhoneNum=Long.parseLong(in.readLine());
		System.out.println("Select from these mobiles list");
		List<Mobilesdto> list2=DisplayMobiles();
		for(Mobilesdto Mobile:list2) {
			System.out.println("Mobile Id: "+Mobile.getMobileId());
			System.out.println("Mobile Name: "+Mobile.getMobileName());
			System.out.println("Price: "+Mobile.getPrice());
			System.out.println("Quantity:"+Mobile.getQuantity());
			System.out.println("**********************");
		}
		System.out.println("Enter mobileId");
		int MobileId=Integer.parseInt(in.readLine());
		int PurchaseId=0;
		PurchaseDetailsdto pur=new PurchaseDetailsdto();
		pur.setName(Name);
		pur.setMail(Mail);
		pur.setPhoneNum(PhoneNum);
		pur.setMobileId(MobileId);
		PurchaseDetailsServiceImpl service=new PurchaseDetailsServiceImpl();
  
         boolean res=service.validatePurchaseDetails(pur);
        
         if(res) {
        	try {
				PurchaseId=service.AddPurchaseDetails(pur);
			} catch (PurchaseDetailsException e) {
				System.out.println(e.getMessage());
			}
          }
	
		
		return  PurchaseId;
	}
	
	public static List<Mobilesdto> DisplayMobiles() {
		List<Mobilesdto> list=new ArrayList<Mobilesdto>();
		MobilesServiceImpl service=new MobilesServiceImpl();
		try {
			list=service.DisplayMobiles();
		} catch (MobilesException e) {
			System.out.println("no mobiles to display");
		}
		
		return list;
	}
	
	public static boolean deleteMobiledetails() throws NumberFormatException, IOException, MobilesException {
		System.out.println("enter MobileId to delete");
			int MobileId=Integer.parseInt(in.readLine());
			MobilesServiceImpl service=new MobilesServiceImpl();
		boolean res=false;
		res = service.deleteMobiledetailsbasedonId(MobileId);
		return res;
	}
	
	public static List<Mobilesdto> SearchMobiles() throws NumberFormatException, IOException, MobilesException {
		System.out.println("Enter min price");
	int	MinPrice=Integer.parseInt(in.readLine());
	System.out.println("Enter Max Price");
	int MaxPrice=Integer.parseInt(in.readLine());
	MobilesServiceImpl service=new MobilesServiceImpl();
	return service.Searchmobiles(MinPrice,  MaxPrice);

	
	}
	
	
public static void main(String[] args) throws NumberFormatException, IOException {
	PropertyConfigurator.configure("./src/log4j.properties");
	while(true)
	{
	System.out.println("\n1.Add Customer Details\n"
			+ "2.Display details of all mobiles available in the shop\n"
			+ "3.Delete a mobile details based on mobile id\n"
			+ "4.Search mobiles based on price range\n"
			+ "5.exit");
	System.out.println("\nEnter your choice");
	int choice=Integer.parseInt(in.readLine());
	switch(choice) {
	case 1:
		
		System.out.println("Purchase Id is "+AddCustomerDetails());
		break;
	case 2:
		
		List<Mobilesdto> list=DisplayMobiles();
		for(Mobilesdto Mobile:list) {
			System.out.println("Mobile Id: "+Mobile.getMobileId());
			System.out.println("Mobile Name: "+Mobile.getMobileName());
			System.out.println("Price: "+Mobile.getPrice());
			System.out.println("Quantity:"+Mobile.getQuantity());
			System.out.println("**********************");
		}
		break;
	case 3:
		
		try {
			if(deleteMobiledetails()) {
				System.out.println("Mobile Details are deleted");
			}
		} catch (MobilesException e) {
			System.out.println(e.getMessage());
		}
		break;
	case 4:
		List<Mobilesdto> list1;
		try {
			list1 = SearchMobiles();
			if(list1.size()==0) {
				System.out.println("No mobiles found in the given range");
			}
			else 
			for(Mobilesdto Mobile1:list1) {
				System.out.println("Mobile Id: "+Mobile1.getMobileId());
				System.out.println("Mobile Name: "+Mobile1.getMobileName());
				System.out.println("Price: "+Mobile1.getPrice());
				System.out.println("Quantity:"+Mobile1.getQuantity());
				System.out.println("**********************");
			}
		} catch (MobilesException e) {
			System.out.println("Error occured"+e.getMessage());
		}
		break;
	case 5:
		System.out.println("Thank you");
		System.exit(0);
		default: 
			System.out.println("Your select doesn't match any option in Menu");
			break;
	}
	}
}
	
}
