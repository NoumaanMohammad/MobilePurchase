package com.cg.evm.service;

import java.util.regex.Pattern;

import com.cg.evm.dao.PurchaseDetailsdaoImpl;
import com.cg.evm.dto.PurchaseDetailsdto;
import com.cg.evm.exception.PurchaseDetailsException;
	
 
public class PurchaseDetailsServiceImpl implements PurchaseDetailsService {
	
	
	public int AddPurchaseDetails(PurchaseDetailsdto pur) throws PurchaseDetailsException {
		PurchaseDetailsdaoImpl pd=new  PurchaseDetailsdaoImpl();
		int PurchaseId=pd.AddPurchasedetails(pur);
		return  PurchaseId;
	}

	
	
	  public boolean validatePurchaseDetails(PurchaseDetailsdto Purchase) {
		   
		  if(Pattern.matches("[A-Z]{1}[a-z]{5,19}",Purchase.getName()))
		  {
			  if(Pattern.matches("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$", Purchase.getMail()))
			  {
				  if(Pattern.matches("[0-9]{10}", Purchase.getPhoneNum()+"")) 
				  {
					  return true;
				  } else return false;
			  } else return false;
		  }
		return false;
		  
	  }
	  
	  

}
