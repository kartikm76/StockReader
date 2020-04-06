package com.stockmgmt.model;

public class StockHolding 
{
    private String stockSymbol;
	private int stockQuantity;
    private Double stockPurchasePrice;
       
   public StockHolding(String stockSymbol, int stockQuantity, Double stockPurchasePrice) 
   {
		super();
		this.stockSymbol = stockSymbol;
		this.stockQuantity = stockQuantity;
		this.stockPurchasePrice = stockPurchasePrice;
	}
    
	public String getStockSymbol() 
	{
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) 
    {
        this.stockSymbol = stockSymbol;
    }

    public int getStockQuantity() 
    {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Double getStockPurchasePrice() {
        return stockPurchasePrice;
    }

    public void setStockPurchasePrice(Double stockPurchasePrice) {
        this.stockPurchasePrice = stockPurchasePrice;
    }
}
