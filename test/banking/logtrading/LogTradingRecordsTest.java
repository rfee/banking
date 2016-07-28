package banking.logtrading;

import banking.stocks.CommonStock;
import banking.stocks.PreferredStock;
import banking.stocks.Stock;
import banking.stocks.StockCalcException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author robertfee
 */

public class LogTradingRecordsTest {

    private LogTradingRecords logTradingRecords;

    public LogTradingRecordsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        try {
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

        } catch (LogTradeException | StockCalcException ex) {
            fail("Fail in setup " + ex.getMessage());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getVolumeWeightedStockPriceOnTrades method, of class
     * LogTradingRecords.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetVolumeWeightedStockPriceOnTrades() throws Exception {
        System.out.println("getVolumeWeightedStockPriceOnTrades");
        String symbol = "TEA";
        int inPastMinutes = 5;
        double expResult = 14.815;
        double result = logTradingRecords.getVolumeWeightedStockPriceOnTrades(symbol, inPastMinutes);
        assertEquals(expResult, result, 0.001);
    }

    /**
     * Test of getGBCEAllShareIndexForallStocks method, of class
     * LogTradingRecords.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetGBCEAllShareIndexForallStocks() throws Exception {
        System.out.println("getGBCEAllShareIndexForallStocks");
        double expResult = 192.673;
        double result = logTradingRecords.getGBCEAllShareIndexForallStocks();
        assertEquals(expResult, result, 0.001);
    }

}
