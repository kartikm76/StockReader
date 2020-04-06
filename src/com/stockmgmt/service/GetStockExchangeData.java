package com.stockmgmt.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.stockmgmt.interfaces.InterfaceExchangeData;
import com.stockmgmt.model.Stock;

public class GetStockExchangeData implements InterfaceExchangeData 
{
	
	private static String csvFile = "./data/StockPrice.csv";
		
	public ArrayList<Stock> getStockExchangeData() 
	{
		ArrayList<Stock> stockList = new ArrayList<Stock>();
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		try //block of code that reads file that may fail because of external factors`
		{
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) 
			{
				String[] stock = line.split(cvsSplitBy); //splits w/ commas
				Stock spObject = new Stock (stock[0], Double.parseDouble(stock[1])); //parseDouble converts into a double
				stockList.add(spObject);
			}
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace(); //will throw an exception if the file is not found
		} catch (IOException e) 
		{
			e.printStackTrace(); //file exists, but corrupt (input/output)
		} finally { //will always be executed
			if (br != null) 
			{
				try { //close file
					br.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return stockList;
	}
}
