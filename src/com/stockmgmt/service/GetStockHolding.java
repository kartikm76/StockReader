
package com.stockmgmt.service;

import java.io.BufferedReader;
//everything you have bought
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.stockmgmt.interfaces.InterfaceStockHolding;
import com.stockmgmt.model.StockHolding;

public class GetStockHolding implements InterfaceStockHolding {
	private static String csvFile = "./data/StockHolding.csv";
	
	public ArrayList<StockHolding> getStockHolding() {	
		ArrayList<StockHolding> stockHoldingArray = new ArrayList<StockHolding>();
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				String[] thisStock = line.split(cvsSplitBy);
				StockHolding sH = new StockHolding(	thisStock[0], 
													Integer.parseInt(thisStock[1]),
													Double.parseDouble(thisStock[2]));
				
				stockHoldingArray.add(sH);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return stockHoldingArray;
	}
}
