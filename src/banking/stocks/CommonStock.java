package banking.stocks;

import java.util.Objects;

/**
 * Looks like an immutable class in the specification so make it so.
 *
 * @author robertfee
 */
final public class CommonStock implements Stock {

    private final String symbol;
    private final long lastDividend;
    private final long parValue;

    public CommonStock(final String symbol, final long lastDividend, final long parValue)
            throws StockCalcException {
        if (symbol == null)
            throw new StockCalcException("Null symbol in stock");
        else {
            this.symbol = symbol;
            this.lastDividend = lastDividend;
            this.parValue = parValue;
        }
    }

    @Override
    public double getDividendYield(long price) throws StockCalcException {
        if (price == 0)
            throw new StockCalcException("zero price in dividend yield");
        else
            return this.lastDividend / price;
    }

    @Override
    public double getPERatio(long price) throws StockCalcException {
        if (this.lastDividend == 0)
            throw new StockCalcException("zero price in PE Ratio");
        else
            return price / this.lastDividend;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    public long getLastDividend() {
        return lastDividend;
    }

    public long getParValue() {
        return parValue;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.symbol);
        hash = 17 * hash + (int) (this.lastDividend ^ (this.lastDividend >>> 32));
        hash = 17 * hash + (int) (this.parValue ^ (this.parValue >>> 32));
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
        final CommonStock other = (CommonStock) obj;
        if (this.lastDividend != other.lastDividend)
            return false;
        if (this.parValue != other.parValue)
            return false;
        if (!Objects.equals(this.symbol, other.symbol))
            return false;
        return true;
    }

}
