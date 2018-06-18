package com.cg.evm.service;

import java.util.List;

import com.cg.evm.dto.Mobilesdto;
import com.cg.evm.exception.MobilesException;

public interface MobilesService {
    public List<Mobilesdto>  DisplayMobiles();
	public  boolean deleteMobiledetailsbasedonId(int MobileId) throws MobilesException;
	public List<Mobilesdto> Searchmobiles(int min,int max);

}
