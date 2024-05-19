import java.util.Scanner;

public class Main {
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to BankConsole");

                // Loop until a valid Bank Name is provided
                String BankName;
                do {
                    System.out.println("Enter Bank Name:");
                    BankName = sc.nextLine();
                    if (BankName == null || BankName.isEmpty()) {
                        sc.nextLine();
                        System.out.println("Bank Name cannot be empty.");
                    }
                }while(BankName == null || BankName.isEmpty());

                // Loop until a valid flat fee is provided
                float FlatFee;
                do {
                    System.out.println("Enter Transaction Flat Fee Amount:");
                    FlatFee = sc.nextFloat();
                    if (FlatFee < 0) {
                        System.out.println("Transaction flat fee cannot be negative");
                    }
                } while (FlatFee < 0);

                // Loop until a valid percentage fee is provided
                float PercentFee;
                do {
                    System.out.println("Enter Transaction Percent Fee Amount:");
                    PercentFee = sc.nextFloat();
                    if (PercentFee < 0) {
                        System.out.println("Transaction percentage fee cannot be negative");
                    }
                } while (PercentFee < 0);

                sc.nextLine();
                Bank B = new Bank(BankName, FlatFee, PercentFee);
                while(true){
                    printMenu(B.getName());
                    int selected = sc.nextInt();
                    switch (selected){
                        case 1:
                            createAccount(B, sc);
                            break;
                        case 2:
                            return;
                    }
                }


    }

    public static void printMenu(String Name){
            System.out.println("----"+Name+" System Menu----");
            System.out.println("Please select an option");
            System.out.println("1) Create New Account");
            System.out.println("2) Transfer");
            System.out.println("3) Deposit");
            System.out.println("4) Withdraw");
            System.out.println("5) View Account Transactions");
            System.out.println("6) Check Account Balance");
            System.out.println("7) Check Total Transaction Fee Amount");
            System.out.println("8) Check Total Transfer Amount");
            System.out.println("9) Exit");
    }

    public static void createAccount(Bank b, Scanner sc){
            String name;
            do{
                System.out.println("Enter Account Name:");
                name = sc.nextLine();
                if(name == null || name.isEmpty()){
                    sc.nextLine();
                    System.out.println("Name cannot be empty!");
                }
            }while(name == null || name.isEmpty());
            b.addAccount(name);
            System.out.println("Account successfully created!");
    }


}
