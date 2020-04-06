package com.stockmgmt.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.stockmgmt.model.Stock;

public class StockPerformance extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static String csvFile = "./data/StockPrice.csv";
	private static ArrayList<Stock> stockPriceArray = new ArrayList<Stock>();
	
	public StockPerformance () 
	{
		initUI(); 
	}
	
	private void initUI() 
	{
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		
		try
		{
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) 
			{
				String[] thisStock = line.split(cvsSplitBy);
				Stock sP = new Stock(thisStock[0], Double.parseDouble(thisStock[1]));
				stockPriceArray.add(sP);
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
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		
		XYSeries series = null;
		XYSeriesCollection dataset = new XYSeriesCollection();
		for (Stock thisStock : stockPriceArray) 
		{
			series = createDataset(thisStock.getSymbol(), thisStock.getExchangePrice());
			dataset.addSeries(series);
    	}
	                
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Stock Performance Report");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //private XYDataset createDataset(String symbol, Double price) 
    private XYSeries createDataset(String symbol, Double price) //range of 10% down 10% up 
    {
    	double min = price - ((price * 10) / 100); 
		double max = price + ((price * 10) / 100);
    	
        XYSeries series = new XYSeries(symbol);
     
        series.add(9.00, getRandomNumber(min, max)); //when stock exchange opens
        series.add(10.00, getRandomNumber(min, max));
        series.add(11.00, getRandomNumber(min, max));
        series.add(12.00, getRandomNumber(min, max));
        series.add(13.00, getRandomNumber(min, max));
        series.add(14.00, getRandomNumber(min, max));
        series.add(15.00, getRandomNumber(min, max));
        series.add(16.00, getRandomNumber(min, max)); //when stock exchange closes
       
        return series;
    }

    private double getRandomNumber (Double min, Double max) 
    {
    	return min + (int)(Math.random() * ((max - min) + 1)); 
    }
    
    private JFreeChart createChart(final XYDataset dataset) 
    {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Stock Performance", 
                "Hours",
                "Stock Price",
                dataset, 
                PlotOrientation.VERTICAL,
                true, 
                true, 
                false
        );

        XYPlot plot = chart.getXYPlot();
        
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        
        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesStroke(1, new BasicStroke(2.0f));
        
        renderer.setSeriesPaint(2, Color.BLACK);
        renderer.setSeriesStroke(2, new BasicStroke(2.0f));        
        
        renderer.setSeriesPaint(3, Color.CYAN);
        renderer.setSeriesStroke(3, new BasicStroke(2.0f));        
        
        renderer.setSeriesPaint(4, Color.GRAY);
        renderer.setSeriesStroke(4, new BasicStroke(2.0f));        
        
        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(false);
        plot.setDomainGridlinesVisible(false);
        
        //DateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, ''yy");
	    //Date date = new Date();
	    String pattern = "EEE, MMM d, ''yy";
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	    String date = simpleDateFormat.format(new Date());
        
	    chart.setTitle(new TextTitle("Stock Performance on: " + date,
                        new Font("Serif", Font.BOLD, 18)
                )
        );

        return chart;
    }
}
