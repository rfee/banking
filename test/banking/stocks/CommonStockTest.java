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
public class CommonStockTest {
    private CommonStock commonStock;
    
    public CommonStockTest() {
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
            commonStock=new CommonStock("POP",8,100);
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
        long price = 4;
        double expResult = 2;
        double result = commonStock.getDividendYield(price);
        assertEquals(expResult, result,0.1);
    }

    
    @Test
    public void testGetPERatio() throws Exception {
        System.out.println("getPERatio");
        long price =450;
        double expResult = 56;
        double result = commonStock.getPERatio(price);
        assertEquals(expResult, result, 0.1);
    }

    
    @Test
    public void testGetSymbol() {
        System.out.println("getSymbol");
        String expResult = "POP";
        String result = commonStock.getSymbol();
        assertEquals(expResult, result);
    }

   
    @Test
    public void testGetLastDividend() {
        System.out.println("getLastDividend");
        long expResult = 8;
        long result = commonStock.getLastDividend();
        assertEquals(expResult, result);
    }

    
    @Test
    public void testGetParValue() {
        System.out.println("getParValue");
        long expResult = 100;
        long result = commonStock.getParValue();
        assertEquals(expResult, result);
    }

   
    
}
