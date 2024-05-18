public class Account{
    private int AccountID;
    private String Name;
    private float Balance;

    public Account(int AccountID, String Name, float Balance) throws BankException{
        this.AccountID = AccountID;
        if(Name == null || Name.isEmpty()){
            throw new BankException("Name field cannot be empty. Please provide a valid name.");
        }
        else{
            this.Name = Name;
        }
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

}