	package com.stockmgmt.service;

import com.stockmgmt.model.StockHolding;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class StockHoldingManager 
{

	private static String cvsSplitBy = ",";
	BufferedReader br = null;
	BufferedWriter bw = null;
	
    public void updateTradeFile(StockHolding sh, String tradeType) 
    {
    	String content = null;
	    String line = "";
	    
	    try 
	    {
	    	br = new BufferedReader (new FileReader ("./data/StockHolding.csv"));
		    bw = new BufferedWriter (new FileWriter ("./data/StockHoldingNew.csv"));
		    	
	    	boolean stockInHoldingFound = false;
	    	ArrayList<String> stockHoldingArray = new ArrayList<String>();
		    while ((line = br.readLine()) != null) 
		    {
		    	String [] values = line.split(cvsSplitBy);
		    	
		    	if (tradeType == "BUY") 
		    	{	
		    		for (String element: values) //if already there
		    		{
		    			if (element.equals(sh.getStockSymbol())) 
		    			{
		    				stockInHoldingFound = true;
			    			int updatedQuantity = Integer.valueOf(values[1]) + sh.getStockQuantity();
			    			content = sh.getStockSymbol() + ","
			    					+ updatedQuantity + ","
				                    + sh.getStockPurchasePrice();
			    			break;
		    			}
		    		}
					
					if (!stockInHoldingFound) //keeps original file
					{
						content = values[0] + "," + values[1] + "," + values[2];
					}
					
					stockHoldingArray.add(values[0]);
					
					bw.write(content);
			    	bw.newLine();
			    	if (stockInHoldingFound) 
			    	{ 
			    		stockInHoldingFound = false; 
			    	}
		    	}
		    	
		    	if (tradeType == "SELL") 
		    	{
		    		stockInHoldingFound = false;
		    		for (String element: values) {
		    			if (element.equals(sh.getStockSymbol())) 
		    			{
		    				stockInHoldingFound = true;
		 
			    			int updatedQuantity = Integer.valueOf(values[1]) - sh.getStockQuantity();
			    			if (updatedQuantity > 0) 
			    			{
				    			content = sh.getStockSymbol() + ","
				    					+ updatedQuantity + ","
					                    + sh.getStockPurchasePrice();
			    			}
			    			
			    			if (updatedQuantity == 0) //deletes record if sell 10 for 10 
			    			{
			    				content = "";
			    			}
			    			break;
		    			}
		    			
		    			if (!stockInHoldingFound) 
		    			{
							content = values[0] + "," + values[1] + "," + values[2];
						}
		    		}
		    		if (!content.equals("")) 
		    		{
		    			bw.write(content);
		    			bw.newLine();
		    			if (stockInHoldingFound) 
		    			{ 
		    				stockInHoldingFound = false; 
		    			}
		    		}
		    	}
		    }
		    
		    if (tradeType == "BUY" && (!stockHoldingArray.contains(sh.getStockSymbol()))) //if new symbol wanting to come for the first time,then create a new entry 
		    {
		    	content = sh.getStockSymbol() + ","
					+ sh.getStockQuantity() + ","
                    + sh.getStockPurchasePrice();
		    	bw.write(content);
		    	bw.newLine();
		    	//System.out.println("File Contents: " + content);
		    }
	    } catch (FileNotFoundException e) 
	    {
			e.printStackTrace();
		} catch (IOException e) 
	    {
			e.printStackTrace();
		} finally 
	    {
			if (br != null) 
			{
				try 
				{
					br.close();
					bw.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	    
	    try //create new file to replace old one 
	    {	
    		File oldFile = new File("./data/StockHolding.csv");
    		File newFile = new File("./data/StockHoldingNew.csv");
        	
    		if(oldFile.delete())
    		{
    			//System.out.println(oldFile.getName() + " is deleted!");
    			    			
    			if(!newFile.renameTo(oldFile))
    			{
    				System.out.println("Rename failed");
    			}
    		} 
    		else 
    		{
    			System.out.println("Delete operation is failed.");
    		}  	   
    	} catch(Exception e)
	    {
    		e.printStackTrace();
    	}
	    
	    writeTradeActivity(content, tradeType);
	    
	    ArrayList<StockHolding> latestStockHoldingArray = new ArrayList<StockHolding>();
    	GetStockHolding stockHolding = new GetStockHolding();
    	
    	latestStockHoldingArray = stockHolding.getStockHolding();
    	
    	System.out.println("");
    	String leftAlignFormat = "| %-15s | %-4d | %10.2f | %n";
    	System.out.format("+-----------------+------+------------+%n");
    	System.out.format("| Stock           | Qty  | Buy Price  | %n");
    	System.out.format("+-----------------+------+------------+%n");
   
    	for (StockHolding thisStock : latestStockHoldingArray) 
    	{
    	    System.out.format(leftAlignFormat, thisStock.getStockSymbol(), thisStock.getStockQuantity(), thisStock.getStockPurchasePrice());
    	}
    	System.out.format("+-----------------+------+------------+%n");
    	System.out.println("");
    }
    
    private void writeTradeActivity (String content, String tradeType)
    //shows all trade activity
    {
    	String file = "./data/TradeActivity.csv";
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    Date date = new Date();
        try (
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(writer)) 
        {
    		content = content + ","
    				+ tradeType + ","
    				+ dateFormat.format(date);
            bw.write(content);
            bw.newLine();
        } catch (IOException e) 
        {
            System.err.format("IOException: %s%n", e);
        }
    }
}
