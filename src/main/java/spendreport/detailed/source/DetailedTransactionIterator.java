package spendreport.detailed.source;

import java.sql.Timestamp;
import java.time.Instant;

/**
 * An iterator for the detailed transaction events.
 */
public class DetailedTransactionIterator {

    private static final long serialVersionUID = 1L;

    // Use Instant here to get the current time as long epoch milliseconds
    private static final Timestamp INITIAL_TIMESTAMP = new Timestamp(Instant.now().toEpochMilli());

    // Using the same
    private static final long SIX_MINUTES = 6 * 60 * 1000L;
}
