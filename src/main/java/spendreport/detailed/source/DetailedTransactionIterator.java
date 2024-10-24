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
import java.util.Random;

/**
 * An iterator for the detailed transaction events.
 */
@Getter
@Setter
public final class DetailedTransactionIterator implements Iterator<DetailedTransaction>, Serializable {

    private static final long serialVersionUID = 1L;

    // Use Instant here to get the current time as long epoch milliseconds
    private static final Timestamp INITIAL_TIMESTAMP = new Timestamp(Instant.now().toEpochMilli());

    // Define zip code constants for cleaner code
    private static final String ZIP_CODE_1 = "01003";
    private static final String ZIP_CODE_2 = "02115";
    private static final String ZIP_CODE_3 = "78712";

    static DetailedTransactionIterator randomized() {
        return new DetailedTransactionIterator(true);
    }

    static DetailedTransactionIterator fixed() {
        return new DetailedTransactionIterator(false);
    }

    /**
     * The list of detailed transactions to iterate over.
     * For the purpose of this assignment, we will use a fixed list of transactions.
     */
    private static List<DetailedTransaction> detailedTransactions = Lists.newArrayList();

    /**
     * Flag to indicate whether the iterator is bounded.
     * If bounded, the iterator will stop returning elements after the last one
     * If unbounded, the iterator will never stop returning elements. It will continue in a round-robin fashion.
     */
    private final static boolean BOUNDED = false;

    /**
     * The timestamp of the last element returned by the iterator.
     * This is used to specify the timestamp of the next element.
     */
    private long timestamp;

    /**
     * Flag to indicate whether a random set should be chosen or the hardcoded one.
     */
    private final boolean isRandomized;

    /**
     * The running index of the iterator.
     */
    private int runningIndex = 0;

    private static final List<Integer> ACCOUNT_ID = Lists.newArrayList(1, 2, 3, 4, 5);
    private static final List<String> ZIP_CODES = Lists.newArrayList(ZIP_CODE_1, ZIP_CODE_2, ZIP_CODE_3);

    /**
     * A randomizer to generate random data.
     */
    private static final Random RANDOMIZER = new Random();


    public DetailedTransactionIterator() {
        this(true);
    }

    private DetailedTransactionIterator(boolean isRandomized) {
        this.isRandomized = isRandomized;
        this.timestamp = INITIAL_TIMESTAMP.getTime();

        // Initialize the data if not randomized
        if (!isRandomized) {
            initData();
        }
    }

