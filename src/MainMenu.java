/* PreCondition: User must have a profile, and must have more than 0 dollars. User must also 
 * have options he or she can choose
 
 * PostCondition: User must pick one of the four options
 * Goals: Allow the User to interact with the program
 */

import com.stockmgmt.model.BalanceAmount;
import com.stockmgmt.model.Stock;
import com.stockmgmt.model.StockHolding;
import com.stockmgmt.service.StockPerformance;
import com.stockmgmt.service.BuyStock;
import com.stockmgmt.service.GetStockExchangeData;
import com.stockmgmt.service.GetStockHolding;
import com.stockmgmt.service.SellStock;
import com.stockmgmt.service.StockAllocationReport;
import com.stockmgmt.service.TradeActivityReport;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.SwingUtilities;
import com.stockmgmt.service.UserSettings;

public class MainMenu {
		
	public static void main(String[] args)
	{	
		String login = "";
		Scanner scanObj = new Scanner(System.in);
		
		System.out.println("\t\t\t*********************************\t\t\t");
		System.out.println("\t\t\t  Welcome to Super Stocks!\t\t\t");
		System.out.println("\t\t\t*********************************\t\t\t");
		System.out.println("");

		System.out.print("Enter your login: ");
		login = scanObj.nextLine().trim(); //removes white spaces
		
		UserSettings userSettingService = new UserSettings();
		boolean loginFound = userSettingService.findUserLogin(login);
		
		if (loginFound) {
			System.out.println("**************************************");
			System.out.println("SUCCESS: You can start trading Stocks");
			System.out.println("**************************************");
			System.out.println("");
			
			BalanceAmount.balanceAmount = 5000; //only place to change balance
			int selectedOption = 0;

			while (selectedOption < 1 || selectedOption > 5) {
				System.out.println("**************************");
				System.out.println("Main Menu: ");
				System.out.println("**************************");
				System.out.println("");
				
				System.out.println("1. Buy a Stock"); 
				System.out.println("2. Sell a Stock");
				System.out.println("3. Stock Allocation Report");
				System.out.println("4. Trade Activity Report");
				System.out.println("5. My Stock Performance Report");
				System.out.println("6. Update User Settings");
				System.out.println("");
				System.out.print("Enter your option: ");
				System.out.println("");

				Scanner optionObj = new Scanner(System.in);
				selectedOption = optionObj.nextInt();

				if(selectedOption == 1) {
					String buyStockSymbol;
					int buyStockQuantity;

					ArrayList<Stock> stockList = new ArrayList<Stock>();
					GetStockExchangeData stockExchData = new GetStockExchangeData();
					stockList = stockExchData.getStockExchangeData(); 

					System.out.println("*** Exchange Data ***");
					System.out.println("");
					
					String leftAlignFormat = "| %-15s | %10.2f |%n"; //puts in a table format

					System.out.format("+-----------------+------------+%n");
			    	System.out.format("| Stock           | Exch Price |%n");
			    	System.out.format("+-----------------+------------+%n");
			   
			    	for (Stock thisStock : stockList) 
			    	{
			    	    System.out.format(leftAlignFormat, thisStock.getSymbol(), thisStock.getExchangePrice());
			    	}
			    	System.out.format("+-----------------+------------+%n");
			    	System.out.println("");
			    	
					Scanner buyObj = new Scanner(System.in);

					System.out.print("Enter Stock Symbol from the exchange: ");
					buyStockSymbol = buyObj.nextLine().trim();

					System.out.print("Enter Stock Quanity: ");
					buyStockQuantity = buyObj.nextInt();

					BuyStock bs = new BuyStock();

					String buyStatus = bs.buyStock(buyStockSymbol, buyStockQuantity);
					System.out.println(buyStatus);
					buyObj.close();
					optionObj.close();
				}

				if(selectedOption == 2) 
				{
					String sellStockSymbol;
					int sellStockQuantity;

					Scanner sellObj = new Scanner(System.in);
					
					ArrayList<StockHolding> stockHoldingArray = new ArrayList<StockHolding>();
			    	GetStockHolding stockHolding = new GetStockHolding();
			    	
			    	stockHoldingArray = stockHolding.getStockHolding();
			    	
			    	System.out.println("*** Stock Holdings ***");
					System.out.println("");
					
			    	String leftAlignFormat = "| %-15s | %-4d | %10.2f | %n";

			    	System.out.format("+-----------------+------+------------+%n");
			    	System.out.format("| Stock           | Qty  | Buy Price  | %n");
			    	System.out.format("+-----------------+------+------------+%n");
			   
			    	for (StockHolding thisStock : stockHoldingArray) 
			    	{
			    	    System.out.format(leftAlignFormat, thisStock.getStockSymbol(), thisStock.getStockQuantity(), thisStock.getStockPurchasePrice());
			    	}
			    	System.out.format("+-----------------+------+------------+%n");
			    	System.out.println("");
			    	
					System.out.print("Enter Stock Symbol from the Holding list: ");
					sellStockSymbol = sellObj.nextLine().trim();

					System.out.print("Enter Stock Quanity: ");
					sellStockQuantity = sellObj.nextInt();

					SellStock ss = new SellStock();

					String sellStatus = ss.sellStock(sellStockSymbol, sellStockQuantity);
					System.out.println(sellStatus);
					
					sellObj.close();
					optionObj.close();
				}

				if(selectedOption == 3) 
				{	
					StockAllocationReport stockReport = new StockAllocationReport();
			    	stockReport.setVisible(true);
				}
				
				if(selectedOption == 4) 
				{	
					new TradeActivityReport();
				}
				
				if(selectedOption == 5) 
				{	
			        SwingUtilities.invokeLater(() -> {
			            StockPerformance ex = new StockPerformance();
			            ex.setVisible(true);
			        });
				}
				
				if(selectedOption == 6) 
				{	
					userSettingService.setUserDetails();
				}
			}
		}
		
		if (!loginFound) {
			System.out.println("");
			System.out.println("****************************************************************************");
			System.out.println("*** WARN: You will have to register yourself before you can trade stocks ***");
			System.out.println("****************************************************************************");
			System.out.println("");
			
			userSettingService.setUserDetails();
		}
		scanObj.close();
	}
}
