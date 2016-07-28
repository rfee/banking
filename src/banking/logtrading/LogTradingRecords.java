package banking.logtrading;

import banking.statistics.StatisticsLibrary;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import banking.stocks.Stock;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 *
 * @author robertfee
 */
public class LogTradingRecords {

    /*
In this case the most obvious log structure would have been a linear list. It is 
what most people expect and hence no surprise for the maintainer.  It is also a 
good idea to keep it simple as we have been given an incomplete specification. 
However it is worth thinking about the log structure as it can keep the code simple
and aid performance especially if the structure is in the file store or the database. 
Some questions we should ask are
	How many log records are expected to be kept?
	What is the frequency of the log updates?
	What is the frequency of the log reads?
	What is the number of different types of stock expected?
Normally the log structure is chosen to make appending quick as usually business 
processes that cannot be held up do the appending. On the hand reporting tools
do the reading and do not need to be so quick. However it is best to find out who
our readers are. An important business process could be triggered when an event happens 
in the log.
In a concurrent environment this becomes more complex as readers need to be quick to
avoid appending being held up. Here we use a log structure indexed on stock key.

     */
    /* Use Map and List rather than a particular implementation such as HashNap
    and ArrayList to keep our implementation choices open */
    private final Map<String, List<TradeRecord>> eachStocksTradeLog;

    public LogTradingRecords() {
        this.eachStocksTradeLog = new HashMap<>();
    }

    public void logTrade(Stock stock, long quantity, TradeType tradeType, long price)
            throws LogTradeException {
        List<TradeRecord> tradeLog;

        if ((stock == null) || (stock.getSymbol() == null))
            throw new LogTradeException("Null stock was logged");
        else {
            tradeLog = this.eachStocksTradeLog.get(stock.getSymbol());
            if (tradeLog == null) {
                tradeLog = new ArrayList<>();
                this.eachStocksTradeLog.put(stock.getSymbol(), tradeLog);
            }
            tradeLog.add(new TradeRecord(stock, new Date(), quantity, tradeType, price));
        }
    }
    
    /* Rather than just find 5 minutes ago we make the method more general. 
       Who can believe users will just stick to wanting 5 minutes ago.
    */

    public double getVolumeWeightedStockPriceOnTrades(String symbol, int inPastMinutes)
            throws LogTradeException {
        int oldestPositionInPastMinutes;
        List<TradeRecord> tradeLogForSymbol;

        tradeLogForSymbol = this.eachStocksTradeLog.get(symbol);
        if ((tradeLogForSymbol == null) || (tradeLogForSymbol.isEmpty()))
            return 0;
        else {
            oldestPositionInPastMinutes = getOldestRecordPositionMinutesAgo(tradeLogForSymbol, inPastMinutes);
            if (oldestPositionInPastMinutes>= tradeLogForSymbol.size())
                return 0;
            else
                return getAllVolumeWeightedStockPriceOnTrades(
                        tradeLogForSymbol.subList(oldestPositionInPastMinutes, tradeLogForSymbol.size()));
        }
    }

    public double getGBCEAllShareIndexForallStocks() throws LogTradeException {
        List<Double> allVolumeWeightedStockPrices;

        allVolumeWeightedStockPrices = new LinkedList();
        for (List<TradeRecord> specificStockLog : this.eachStocksTradeLog.values())
            allVolumeWeightedStockPrices.add(getAllVolumeWeightedStockPriceOnTrades(specificStockLog));
        return StatisticsLibrary.getGeometricMean(allVolumeWeightedStockPrices);
    }

    private int getOldestRecordPositionMinutesAgo(List<TradeRecord> tradeLog, int minutesAgo) 
            throws LogTradeException {
        long pastTimestamp;
        int oldestPos;
        ListIterator<TradeRecord> listIter;
        pastTimestamp = getTimeStampAtPastMinutesAgo(minutesAgo);
        oldestPos = tradeLog.size();
        listIter = tradeLog.listIterator(tradeLog.size());
        while (listIter.hasPrevious() && (listIter.previous().getTimestamp() >= pastTimestamp))
            oldestPos--;
        return oldestPos;
    }

    private long getTimeStampAtPastMinutesAgo(int minutesAgo) throws LogTradeException {
        Calendar calendar;
        if (minutesAgo < 0)
            throw new LogTradeException("Past minutes in the future");
        else {
            calendar = new GregorianCalendar();
            calendar.add(Calendar.MINUTE, -minutesAgo);
            return calendar.getTime().getTime();
        }
    }

   

    /* Not everywhere is Java 8 ready so old Java version listed below */
    private double getAllVolumeWeightedStockPriceOnTrades(List<TradeRecord> stockTradeLog)
            throws LogTradeException {
        long sumQuantity = 0;
        long sumPriceTimesQuantity = 0;

        if (stockTradeLog == null)
            return 0;
        else
            for (TradeRecord tradeRecord : stockTradeLog) {
                sumPriceTimesQuantity += (tradeRecord.getQuantity() * tradeRecord.getPrice());
                sumQuantity += tradeRecord.getQuantity();
            }
        if (sumQuantity == 0)
            return 0;
        else
            return (double) sumPriceTimesQuantity / (double) sumQuantity;
    }
    
     /* Java 8 version 
    private double getAllVolumeWeightedStockPriceOnTrades(List<TradeRecord> stockTradeLog)
            throws LogTradeException {
        VolumeWeighting volumeWeighting;
        if (stockTradeLog == null)
            return 0;
        else {
            volumeWeighting = stockTradeLog.stream()
                    .collect(VolumeWeighting::new, VolumeWeighting::accept, VolumeWeighting::combine);
            return volumeWeighting.totalVolumeWeighting();
        }
    }
 */
}
