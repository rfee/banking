package banking.logtrading;

import java.util.function.Consumer;

/**
 *
 * @author robertfee
 */
public class VolumeWeighting implements Consumer<TradeRecord>
{
 private long sumQuantity = 0;
 private long sumPriceTimesQuantity = 0;

  public double totalVolumeWeighting(){
        if (sumQuantity == 0)
            return 0;
        else
            return (double) sumPriceTimesQuantity / (double) sumQuantity;
    }
    
        
 @Override
    public void accept(TradeRecord tradeRecord) {
       sumPriceTimesQuantity+=(tradeRecord.getQuantity() * tradeRecord.getPrice());
       sumQuantity  +=tradeRecord.getQuantity();
    }
    public void combine(VolumeWeighting other) {
        sumPriceTimesQuantity += other.sumPriceTimesQuantity;
        sumQuantity += other.sumQuantity;
    }
}
    
    
