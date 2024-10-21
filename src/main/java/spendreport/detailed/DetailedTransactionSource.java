package spendreport.detailed;

import org.apache.flink.walkthrough.common.source.TransactionIterator;
import org.apache.flink.walkthrough.common.source.TransactionSource;

import java.io.Serializable;
import java.util.Iterator;

public class DetailedTransactionSource {
    private static final long serialVersionUID = 1L;

    public DetailedTransactionSource() {
        super(new RateLimitedIterator(TransactionIterator.unbounded()));
    }

    private static class RateLimitedIterator<T> implements Iterator<T>, Serializable {
        private static final long serialVersionUID = 1L;
        private final Iterator<T> inner;

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
