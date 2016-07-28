package banking.stocks;

import java.util.logging.Level;
import java.util.logging.Logger;
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
public class PreferredStockTest {
    private PreferredStock preferredStock;
    
    public PreferredStockTest() {
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
            preferredStock=new PreferredStock("GIN",8,2,100);
        } catch (StockCalcException ex) {
            fail("in set up "+ex.getMessage());
        }
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void testGetDividendYield() throws Exception {
        System.out.println("getDividendYield");
        long price = 100;
        double expResult = 2;
        double result = preferredStock.getDividendYield(price);
        assertEquals(expResult, result, 0.1);
    }

    
    @Test
    public void testGetPERatio() throws Exception {
        System.out.println("getPERatio");
        long price = 80;
        double expResult = 10;
        double result = preferredStock.getPERatio(price);
        assertEquals(expResult, result, 0.1);
    }

    
    @Test
    public void testGetSymbol() {
        System.out.println("getSymbol");
        String expResult = "GIN";
        String result = preferredStock.getSymbol();
        assertEquals(expResult, result);
    }

   
    @Test
    public void testGetLastDividend() {
        System.out.println("getLastDividend");
        long expResult = 8;
        long result = preferredStock.getLastDividend();
        assertEquals(expResult, result);
    }

   
    @Test
    public void testGetFixedDividend() {
        System.out.println("getFixedDividend");
        long expResult = 2;
        long result = preferredStock.getFixedDividend();
        assertEquals(expResult, result);
    }

    
    @Test
    public void testGetParValue() {
        System.out.println("getParValue");
        long expResult = 100;
        long result = preferredStock.getParValue();
        assertEquals(expResult, result);
    }
    
}
