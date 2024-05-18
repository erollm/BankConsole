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

    public Bank(String Name, float transactionFlatFee, float transactionPercentFee) throws BankException{
        if(Name == null || Name.isEmpty()){
            throw new BankException("Bank Name cannot be empty. Please provide a valid name.");
        }
        else if(transactionFlatFee < 0){
            throw new BankException("Transaction flat fee cannot be negative");
        }
        else if(transactionPercentFee < 0){
            throw new BankException("Transaction percentage fee cannot be negative");
        }
        else {
            this.Name = Name;
            this.transactionFlatFee = transactionFlatFee;
            this.transactionPercentFee = transactionPercentFee;
            Accounts = new HashMap<Integer, Account>();
            Transactions = new HashMap<Integer, Transaction>();
        }
    }

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

    public void listTransactions(){
        System.out.println(Transactions);
    }

    public void newTransaction(float Amount, int originatingID, int recipientID, String Description, boolean tType) throws BankException{
        float Fee;
        if(tType){      // If true Transaction using flat Fee
            Fee = transactionFlatFee;
        }
        else{
            Fee = transactionPercentFee/100*Amount;
        }

        if(!Accounts.containsKey(recipientID)){
            throw new BankException("The Recipient Not Found!");
        }
        else if(Accounts.get(originatingID).getBalance() < (Amount+Fee)){   // The User needs enough money for the fee aswell
            throw new BankException("Insufficient funds!");
        }
        else{
            Transaction T = new Transaction(tLastKey++, (Amount+Fee), originatingID, recipientID, Description);
            Transactions.put(T.getTransactionID(), T);
            sendMoney(originatingID, recipientID, Amount, Fee);
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
        if(Amount < 0){
            throw new BankException("Deposit amount cannot be negative!");
        }
        else{
            Accounts.get(AccountID).setBalance(Accounts.get(AccountID).getBalance() + Amount);
        }
    }

    public void withdraw(int AccountID,float Amount) throws BankException{
        if(Amount < 0){
            throw new BankException("Withdraw amount cannot be negative!");
        }
        else{
            Accounts.get(AccountID).setBalance(Accounts.get(AccountID).getBalance() - Amount);
        }
    }

    public static void main(String [] args){
        /*try {
            Bank BKT = new Bank("BKT", 5, 1);
            BKT.addAccount("Eroll");
            BKT.addAccount("Filani");
            BKT.deposit(1, 450);
            BKT.listAccounts();
            BKT.newTransaction(100, 1, 2, "Hello", false);
            BKT.listTransactions();
            BKT.listAccounts();
        }catch(BankException b){
            System.out.println(b);
        }*/
    }
}
