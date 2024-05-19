import java.util.HashMap;
import java.util.Map;

public class Bank {
    private String Name;
    private HashMap<Integer, Account> Accounts;
    private HashMap<Integer, Transaction> Transactions;
    private float totalTransactionFee; // The total of the transactions fees
    private float totalOfTransfers; // The total of Transfers
    private float transactionFlatFee = 0f;
    private float transactionPercentFee = 0f;
    private int aLastKey = 1;
    private int tLastKey = 1;

    public Bank(String Name, float transactionFlatFee, float transactionPercentFee){
            this.Name = Name;
            this.transactionFlatFee = transactionFlatFee;
            this.transactionPercentFee = transactionPercentFee;
            Accounts = new HashMap<Integer, Account>();
            Transactions = new HashMap<Integer, Transaction>();
    }


    public String getName(){
        return Name;
    }

    public float getTotalTransactionFee() {
        return totalTransactionFee;
    }

    public float getTotalOfTransfers() {
        return totalOfTransfers;
    }

    public void setTotalTransactionFee(float totalTransactionFee){
        this.totalTransactionFee = totalTransactionFee;
    }

    public void setTotalOfTransfers(float totalOfTransfers){
        this.totalOfTransfers = totalOfTransfers;
    }

    public float getTransactionFlatFee(){
        return transactionFlatFee;
    }

    public float getTransactionPercentFee() {
        return transactionPercentFee;
    }

    public boolean isEmpty(){
        return Accounts.isEmpty();
    }

    public boolean hasTransactions(){
        return !Transactions.isEmpty();
    }

    public void addAccount(String Name){
            Account A = new Account(aLastKey++, Name);
            Accounts.put(A.getAccountID(), A);
    }

    public void listAccounts(){
        if(Accounts.isEmpty()){
                System.out.println("The bank does not have any accounts yet.");
        }
        else {
            System.out.println("---Account List---");
            for (Map.Entry<Integer, Account> entry : Accounts.entrySet()) {
                System.out.println(entry.getValue());
            }
        }
    }

    public void listTransactions(){
        System.out.println(Transactions);
    }

    public void newTransaction(float Amount, int originatingID, int recipientID, String Description, boolean tType) throws BankException{
        float Fee;
        float AmountWFee = Amount;
        if(tType){      // If true Transaction using flat Fee
            Fee = transactionFlatFee;
            AmountWFee += transactionFlatFee;
        }
        else{
            Fee = transactionPercentFee/100*Amount;
            AmountWFee += transactionPercentFee/100*Amount;
        }
        if(!Accounts.containsKey(recipientID)){
            throw new BankException("The Recipient Not Found!");
        }
        else if(Accounts.get(originatingID).getBalance() < AmountWFee){   // The User needs enough money for the fee aswell
            throw new BankException("Insufficient funds!");
        }
        else{
            Transaction T = new Transaction(tLastKey++, AmountWFee, originatingID, recipientID, Description);
            Transactions.put(T.getTransactionID(), T);
            sendMoney(originatingID, recipientID, Amount, Fee);
            setTotalOfTransfers(getTotalOfTransfers()+AmountWFee);
            setTotalTransactionFee(getTotalTransactionFee()+Fee);
        }
    }

    public void sendMoney(int originatingID, int recipientID, float Amount, float Fee){
        try {
            Accounts.get(originatingID).setBalance(Accounts.get(originatingID).getBalance() - (Amount+Fee));
            Accounts.get(recipientID).setBalance(Accounts.get(recipientID).getBalance() + Amount);
        }catch(BankException b){
            System.out.println(b);
        }
    }

    public void deposit(int AccountID,float Amount) throws BankException{
        if(!Accounts.containsKey(AccountID)){
            throw new BankException("Account does not exist!");
        }
        else if(Amount < 0){
            throw new BankException("Deposit amount cannot be negative!");
        }
        else{
            Accounts.get(AccountID).setBalance(Accounts.get(AccountID).getBalance() + Amount);
        }
    }

    public void withdraw(int AccountID,float Amount) throws BankException{
        if(!Accounts.containsKey(AccountID)){
            throw new BankException("Account does not exist!");
        }
        else if(Amount < 0){
            throw new BankException("Withdraw amount cannot be negative!");
        }
        else{
            Accounts.get(AccountID).setBalance(Accounts.get(AccountID).getBalance() - Amount);
        }
    }

    public void getAccountTransactions(int AccountID) throws BankException{
        if(!Accounts.containsKey(AccountID)){
            throw new BankException("Account not found!");
        }
        else{
            int count = 0;
            System.out.println("---Account"+AccountID+" Transactions List---");
            for (Map.Entry<Integer, Transaction> Entry : Transactions.entrySet()) {
                Transaction T = Entry.getValue();
                if(T.getOriginatingID() == AccountID || T.getRecipientID() == AccountID){
                    System.out.println(T);
                    count++;
                }
            }
            if(count == 0){
                throw new BankException("Account does not have any transactions yet!");
            }
        }
    }

    public void checkBalance(int AccountID) throws BankException{
        if(!Accounts.containsKey(AccountID)){
            throw new BankException("Account not found!");
        }
        else{
            System.out.println(Accounts.get(AccountID).getName()+" Balance is: $"+String.format("%.2f", Accounts.get(AccountID).getBalance()));
        }
    }

}
