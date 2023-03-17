import java.util.Scanner;

public class UserInteraction {
    public void getUserInput() {
        short chosenOption = 0;   //can be the following: 1-create an account, 2 - login into an account, 3 - terminate the program
        Scanner sc = new Scanner(System.in);
        Database db = new Database();

        System.out.println("Welcome user! Please type in one of the following options:");
        showOptions();

        while(chosenOption != 3) {
            chosenOption = sc.nextShort();

            switch (chosenOption) {
                case 1:
                    createAccount(db, sc);
                    System.out.println("What would you like to do next?");
                    break;
                case 2:
                    loginIntoAccount(db, sc);
                    System.out.println("What would you like to do next?");
                    break;
                default:
                    System.out.println("Invalid option! please choose between:");
            }

            showOptions();
        }

        System.out.println("App closing...");
    }

    private void showOptions() {
        System.out.println("1 - Create a new account");
        System.out.println("2 - Login into an existing account");
        System.out.println("3 - Terminate the program");
    }

    private void loginIntoAccount(Database database, Scanner sc) {
        boolean isLogged = false;
        String username, password;

        if(database.isDatabaseEmpty()) {
            System.out.println("There is no account registered!");
            return;
        }

        while(!isLogged) {
            System.out.print("Username:");
            username = sc.next();

            System.out.print("Password:");
            password = sc.next();

            isLogged = database.verifyData(username, password);

            if(!isLogged) {
                System.out.println("Incorrect username or password!");
            }
        }

        System.out.println("Login successful!");
    }

    private void createAccount(Database database, Scanner sc) {
        String username, password;

        System.out.println("Please type in the username and the password for the account");
        System.out.println("Warning: the username nor the password should contain spaces");

        System.out.print("Username:");
        username = sc.next();

        System.out.print("Password:");
        password = sc.next();

        database.writeAccount(username, password);

        System.out.println("Account created successfully!");
    }
}
