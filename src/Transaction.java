public class Transaction {
    private int TransactionID;
    private float Amount;
    private int originatingID; // Orginating Account ID
    private int recipientID; // Recipient Account ID
    private String Description;

    public Transaction(int TransactionID, float Amount, int originatingID, int recipientID, String Description) throws BankException{
        if(Amount < 0){
            throw new BankException("Amount cannot be a negative number.");
        }
        else{
            this.Amount = Amount;
        }
        this.TransactionID = TransactionID;
        this.originatingID = originatingID;
        this.recipientID = recipientID;
        if(Description == null || Description.isEmpty()){
            throw new BankException("Description field cannot be empty. Please provide a valid Description.");
        }
        else{
            this.Description = Description;
        }
    }

    public int getTransactionID() {
        return TransactionID;
    }

    public float getAmount() {
        return Amount;
    }

    public int getOriginatingID() {
        return originatingID;
    }

    public int getRecipientID() {
        return recipientID;
    }

    public String getDescription() {
        return Description;
    }

    public String toString(){
        return "ID:"+TransactionID+" Total Amount:"+Amount+" originatingID:"+originatingID+" recipientID:"+recipientID+" Description:"+Description;
    }
}
