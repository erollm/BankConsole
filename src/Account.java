public class Account{
    private int AccountID;
    private String Name;
    private float Balance;

    public Account(int AccountID, String Name){
        this.AccountID = AccountID;
        this.Name = Name;
        this.Balance = 0f;
    }

    public int getAccountID(){
        return AccountID;
    }

    public String getName(){
        return Name;
    }

    public float getBalance(){
        return Balance;
    }

    public void setBalance(float Balance) throws BankException{
        if(Balance < 0){
            throw new BankException("Balance cannot be negative.");
        }
        else{
            this.Balance = Balance;
        }
    }

    public String toString(){
        return "ID:"+AccountID+", Name:"+Name+", Balance:"+Balance;
    }

}