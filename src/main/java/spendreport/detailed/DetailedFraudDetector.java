package spendreport.detailed;

import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;
import spendreport.detailed.model.DetailedAlert;
import spendreport.detailed.model.DetailedTransaction;

public class DetailedFraudDetector extends KeyedProcessFunction<Long, DetailedTransaction, DetailedAlert> {

    private static final long serialVersionUID = 1L;

    private static final double SMALL_AMOUNT = 1.00;
    private static final double LARGE_AMOUNT = 500.00;
    private static final long ONE_MINUTE = 60 * 1000L;

    @Override
    public void processElement(DetailedTransaction detailedTransaction, Context context, Collector<DetailedAlert> collector) throws Exception {

        DetailedAlert detailedAlert = new DetailedAlert();
        detailedAlert.setId(detailedTransaction.getAccountId());

        collector.collect(detailedAlert);
    }
}
