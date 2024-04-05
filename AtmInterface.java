import java.util.Scanner;

class BankAccount {

    String name;
    String userName;
    String pin;
    String accountNo;
    float balance = 100000f;
    int transactions = 0;
    String transactionHistory = "";

    public void register() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter Your Name: ");
        this.name = sc.nextLine();
        System.out.print("\nEnter Your Username: ");
        this.userName = sc.nextLine();
        System.out.print("\nEnter Your Pin: ");
        this.pin = sc.nextLine();
        System.out.print("\nEnter Your Account Number: ");
        this.accountNo = sc.nextLine();
        System.out.println("\nRegistration completed...\nWould you like kindly login?");
    }

    public boolean login() {
        boolean isLogin = false;
        Scanner sc = new Scanner(System.in);
        while (!isLogin) {
            System.out.print("\nEnter Your Username: ");
            String Username = sc.nextLine();
            if (Username.equals(userName)) {
                while (!isLogin) {
                    System.out.print("\nEnter Your Pin: ");
                    String Pin = sc.nextLine();
                    if (Pin.equals(pin)) {
                        System.out.print("\nLogin successful!!");
                        isLogin = true;
                    } else {
                        System.out.println("\nIncorrect Pin, Try again!\n You have 2 attempts left");
                    }
                }
            } else {
                System.out.println("\nUsername not found, Try again!");
            }
        }
        return isLogin;
    }

    public void withdraw() {

        System.out.print("\nEnter Withdrawal Amount: ");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();
        try {

            if (balance >= amount) {
                transactions++;
                balance -= amount;
                System.out.println("\nWithdraw Successfully!\n Would you like to continue?");
                String str = amount + " ZAR Withdrew\n";
                transactionHistory = transactionHistory.concat(str);

            } else {
                System.out.println("\nInsufficient Balance!");
            }

        } catch (Exception e) {
        }
    }

    public void deposit() {

        System.out.print("\nEnter Deposit Amount: ");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();

        try {
            if (amount <= 100000f) {
                transactions++;
                balance += amount;
                System.out.println("\nSuccessfully Deposited");
                String str = amount + " ZAR deposited\n";
                transactionHistory = transactionHistory.concat(str);
            } else {
                System.out.println("\nYour Daily Limit is 10 000\n Try again!");
            }

        } catch (Exception e) {
        }
    }

    public void transfer() {

        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter Receipent's Name: ");
        String receipent = sc.nextLine();
        System.out.print("\nEnter Transfer Amount: ");
        float amount = sc.nextFloat();

        try {
            if (balance >= amount) {
                if (amount <= 50000f) {
                    transactions++;
                    balance -= amount;
                    System.out.println("\nSuccessfully Transfered to " + receipent);
                    String str = amount + " ZAR transfered to " + receipent + "\n";
                    transactionHistory = transactionHistory.concat(str);
                } else {
                    System.out.println("\nYour Daily Limit is 10 000");
                }
            } else {
                System.out.println("\nInsufficient Balance");
            }
        } catch (Exception e) {
        }
    }

    public void checkBalance() {
        System.out.println("\n" + balance + " ZAR");
    }

    public void transHistory() {
        if (transactions == 0) {
            System.out.println("\nEmpty");
        } else {
            System.out.println("\n" + transactionHistory);
        }
    }
}

public class AtmInterface {

    public static int takeIntegerInput(int limit) {
        int input = 0;
        boolean flag = false;

        while (!flag) {
            try {
                Scanner sc = new Scanner(System.in);
                input = sc.nextInt();
                flag = true;

                if (flag && input > limit || input < 1) {
                    System.out.println("Choose a number between 1 to " + limit);
                    flag = false;
                }
            } catch (Exception e) {
                System.out.println("Enter only integer value");
                flag = false;
            }
        }
        ;
        return input;
    }

    public static void main(String[] args) {

        System.out.println("Good Day Potential Client!\nWould you like to join our company");
        System.out.println("1.Register \n2.Exit");
        System.out.print("SELECT OPTION: ");
        int choice = takeIntegerInput(2);

        if (choice == 1) {
            BankAccount b = new BankAccount();
            b.register();
            while (true) {
                System.out.println("\n1.Login \n2.Exit");
                System.out.print("SELECT OPTION: ");
                int ch = takeIntegerInput(2);
                if (ch == 1) {
                    if (b.login()) {
                        System.out.println("\n\nWELCOME BACK " + b.name + "\n");
                        boolean isFinished = false;
                        while (!isFinished) {
                            System.out.print("\nHoW CAN WE HELP YOU TODAY?");
                            System.out.println(
                                    "\n1.Withdraw \n2.Deposit \n3.Transfer \n4.Check Balance \n5.Transaction History \n6.Exit");
                            System.out.print("\nSELECT OPTION: ");
                            int c = takeIntegerInput(6);
                            switch (c) {
                                case 1:
                                    b.withdraw();
                                    break;
                                case 2:
                                    b.deposit();
                                    break;
                                case 3:
                                    b.transfer();
                                    break;
                                case 4:
                                    b.checkBalance();
                                    break;
                                case 5:
                                    b.transHistory();
                                    break;
                                case 6:
                                    isFinished = true;
                                    break;
                            }
                        }
                    }
                } else {
                    System.exit(0);
                }
            }
        } else {
            System.exit(0);
        }

    }
}