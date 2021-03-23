import java.io.IOException;
import java.util.Scanner;

public class ContactsManagerApp {
    public static void main (String[] args) throws IOException {
        mainMenu();
//        userChoice();
        Scanner scanner = new Scanner(System.in);

    }
        public static void mainMenu(){
            System.out.println("1. View contacts");
            System.out.println("2. Add a new contact");
            System.out.println("3. Search a contact by name");
            System.out.println("4. Delete an existing contact");
            System.out.println("5. Exit");
            System.out.println("Enter an option (1, 2, 3, 4 or 5): ");
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
