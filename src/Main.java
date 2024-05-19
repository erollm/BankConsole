import java.util.Scanner;

public class Main {
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to BankConsole");

        boolean isException = true;
        while(isException){
            System.out.println("Enter Bank Name:");
            String BankName = sc.nextLine();

            System.out.println("Enter Transaction Flat Fee Amount:");
            float FlatFee = sc.nextFloat();

            System.out.println("Enter Transaction Percent Fee Amount:");
            float PercentFee = sc.nextFloat();

            try {
                Bank B = new Bank(BankName, FlatFee, PercentFee);
                printMenu(B.getName());
                isException = false;
            }catch(BankException b){
                System.out.println(b);
                sc.nextLine();
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


}
