import java.io.IOException;
import java.util.Scanner;

public class ContactsManagerApp {
    public static void main(String[] args) {
        mainMenu();

    }

    public static void mainMenu() {
        System.out.println("1. View contacts");
        System.out.println("2. Add a new contact");
        System.out.println("3. Search a contact by name");
        System.out.println("4. Delete an existing contact");
        System.out.println("5. Exit");

        userInput();
    }

        public static void userInput() {
            Input selection = new Input();
            int input = selection.getInt("Enter an option (1, 2, 3, 4 or 5): ");
            switch (input) {
                case 1:
                    ContactsIO.displayAllContacts();
                case 2:
                    ContactsIO.addContact();
                case 3:
                    ContactsIO.searchContactsByName();
                case 4:
                    ContactsIO.deleteContact();
                case 5:
                    System.out.println("Goodbye");
                    break;

                default:
                    System.out.println("I'm sorry but, it appears that you have made an invalid entry");
                    userInput();
            }
        }

        public static void continueScript() {
        System.out.println("Would you like to continue? ");
        Input choice = new Input();
        if(choice.YorN()) {
            mainMenu();
        } else {
            System.out.println("Process Ended");
            System.exit(0);
        }
    }
}
//              888_8888
//        public static void userChoice(){
//
//            Input option = new Input();
//            int choice = option.getInt("Enter an option (1, 2, 3, 4 or 5): ");
//            switch (choice) {
//                case 1:
//                  phoneNumber = 1;
//                    break;
//            }
//        }
