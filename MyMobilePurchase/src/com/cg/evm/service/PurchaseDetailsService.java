package com.cg.evm.service;

import com.cg.evm.dto.PurchaseDetailsdto;
import com.cg.evm.exception.PurchaseDetailsException;

public interface PurchaseDetailsService {
	public int AddPurchaseDetails(PurchaseDetailsdto pur) throws PurchaseDetailsException;
	public boolean validatePurchaseDetails(PurchaseDetailsdto Purchase);
}
