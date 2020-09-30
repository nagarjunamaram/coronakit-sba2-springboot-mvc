package com.eval.coronakit.service;

import java.util.List;

import com.eval.coronakit.entity.KitDetail;
import com.eval.coronakit.exception.ProductException;

public interface KitDetailService {
	public KitDetail addKitItem(KitDetail kitItem) throws ProductException;
	public KitDetail getKitItemById(int itemId);
	public List<KitDetail> getAllKitItemsOfAKit(int kitId);
}