    private void initData() {
        detailedTransactions.addAll(
                Lists.newArrayList(
                        new DetailedTransaction(1, 0L, 7.25, ZIP_CODE_1),
                        new DetailedTransaction(2, 0L, 850.75, ZIP_CODE_2),
                        new DetailedTransaction(3, 0L, 9.50, ZIP_CODE_3),
                        new DetailedTransaction(4, 0L, 8.30, ZIP_CODE_1),   // Faulty pattern starts
                        new DetailedTransaction(4, 0L, 1000.00, ZIP_CODE_1), // Faulty pattern continues
                        new DetailedTransaction(5, 0L, 600.00, ZIP_CODE_2),
                        new DetailedTransaction(1, 0L, 6.00, ZIP_CODE_3),
                        new DetailedTransaction(3, 0L, 900.00, ZIP_CODE_3),
                        new DetailedTransaction(2, 0L, 5.50, ZIP_CODE_2),   // Faulty pattern starts
                        new DetailedTransaction(2, 0L, 950.00, ZIP_CODE_2), // Faulty pattern continues
                        new DetailedTransaction(1, 0L, 7.75, ZIP_CODE_1),
                        new DetailedTransaction(4, 0L, 650.00, ZIP_CODE_1),
                        new DetailedTransaction(5, 0L, 5.25, ZIP_CODE_2),   // Faulty pattern starts
                        new DetailedTransaction(5, 0L, 700.00, ZIP_CODE_2), // Faulty pattern continues
                        new DetailedTransaction(3, 0L, 5.00, ZIP_CODE_3),
                        new DetailedTransaction(1, 0L, 750.50, ZIP_CODE_1),
                        new DetailedTransaction(2, 0L, 850.25, ZIP_CODE_2),
                        new DetailedTransaction(4, 0L, 8.10, ZIP_CODE_1),   // Faulty pattern starts
                        new DetailedTransaction(4, 0L, 950.00, ZIP_CODE_1), // Faulty pattern continues
                        new DetailedTransaction(5, 0L, 900.50, ZIP_CODE_3),
                        new DetailedTransaction(3, 0L, 9.75, ZIP_CODE_3),
                        new DetailedTransaction(1, 0L, 6.75, ZIP_CODE_1),
                        new DetailedTransaction(2, 0L, 850.50, ZIP_CODE_2),
                        new DetailedTransaction(3, 0L, 8.00, ZIP_CODE_3),   // Faulty pattern starts
                        new DetailedTransaction(3, 0L, 900.00, ZIP_CODE_3), // Faulty pattern continues
                        new DetailedTransaction(4, 0L, 850.00, ZIP_CODE_1),
                        new DetailedTransaction(5, 0L, 600.00, ZIP_CODE_2),
                        new DetailedTransaction(1, 0L, 5.90, ZIP_CODE_1),   // Faulty pattern starts
                        new DetailedTransaction(1, 0L, 950.00, ZIP_CODE_1), // Faulty pattern continues
                        new DetailedTransaction(2, 0L, 650.00, ZIP_CODE_2),
                        new DetailedTransaction(3, 0L, 6.30, ZIP_CODE_3),
                        new DetailedTransaction(4, 0L, 5.50, ZIP_CODE_1),   // Faulty pattern starts
                        new DetailedTransaction(4, 0L, 1000.00, ZIP_CODE_1), // Faulty pattern continues
                        new DetailedTransaction(5, 0L, 900.00, ZIP_CODE_2),
                        new DetailedTransaction(1, 0L, 9.00, ZIP_CODE_1),
                        new DetailedTransaction(2, 0L, 850.00, ZIP_CODE_2),
                        new DetailedTransaction(3, 0L, 7.25, ZIP_CODE_3),
                        new DetailedTransaction(4, 0L, 700.00, ZIP_CODE_1),
                        new DetailedTransaction(5, 0L, 9.80, ZIP_CODE_2),   // Faulty pattern starts
                        new DetailedTransaction(5, 0L, 950.00, ZIP_CODE_2), // Faulty pattern continues
                        new DetailedTransaction(1, 0L, 6.50, ZIP_CODE_1),
                        new DetailedTransaction(2, 0L, 5.75, ZIP_CODE_2),
                        new DetailedTransaction(3, 0L, 950.00, ZIP_CODE_3),
                        new DetailedTransaction(4, 0L, 8.90, ZIP_CODE_1),   // Faulty pattern starts
                        new DetailedTransaction(4, 0L, 900.00, ZIP_CODE_1), // Faulty pattern continues
                        new DetailedTransaction(5, 0L, 850.50, ZIP_CODE_2)
                )
        );
    }

    @Override
    public boolean hasNext() {
        if (runningIndex < detailedTransactions.size()) {
            return true;
        } else if (!BOUNDED) {
            runningIndex = 0;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public DetailedTransaction next() { // NOSONAR

        DetailedTransaction detailedTransaction;

        if (this.isRandomized) {
            // Generate a random accountId
            int accountIdIndex = RANDOMIZER.nextInt(0, ACCOUNT_ID.size());
            long accountId = ACCOUNT_ID.get(accountIdIndex);

            // Generate amount
            double amount = RANDOMIZER.nextDouble(0, 1000);

            // Generate zipCode
            int zipCodeIndex = RANDOMIZER.nextInt(0, ZIP_CODES.size());
            String zipCode = ZIP_CODES.get(zipCodeIndex);

            detailedTransaction = new DetailedTransaction(accountId, timestamp, amount, zipCode);
        } else {
            detailedTransaction = detailedTransactions.get(runningIndex++);
            detailedTransaction.setTimestamp(timestamp);

        }
        // Increment the transaction by one minute
        timestamp += 60 * 1000L;

        return detailedTransaction;
    }
}
