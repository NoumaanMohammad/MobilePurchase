package com.cg.evm.service;

import java.util.List;

import com.cg.evm.dao.MobilesdaoImpl;
import com.cg.evm.dto.Mobilesdto;
import com.cg.evm.exception.MobilesException;

public class MobilesServiceImpl {
	
	static MobilesdaoImpl Mob=new MobilesdaoImpl();
    public List<Mobilesdto>  DisplayMobiles() throws MobilesException {
    	
    	return Mob.displayMobiles();
    }
    
    
	public boolean deleteMobiledetailsbasedonId(int MobileId) throws MobilesException {
		
		return Mob.removeMobiledetails(MobileId);
		
	}
	public List<Mobilesdto> Searchmobiles(int min,int max) throws MobilesException {
		return Mob.SearchmobilesinRange(min, max);
	}



}
