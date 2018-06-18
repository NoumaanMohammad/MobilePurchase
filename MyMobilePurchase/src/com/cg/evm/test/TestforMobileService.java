package com.cg.evm.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.cg.evm.dao.MobilesdaoImpl;
import com.cg.evm.dao.PurchaseDetailsdaoImpl;
import com.cg.evm.dto.Mobilesdto;
import com.cg.evm.dto.PurchaseDetailsdto;
import com.cg.evm.exception.MobilesException;
import com.cg.evm.exception.PurchaseDetailsException;

public class TestforMobileService {
	
	@Test
	public void TestforAddPurchaseDetails()  {

		PurchaseDetailsdaoImpl pd=new PurchaseDetailsdaoImpl();
		int Actual;
		int res=0;
		PurchaseDetailsdto pur = new PurchaseDetailsdto();
		
		pur.setName("Nikhilv");
		pur.setMail("nikhil@gmail.com");
		pur.setPhoneNum(9987456320l);
		pur.setMobileId(1002);

		try {
			res = pd.AddPurchasedetails(pur);
		} catch (PurchaseDetailsException e) {
			Actual=0;
		}
		
		if(res==0) {
			Actual=0;
		} 
		else {
			Actual=1;
			}
		Assert.assertEquals(1, Actual);
	}
	
	/*public void TestforMobileSearchinRange() {
		MobilesdaoImpl mob=new MobilesdaoImpl();
		List<Mobilesdto> list2=new ArrayList<Mobilesdto>();
		boolean result;
		try {
			mob.SearchmobilesinRange(5000,50000);
			if(list2.size()==0) {
			   result=true; }
			   else { 
				   result=false; 
			   }
			}
		} catch (MobilesException e) {
			
		}
		

	}*/
}
