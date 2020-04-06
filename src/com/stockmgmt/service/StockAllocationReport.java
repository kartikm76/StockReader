//http://zetcode.com/java/jfreechart/

package com.stockmgmt.service;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import com.stockmgmt.model.BalanceAmount;

public class StockAllocationReport extends JFrame 
{
    
	private static final long serialVersionUID = 1L;

	public StockAllocationReport() 
	{
        initUI();
    }

    private void initUI() 
    {
        DefaultPieDataset dataset = createDataset();

        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Pie chart");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private DefaultPieDataset createDataset() 
    {
    	String line = "";
        String cvsSplitBy = ",";
        Double investedAmount = 0.0;
        BufferedReader br = null;
    	DefaultPieDataset dataset = new DefaultPieDataset();
        
    	try 
    	{
    		br = new BufferedReader (new FileReader ("./data/StockHolding.csv"));
	    
		    while ((line = br.readLine()) != null)
		    {
		    	String [] values = line.split(cvsSplitBy);
    			dataset.setValue(values[0], (Integer.valueOf(values[1]) * Double.valueOf(values[2])));
    			investedAmount = investedAmount + (Integer.valueOf(values[1]) * Double.valueOf(values[2]));
			}
		    dataset.setValue("Cash Balance", BalanceAmount.balanceAmount - investedAmount);
	    } 
    	catch (FileNotFoundException e) 
    	{
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
    	
        return dataset;
    }

    private JFreeChart createChart(DefaultPieDataset dataset) 
    {
	    JFreeChart barChart = ChartFactory.createPieChart(
	            "My Stock Portfolio",
	            dataset,
	            true, true, false);
	
	    return barChart;
    }
}
