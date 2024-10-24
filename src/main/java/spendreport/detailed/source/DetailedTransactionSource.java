package spendreport.detailed.source;

import org.apache.flink.annotation.Public;
import org.apache.flink.streaming.api.functions.source.FromIteratorFunction;
import spendreport.detailed.model.DetailedTransaction;

import java.io.Serializable;
import java.util.Iterator;

@Public
public class DetailedTransactionSource extends FromIteratorFunction<DetailedTransaction> {
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new DetailedTransactionSource that produces the given transactions.
     * If true is passed, the transactions are produced in a randomized properties.
     * If false is passed, the transactions are produced from a fixed set of transactions.
     *
     * @param randomized Should the properties of the transactions be randomized.
     */
    public DetailedTransactionSource(boolean randomized) {
        super(new RateLimitedIterator<>(
                randomized ? DetailedTransactionIterator.randomized() : DetailedTransactionIterator.fixed()
        ));
    }

    private static class RateLimitedIterator<T> implements Iterator<T>, Serializable {
        private static final long serialVersionUID = 1L;
        private final Iterator<T> inner; // NOSONAR

        private RateLimitedIterator(Iterator<T> inner) {
            this.inner = inner;
        }

        public boolean hasNext() {
            return this.inner.hasNext();
        }

        public T next() {
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) { // NOSONAR
                throw new RuntimeException(e); // NOSONAR
            }

            return this.inner.next();
        }
    }
}
