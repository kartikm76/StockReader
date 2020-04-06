package com.stockmgmt.interfaces;

import java.util.ArrayList;
import com.stockmgmt.model.Stock;

public interface InterfaceExchangeData 
{
	public ArrayList<Stock> getStockExchangeData();
	//method reads the exchange data from the file
}