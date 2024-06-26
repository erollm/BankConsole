import java.util.Scanner;

public class BankConsole {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to BankConsole");

        // Loop until a valid Bank Name is provided
        String BankName;
        do {
            System.out.println("Plase provide the name of the Bank you would like to create:");
            BankName = sc.nextLine();
            if (BankName == null || BankName.isEmpty()) {
                System.out.println("Bank Name cannot be empty.");
            }
            System.out.println(BankName+" Bank Successfully Created.");
            System.out.println("To initiate the "+BankName+" Bank system please provide values for the following parameters:");
        } while (BankName == null || BankName.isEmpty());

        // Loop until a valid flat fee is provided
        float FlatFee;
        do {
            System.out.println("Enter Transaction Flat Fee Amount:");
            FlatFee = Float.parseFloat(sc.nextLine());
            if (FlatFee < 0) {
                System.out.println("Transaction flat fee cannot be negative");
            }
        } while (FlatFee < 0);

        // Loop until a valid percentage fee is provided
        float PercentFee;
        do {
            System.out.println("Enter Transaction Percent Fee Amount:");
            PercentFee = Float.parseFloat(sc.nextLine());
            if (PercentFee < 0) {
                System.out.println("Transaction percentage fee cannot be negative");
            }
        } while (PercentFee < 0);
        System.out.println("Bank Successfully Configured with Transaction Flat Fee of $"+FlatFee+" and Transaction Percent Fee of "+PercentFee+"%\n");
        Bank B = new Bank(BankName, FlatFee, PercentFee);
        while (true) {
            printMenu(B.getName());
            int selected = Integer.parseInt(sc.nextLine());
            if(selected > 10 || selected < 1){
                System.out.println("Invalid Option Selected"); // The user can choose only one of the 10 options.
                returnMenu(sc);
                continue;
            }
            else if (selected != 1 && B.isEmpty()) { // When there are no accounts, the user can only access the Create Account option.
                System.out.println("The bank does not have any accounts yet.");
                returnMenu(sc);
                continue;
            }
            else if(selected == 5 && !B.hasTransactions()){
                System.out.println("The bank does not have any transactions yet."); // When there are no transactions, the user cannot access the View Account Transacitons.
                returnMenu(sc);
                continue;
            }

            switch (selected) {
                case 1:
                    createAccount(B, sc);
                    break;
                case 2:
                    transferMoney(B, sc);
                    returnMenu(sc);
                    break;
                case 3:
                    depositToAccount(B, sc);
                    returnMenu(sc);
                    break;
                case 4:
                    withdrawFromAccount(B, sc);
                    returnMenu(sc);
                    break;
                case 5:
                    viewAccountTransactions(B, sc);
                    returnMenu(sc);
                    break;
                case 6:
                    checkAccountBalance(B, sc);
                    returnMenu(sc);
                    break;
                case 7:
                    B.listAccounts();
                    returnMenu(sc);
                    break;
                case 8:
                    checkTotalTransactionFeeAmount(B);
                    returnMenu(sc);
                    break;
                case 9:
                    checkTotalTransferAmount(B);
                    returnMenu(sc);
                    break;
                case 10:
                    return;
            }
        }
    }

    public static void printMenu(String Name) {
        System.out.println("----" + Name + " Bank System Menu----");
        System.out.println("Please select an option");
        System.out.println("1) Create New Account");
        System.out.println("2) Transfer");
        System.out.println("3) Deposit");
        System.out.println("4) Withdraw");
        System.out.println("5) View Account Transactions");
        System.out.println("6) Check Account Balance");
        System.out.println("7) View All Accounts");
        System.out.println("8) Check Total Transaction Fee Amount");
        System.out.println("9) Check Total Transfer Amount");
        System.out.println("10) Exit");
    }

    public static void createAccount(Bank b, Scanner sc) {
        String name;
        do {
            System.out.println("Enter Account Name:");
            name = sc.nextLine();
            if (name == null || name.isEmpty()) {
                System.out.println("Name cannot be empty!");
            }
        } while (name == null || name.isEmpty());
        b.addAccount(name);
        System.out.println("Account successfully created!\n");
    }

    public static void transferMoney(Bank b, Scanner sc){
        boolean stayOnTransfer = true;
        while(stayOnTransfer){
            try {
                System.out.println("Select transaction fee type:");
                System.out.println("1) Flat Fee ($" + b.getTransactionFlatFee() + ")");
                System.out.println("2) Percent Fee (" + b.getTransactionPercentFee() + "%)");
                byte Fee = Byte.parseByte(sc.nextLine());
                boolean feeType;
                if (Fee == 1) {
                    feeType = true;
                } else if (Fee == 2) {
                    feeType = false;
                } else {
                    throw new BankException("Inputted fee type not valid!");
                }
                System.out.println("Enter the Amount of transfer:");
                float amount = Float.parseFloat(sc.nextLine());
                System.out.println("Enter the Originating Account ID:");
                int orgID = Integer.parseInt(sc.nextLine());
                System.out.println("Enter the Recieving Account ID:");
                int recipientID = Integer.parseInt(sc.nextLine());
                System.out.println("Enter the Transfer Description:");
                String description = sc.nextLine();
                b.newTransaction(amount, orgID, recipientID, description, feeType);
                System.out.println("Successfully transferred $"+String.format("%.2f", amount)+" from Account"+orgID+" to Account"+recipientID+"\n");
                stayOnTransfer = false;
            }catch (BankException e){
                System.out.println(e);
            }
        }
    }

    public static void depositToAccount(Bank b, Scanner sc){
        boolean stayOnDeposit = true;
        while(stayOnDeposit){
            System.out.println("Enter the Account ID you wish to deposit to:");
            int ID = Integer.parseInt(sc.nextLine());;
            System.out.println("Enter the Amount:");
            float amount = Float.parseFloat(sc.nextLine());
            try{
                b.deposit(ID, amount);
                System.out.println("Successfully deposited $"+String.format("%.2f", amount)+" to Account "+ID+"\n");
                stayOnDeposit = false;
            }catch (BankException e){
                System.out.println(e);
            }
        }
    }

    public static void withdrawFromAccount(Bank b, Scanner sc){
        boolean stayOnWithdraw = true;
        while(stayOnWithdraw){
            System.out.println("Enter the Account ID you wish to withdraw from:");
            int ID = Integer.parseInt(sc.nextLine());
            System.out.println("Enter the Amount:");
            float amount = Float.parseFloat(sc.nextLine());
            try{
                b.withdraw(ID, amount);
                System.out.println("Successfully withdrawn  $"+String.format("%.2f", amount)+" from Account "+ID+"\n");
                stayOnWithdraw = false;
            }catch (BankException e){
                System.out.println(e);
            }
        }
    }

    public static void viewAccountTransactions(Bank b, Scanner sc){
        boolean stayOnAccountTransacitons = true;
        while(stayOnAccountTransacitons){
            System.out.println("Enter the Account ID you wish to see the transactions of:");
            int id = Integer.parseInt(sc.nextLine());
            try{
                b.getAccountTransactions(id);
                stayOnAccountTransacitons = false;
            }catch (BankException e){
                System.out.println(e);
            }
        }
    }

    public static void checkAccountBalance(Bank b, Scanner sc){
        boolean stayOnCheckAccountBalance = true;
        while(stayOnCheckAccountBalance){
            System.out.println("Enter the Account ID you wish to check the balance of:");
            int id = Integer.parseInt(sc.nextLine());
            try{
                b.checkBalance(id);
                stayOnCheckAccountBalance = false;
            }
            catch (BankException e){
                System.out.println(e);
            }
        }
    }

    public static void checkTotalTransactionFeeAmount(Bank b){
        System.out.println("The total of Transaction Fee Amount is: $"+String.format("%.2f", b.getTotalTransactionFee())+"\n");
    }

    public static void checkTotalTransferAmount(Bank b){
        System.out.println("The total of Transfer Amount is: $"+String.format("%.2f", b.getTotalOfTransfers())+"\n");
    }

    public static void returnMenu(Scanner sc){
        boolean stayOnMenu = true;
        while (stayOnMenu){
            System.out.println("Press enter to return to the menu:");
            sc.nextLine();
            stayOnMenu = false;
        }
    }


}
