package banking.statistics;

import java.util.List;

/**
 * Geometric Mean is put in a Library because
 *  1. It maybe useful to someone else.
 *  2. In general for complex statistical equations that involve real 
 *     numbers you are best using special purpose library routines 
 *     written by an expert in numerical analysis (and even then!). 
 * 
 * @author robertfee
 */
public class StatisticsLibrary {

    static public double getGeometricMean(double... values) {
        double times;
        times = 1.0;
        if ((values == null) || (values.length == 0))
            return 0;
        else
            for (double val : values)
                times *= val;
        return Math.pow(times, ((double) 1 / (double) values.length));
    }

    
    
    static public double getGeometricMean(List<Double> values) {
        /* Netbeans created this Java 8 stream here automatically. I am not 
           convinced that this code is neater than the above.
        */
        double times = 1;
        if ((values == null) || (values.isEmpty()))
            return 0;
        else
            times = values.stream().map((val) -> val).reduce(times, (accumulator, _item) -> accumulator * _item);
        return Math.pow(times, ((double) 1 / (double) values.size()));
    }
    

}
