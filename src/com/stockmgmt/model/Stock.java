package com.stockmgmt.model;

public class Stock 
{
    private String symbol;
    private double exchangePrice;

    public Stock(String symbol, double exchangePrice) 
    {
        this.symbol = symbol;
        this.exchangePrice = exchangePrice;
    }

    public String getSymbol() 
    {
        return symbol;
    }

    public void setSymbol(String symbol) 
    {
        this.symbol = symbol;
    }

    public double getExchangePrice()
    {
        return exchangePrice;
    }

    public void setExchangePrice(double exchangePrice) 
    {
        this.exchangePrice = exchangePrice;
    }
}
