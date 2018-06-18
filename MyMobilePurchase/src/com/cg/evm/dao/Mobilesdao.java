package com.cg.evm.dao;

import java.util.List;

import com.cg.evm.dto.Mobilesdto;
import com.cg.evm.exception.MobilesException;

public interface Mobilesdao {
 public List<Mobilesdto> displayMobiles();
 public boolean removeMobiledetails(int MobileId) throws MobilesException;
 public List<Mobilesdto> SearchmobilesinRange(int min,int max);
}
