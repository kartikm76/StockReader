package com.stockmgmt.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TradeActivityReport 
{

	public TradeActivityReport() 
	{
		String line = "";
		String cvsSplitBy = ",";
		BufferedReader br = null;
		
		String[][] data = new String[100][100];
			    
		JFrame f = new JFrame(); 
	    
	    // Frame Title 
	    f.setTitle("My Trading Activity Report"); 
	
	    // Data to be displayed in the JTable
	    
	    try {
	    	br = new BufferedReader (new FileReader ("./data/TradeActivity.csv"));

	    	int i = 0;
		    while ((line = br.readLine()) != null) 
		    {
		    	System.out.println(line);
		    	String[] values = line.split(cvsSplitBy);
		    	for (int j=0; j<= values.length-1; j++) 
		    	{
		    		data[i][j] = values[j];
		    	}
		    	i++;
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
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	   
	    // Column Names 
	    String[] columnNames = {"Stock Name", "Quantity", "Trade Price", "Trade Type", "Trade DateTime"}; 
	
	    // Initializing the JTable 
	    JTable j = new JTable(data, columnNames); 
	    j.setBounds(130, 40, 200, 300); 
	
	    // adding it to JScrollPane 
	    JScrollPane sp = new JScrollPane(j); 
	    f.add(sp); 
	    // Frame Size 
	    f.setSize(800, 400); 
	    // Frame Visible = true 
	    f.setVisible(true);
	}
}
