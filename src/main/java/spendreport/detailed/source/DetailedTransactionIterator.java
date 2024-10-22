package spendreport.detailed.source;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import spendreport.detailed.model.DetailedTransaction;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Iterator;
import java.util.List;

/**
 * An iterator for the detailed transaction events.
 */
@Getter
@Setter
public class DetailedTransactionIterator implements Iterator<DetailedTransaction>, Serializable {

    private static final long serialVersionUID = 1L;

    // Use Instant here to get the current time as long epoch milliseconds
    private static final Timestamp INITIAL_TIMESTAMP = new Timestamp(Instant.now().toEpochMilli());
    
    // Define zip code constants for cleaner code
    private static final String ZIP_CODE_1 = "01003";
    private static final String ZIP_CODE_2 = "02115";
    private static final String ZIP_CODE_3 = "78712";

    static DetailedTransactionIterator unbounded() {
        return new DetailedTransactionIterator(false);
    }

    static DetailedTransactionIterator bounded() {
        return new DetailedTransactionIterator(true);
    }

    /**
     * Flag to indicate whether the iterator is bounded.
     * If bounded, the iterator will stop returning elements after the last one
     * If unbounded, the iterator will never stop returning elements. It will continue in a round-robin fashion.
     */
    private final boolean bounded;

    /**
     * The timestamp of the last element returned by the iterator.
     * This is used to specify the timestamp of the next element.
     */
    private long timestamp;

    /**
     * The list of detailed transactions to iterate over.
     * For the purpose of this assignment, we will use a fixed list of transactions.
     */
    private final List<DetailedTransaction> detailedTransactions;

    private int runningIndex = 0;

    private DetailedTransactionIterator(boolean bounded) {
        this.bounded = bounded;
        this.timestamp = INITIAL_TIMESTAMP.getTime();
        this.detailedTransactions = Lists.newArrayList();
        initData();
    }

    private void initData() {
        this.detailedTransactions.addAll(
                Lists.newArrayList(
                        new DetailedTransaction(1, 0L, 45.00, ZIP_CODE_1),   
                        new DetailedTransaction(2, 0L, 600.00, ZIP_CODE_2),   
                        new DetailedTransaction(3, 0L, 25.50, ZIP_CODE_3),   
                        new DetailedTransaction(4, 0L, 30.00, ZIP_CODE_1),   
                        new DetailedTransaction(5, 0L, 800.00, ZIP_CODE_2),   
                        new DetailedTransaction(1, 0L, 15.75, ZIP_CODE_3),   
                        new DetailedTransaction(2, 0L, 900.00, ZIP_CODE_2),   
                        new DetailedTransaction(3, 0L, 20.00, ZIP_CODE_3),   
                        new DetailedTransaction(4, 0L, 50.00, ZIP_CODE_1),   
                        new DetailedTransaction(5, 0L, 750.00, ZIP_CODE_2),   
                        new DetailedTransaction(1, 0L, 45.00, ZIP_CODE_1),   
                        new DetailedTransaction(2, 0L, 500.50, ZIP_CODE_2),   
                        new DetailedTransaction(3, 0L, 40.00, ZIP_CODE_3),   
                        new DetailedTransaction(4, 0L, 25.75, ZIP_CODE_1),   
                        new DetailedTransaction(5, 0L, 850.00, ZIP_CODE_2),   
                        new DetailedTransaction(1, 0L, 35.00, ZIP_CODE_3),   
                        new DetailedTransaction(2, 0L, 920.00, ZIP_CODE_2),   
                        new DetailedTransaction(3, 0L, 15.00, ZIP_CODE_3),   
                        new DetailedTransaction(4, 0L, 700.00, ZIP_CODE_1),   
                        new DetailedTransaction(5, 0L, 950.00, ZIP_CODE_2),   
                        new DetailedTransaction(1, 0L, 49.00, ZIP_CODE_1),   
                        new DetailedTransaction(2, 0L, 675.00, ZIP_CODE_2),   
                        new DetailedTransaction(3, 0L, 30.50, ZIP_CODE_3),   
                        new DetailedTransaction(4, 0L, 40.00, ZIP_CODE_1),   
                        new DetailedTransaction(5, 0L, 999.00, ZIP_CODE_2),   
                        new DetailedTransaction(1, 0L, 10.00, ZIP_CODE_1),   
                        new DetailedTransaction(2, 0L, 870.00, ZIP_CODE_2),   
                        new DetailedTransaction(3, 0L, 35.00, ZIP_CODE_3),   
                        new DetailedTransaction(4, 0L, 48.00, ZIP_CODE_1),   
                        new DetailedTransaction(5, 0L, 510.00, ZIP_CODE_2),   
                        new DetailedTransaction(1, 0L, 20.00, ZIP_CODE_1),   
                        new DetailedTransaction(2, 0L, 520.00, ZIP_CODE_2),   
                        new DetailedTransaction(3, 0L, 29.00, ZIP_CODE_3),   
                        new DetailedTransaction(4, 0L, 60.00, ZIP_CODE_1),   // Faulty pattern starts (low)
                        new DetailedTransaction(4, 0L, 1000.00, ZIP_CODE_1),  // Faulty pattern continues (high)
                        new DetailedTransaction(5, 0L, 49.50, ZIP_CODE_2),   
                        new DetailedTransaction(1, 0L, 515.00, ZIP_CODE_1),   
                        new DetailedTransaction(2, 0L, 48.50, ZIP_CODE_2),   // Faulty pattern starts (low)
                        new DetailedTransaction(2, 0L, 910.00, ZIP_CODE_2),   // Faulty pattern continues (high)
                        new DetailedTransaction(3, 0L, 45.00, ZIP_CODE_3),   
                        new DetailedTransaction(4, 0L, 900.00, ZIP_CODE_1),   
                        new DetailedTransaction(5, 0L, 25.00, ZIP_CODE_2),   // Faulty pattern starts (low)
                        new DetailedTransaction(5, 0L, 970.00, ZIP_CODE_2),   // Faulty pattern continues (high)
                        new DetailedTransaction(1, 0L, 47.00, ZIP_CODE_1),   
                        new DetailedTransaction(2, 0L, 800.00, ZIP_CODE_2),   
                        new DetailedTransaction(3, 0L, 42.50, ZIP_CODE_3),   
                        new DetailedTransaction(4, 0L, 985.00, ZIP_CODE_1),   
                        new DetailedTransaction(5, 0L, 38.00, ZIP_CODE_2)   

                )
        );
    }

    @Override
    public boolean hasNext() {
        if (this.getRunningIndex() > this.getDetailedTransactions().size() - 1) {
            if (this.isBounded()) {
                return false;
            } else {
                this.setRunningIndex(0);
            }
        }
        return true;
    }

    @Override
    public DetailedTransaction next() {
        DetailedTransaction detailedTransaction = this.detailedTransactions.get(this.runningIndex++);
        detailedTransaction.setTimestamp(this.timestamp);

        // Keep a time of 5 minutes between transactions
        // Note that this is just a mock timestamp and not be confused with the logic
        timestamp += 5*60*1000; // 5 minutes
        return detailedTransaction;
    }
}
