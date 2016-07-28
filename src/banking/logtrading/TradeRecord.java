package banking.logtrading;

import java.util.Date;
import banking.stocks.Stock;
import java.util.Objects;

/**
 *
 * @author robertfee
 */
/* needs to public if used in Java stream  rather than just package visable */
final public class TradeRecord implements Comparable {
/*  Timestamp as a long is a bit more useful in a high activity log. If we
    decide to use a Date type later we will need to remember to take a copy
    of the object when we create a trade record instance and do the same in
    the getter. We would the log record times tampered with accidently.
    */
    private final Stock stock;
    private final long timestamp; 
    private final long quantity;
    private final TradeType tradeType;
    private final long price;

    public TradeRecord(final Stock stock, final Date timestamp, final long quantity, final TradeType tradeType, final long price)
            throws LogTradeException {
        if (stock == null)
            throw new LogTradeException("Null stock in trade record");
        else if (timestamp == null)
            throw new LogTradeException("Null timestamp in trade record");
        else {
            this.stock = stock;
            this.timestamp = timestamp.getTime();
            this.quantity = quantity;
            this.tradeType = tradeType;
            this.price = price;
        }
    }

    public Stock getStock() {
        return stock;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public long getQuantity() {
        return quantity;
    }

    public TradeType getTradeType() {
        return tradeType;
    }

    public long getPrice() {
        return price;
    }

    @Override
    public int compareTo(Object object) {
        if ((object == null) || (getClass() != object.getClass()))
            return 1;
        else {
            TradeRecord other = (TradeRecord) object;
            if (this.timestamp > other.timestamp)
                return -1;
            else
                if (this.timestamp < other.timestamp)
                    return 1;
                else
                    return 0;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.stock);
        hash = 83 * hash + (int) (this.timestamp ^ (this.timestamp >>> 32));
        hash = 83 * hash + (int) (this.quantity ^ (this.quantity >>> 32));
        hash = 83 * hash + Objects.hashCode(this.tradeType);
        hash = 83 * hash + (int) (this.price ^ (this.price >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final TradeRecord other = (TradeRecord) obj;
        if (this.timestamp != other.timestamp)
            return false;
        if (this.quantity != other.quantity)
            return false;
        if (this.price != other.price)
            return false;
        if (!Objects.equals(this.stock, other.stock))
            return false;
        return this.tradeType == other.tradeType;
    }

}
