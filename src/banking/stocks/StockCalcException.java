package banking.stocks;

/**
 * Use our own exception to help keep our implementation hidden.
 * @author robertfee
 */
public class StockCalcException extends Exception {

    
    public StockCalcException() {
    }

    
    public StockCalcException(String msg) {
        super(msg);
    }
}
