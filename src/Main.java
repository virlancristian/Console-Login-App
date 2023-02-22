import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File launcher = new File("Launcher.bat");

        if(!launcher.exists()) {
            createLauncher(launcher);
        } else {
            Database database = new Database();
            int userOption;
            Scanner sc = new Scanner(System.in);

            System.out.println("Welcome user! Please type in one of the following options:");
            showOptions();

            userOption = sc.nextInt();

            while (userOption != 3) {
                switch (userOption) {
                    case 1:
                        createAccount(database, sc);
                        System.out.println("What would you like to do next?");
                        break;
                    case 2:
                        loginIntoAccount(database, sc);
                        System.out.println("What would you like to do next?");
                        break;
                    default:
                        System.out.println("Invalid option! please choose between:");
                }

                showOptions();
                userOption = sc.nextInt();
            }

            System.out.println("App closing...");
        }

    }

    private static void loginIntoAccount(Database database, Scanner sc) {
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

    private static void createAccount(Database database, Scanner sc) {
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

    private static void createLauncher(File launcher) {
        try {
            FileWriter writer = new FileWriter(launcher);
            String path = Main.class.getProtectionDomain().getCodeSource().getLocation().toString().substring(6);

            writer.write("@echo off\n");
            writer.write("java -jar \"" + path + "\"" + "\n");
            writer.write("pause\n");
            writer.write("exit\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void showOptions() {
        System.out.println("1 - Create a new account");
        System.out.println("2 - Login into an existing account");
        System.out.println("3 - Terminate the program");
    }
}
