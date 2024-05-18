import java.util.HashMap;

public class Bank {
    private String Name;
    private HashMap<Integer, Account> Accounts;
    private HashMap<Integer, Transaction> Transactions;
    private float totalTransactionFee; // The total of the transactions fees
    private float totalOfTransfers; // The total of Transfers
    private float transactionFlatFee;
    private float transactionPercentFee;
    private int aLastKey = 1;
    private int tLastKey = 1;

    public void addAccount(String Name)throws BankException{
        try{
            Account A = new Account(aLastKey++, Name);
            Accounts.put(A.getAccountID(), A);
        }catch(BankException e){
            System.out.println(e);
        }
    }

    public void listAccounts(){
        System.out.println(Accounts);
    }

}
