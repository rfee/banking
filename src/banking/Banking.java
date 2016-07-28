package banking;

import banking.logtrading.LogTradeException;
import banking.logtrading.LogTradingRecords;
import banking.logtrading.TradeType;
import banking.stocks.CommonStock;
import banking.stocks.PreferredStock;
import banking.stocks.Stock;
import banking.stocks.StockCalcException;
import java.util.Arrays;

/**
 *
 * @author robertfee
 */
public class Banking {

   private LogTradingRecords logTradingRecords;

    
    public static void main(String[] args) {
        Banking banking;
        
        try {
            banking=new Banking();
            banking.reportAllVolumeWeightedStockPrice();
            banking.reportAllSummary();
            System.exit(0);
        } catch (LogTradeException | StockCalcException ex) {
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
    }
    
    public Banking() throws LogTradeException, StockCalcException {
        setUpSample();
    }
    
    public void reportAllVolumeWeightedStockPrice() throws LogTradeException{
        for(String symbol:Arrays.asList("TEA","POP","ALE","GIN","JOE")){
           System.out.println(symbol+"="+logTradingRecords.getVolumeWeightedStockPriceOnTrades(symbol,100));
        }
    }
    
    public void reportAllSummary() throws LogTradeException{
        System.out.println(logTradingRecords.getGBCEAllShareIndexForallStocks());
    }
    
    private void setUpSample() throws LogTradeException, StockCalcException {
        logTradingRecords = new LogTradingRecords();
        Stock tea = new CommonStock("TEA", 0, 100);
        Stock pop = new CommonStock("POP", 8, 100);
        Stock ale = new CommonStock("ALE", 23, 60);
        Stock gin = new PreferredStock("GIN", 8, 2, 100);
        Stock joe = new CommonStock("JOE", 13, 250);
        logTradingRecords.logTrade(joe, 67, TradeType.SELL, 780);
        logTradingRecords.logTrade(tea, 100, TradeType.BUY, 8);
        logTradingRecords.logTrade(pop, 45, TradeType.BUY, 77);
        logTradingRecords.logTrade(ale, 2000, TradeType.SELL, 678);
        logTradingRecords.logTrade(gin, 8, TradeType.BUY, 2);
        logTradingRecords.logTrade(pop, 150, TradeType.SELL, 889);
        logTradingRecords.logTrade(tea, 8, TradeType.BUY, 100);
        logTradingRecords.logTrade(gin, 1, TradeType.BUY, 56);
        logTradingRecords.logTrade(gin, 76, TradeType.BUY, 43);        
        logTradingRecords.logTrade(joe, 567, TradeType.SELL, 980);
    }
    
    
    
}
