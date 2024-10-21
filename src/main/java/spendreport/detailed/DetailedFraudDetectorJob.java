package spendreport.detailed;

import lombok.SneakyThrows;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import spendreport.detailed.model.DetailedTransaction;

public class DetailedFraudDetectorJob {

    @SneakyThrows
    public static void main(String[] args) {

        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<DetailedTransaction> detailedTransactionDataStream = environment
                .addSource(new DetailedTransaction())
                .name("detailed-transactions");

        environment.execute("Detailed Fraud Detector");
    }
}
