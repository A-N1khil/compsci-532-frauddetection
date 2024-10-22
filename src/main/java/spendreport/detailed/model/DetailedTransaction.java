package spendreport.detailed.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * A transaction with additional detail of the zip code
 * Zip Code will be uniformly selected between [01003, 02115, 78712]
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DetailedTransaction {

    private static final long serialVersionUID = 1L;

    private long accountId;
    private long timestamp;
    private double amount;
    private String zipCode;
}
