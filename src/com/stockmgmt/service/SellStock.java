package com.stockmgmt.service;

import java.util.ArrayList;
import com.stockmgmt.interfaces.InterfaceSell;
import com.stockmgmt.model.BalanceAmount;
import com.stockmgmt.model.Stock;
import com.stockmgmt.model.StockHolding;

public class SellStock implements InterfaceSell
{
	
    public String sellStock(String stockToSell, int quantityToSell) 
    {
    	int currentQuantity = -1;
    	ArrayList<StockHolding> stockHoldingArray = new ArrayList<StockHolding>();
    	GetStockHolding stockHolding = new GetStockHolding();
    	
    	stockHoldingArray = stockHolding.getStockHolding();
    	
    	for (StockHolding thisStock : stockHoldingArray)
    	{
    		if (thisStock.getStockSymbol().equals(stockToSell)) 
    		{
				currentQuantity = thisStock.getStockQuantity();
				break;
			}
    	}
    	
    	if (currentQuantity == -1) 
    	{
    		return "You do not hold this stock..";
		} else if ( currentQuantity < quantityToSell ) 
		{
			return "Insufficent stock quantity in your portfolio";
		} else 
		{
			double exchangePrice = -1;
			ArrayList<Stock> stockList = new ArrayList<Stock>();
			GetStockExchangeData stockExchData = new GetStockExchangeData();
			stockList = stockExchData.getStockExchangeData();
			for (Stock thisStock : stockList) 
			{
				if (thisStock.getSymbol().equals(stockToSell))
				{
					exchangePrice = thisStock.getExchangePrice();
					break;
				}
			}
			
			StockHolding sh = new StockHolding(stockToSell, quantityToSell, exchangePrice);
			
			BalanceAmount.balanceAmount = BalanceAmount.balanceAmount + (quantityToSell * exchangePrice);
			
			StockHoldingManager shMgr = new StockHoldingManager();
			shMgr.updateTradeFile(sh, "SELL");
			return "Stock: " + stockToSell + " Sold; Updated Balance: " + BalanceAmount.balanceAmount;
    	}
    }
}