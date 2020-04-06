package com.stockmgmt.service;

import java.util.ArrayList;

import com.stockmgmt.interfaces.InterfaceBuy;
import com.stockmgmt.model.StockHolding;
import com.stockmgmt.model.BalanceAmount;
import com.stockmgmt.model.Stock;

public class BuyStock implements InterfaceBuy 
{
	public String buyStock(String symbol, int quantity)
	{
		StockHolding sh = new StockHolding(symbol, quantity, null);
		Double currentBalance = BalanceAmount.balanceAmount;
		System.out.println("Current Bal: " + currentBalance);

		double exchangePrice = -1;
		
		ArrayList<Stock> stockList = new ArrayList<Stock>();
		GetStockExchangeData stockExchData = new GetStockExchangeData();
		stockList = stockExchData.getStockExchangeData();
		
		for (Stock thisStock : stockList) 
		{
			if (thisStock.getSymbol().equals(symbol)) 
			{
				exchangePrice = thisStock.getExchangePrice();
				break;
			}
		}

		if (exchangePrice == -1) 
		{
			return "Stock information not available..";
		} else if ( currentBalance < (quantity * exchangePrice) )
		{
			return "Insufficent Balance";
		} else 
		{
			sh.setStockSymbol(symbol);
			sh.setStockQuantity(quantity);
			sh.setStockPurchasePrice(exchangePrice);
			BalanceAmount.balanceAmount = currentBalance - (quantity * exchangePrice); //subtracts from balance
			
			StockHoldingManager shMgr = new StockHoldingManager(); //updates file
			shMgr.updateTradeFile(sh, "BUY");
			return "Stock: " + symbol + " Bought; Updated Balance: " + BalanceAmount.balanceAmount;
		}
	}
}
