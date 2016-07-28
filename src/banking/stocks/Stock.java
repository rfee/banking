package banking.stocks;

/**
 *
 * Notes
 * 1. We use an interface rather than extending a base class here because we are
 *    not familiar with the nature of stock.
 * 2. We probably should use a symbol type class rather than String if symbol
 *    names have some rules e.g. 3 letter names only.
 * 3. All money is represented as longs rather than floating point because
 *    floating point are approximations to actual numbers and are never exact. 
 * 
 * @author robertfee
 */
public interface Stock{

    public String getSymbol();

    public double getDividendYield(long price) throws StockCalcException;

    public double getPERatio(long price) throws StockCalcException;
}
