package spendreport.detailed.source;

import org.apache.flink.streaming.api.functions.source.FromIteratorFunction;
import spendreport.detailed.model.DetailedTransaction;

import java.io.Serializable;
import java.util.Iterator;

public class DetailedTransactionSource extends FromIteratorFunction<DetailedTransaction> {
    private static final long serialVersionUID = 1L;

    public DetailedTransactionSource() {
        super(new RateLimitedIterator<>(DetailedTransactionIterator.unbounded()));
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
