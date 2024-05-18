import java.util.HashMap;

public class Bank {
    private String Name;
    private HashMap<Integer, Account> Accounts;
    private HashMap<Integer, Transaction> Transactions;
    private float totalTransactionFee; // The total of the transactions fees
    private float totalOfTransfers; // The total of Transfers
    private float transactionFlatFee;
    private float transactionPercentFee;

}
