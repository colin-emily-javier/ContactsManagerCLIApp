import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContactsIO {
    public static Path dataFile = Paths.get("src", "contacts.txt");
    private static boolean isMatch;

    public static void displayAllContacts() {
        try {
            List<String> contacts = Files.readAllLines(dataFile);
            showContactsHeader();
            for (String contact : contacts) {
                System.out.println(contact);
            }
        } catch (IOException e) {
            System.err.println("ERROR");
        } finally {
            ContactsManagerApp.continueScript();
        }
    }

    public static String formatPhoneNum(String aNum) {
        String areaCode = null;
        String firstThree = null;
        String lastFour = null;
        if (aNum.length() == 10) {
            areaCode = "(" + aNum.substring(0, 3) + ") ";
            firstThree = aNum.substring(3, 6) + "-";
            lastFour = aNum.substring(6);
        } else if (aNum.length() == 7) {
            areaCode = "(???) ";
            firstThree = aNum.substring(0, 3) + "-";
            lastFour = aNum.substring(3);
        }
        return new StringBuilder().append(areaCode).append(firstThree).append(lastFour).toString();
    }

    public static void addContact() {
        Input entry = new Input();
        isMatch = false;
        System.out.print("Enter new contact name");
        String name = entry.getString();
        new ContactsManagerApp();
        try {
            List<String> currentList = Files.readAllLines(dataFile);
            for (String contact : currentList) {
                if (contact.toLowerCase().contains(name.toLowerCase())) {
                    isMatch = true;
                    System.out.println(contact);
                    System.out.println();
                    new ContactsManagerApp();
                    break;
                }
            }
            if (isMatch) {
                System.out.println("A contact with that name already exists.\n" +
                        "Would you like to continue?");
                if (!entry.YorN()) {
                    ContactsManagerApp.continueScript();
                }
            }

        } catch (IOException e) {
            System.err.println("ERROR");
            ContactsManagerApp.continueScript();
        }
        System.out.print("Enter new contact phone number");
        String phoneNum = formatPhoneNum(entry.getString());
        try {
            Files.write(
                    dataFile,
                    Arrays.asList(String.format("%-30s | %-15s", name, phoneNum)),
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            System.err.println("ERROR");
        } finally {
            ContactsManagerApp.continueScript();
        }
    }

    public static void searchContactsByName() {
        Input entry = new Input();
       isMatch = false;
        System.out.print("Enter contact name for search");
        String search = entry.getString().toLowerCase();
        try {
            List<String> currentList = Files.readAllLines(dataFile);
            for (String contact : currentList) {
                if (contact.toLowerCase().contains(search)) {
                    isMatch = true;
                    break;
                }
            }
            new ContactsManagerApp();
            if (!isMatch) {
                System.out.println("404 not found");

            } else {
                showContactsHeader();
                for (String actualContact : currentList) {
                    if (actualContact.toLowerCase().contains(search)) {
                        System.out.println(actualContact);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("ERROR");
        } finally {
            ContactsManagerApp.continueScript();
        }
    }

    public static void deleteContact() {
        Input entry = new Input();
        isMatch = false;
        System.out.print("Enter contact to delete");
        String search = entry.getString().toLowerCase();
        if (search.isEmpty()) {
            System.out.println("Unable to locate matching contact");
            deleteContact();
        }
        try {
            List<String> currentContacts = Files.readAllLines(dataFile);
            List<String> keepContact = new ArrayList<>();
            for (String removeContact : currentContacts) {
                if (removeContact.toLowerCase().contains(search)) {
                    System.out.println(removeContact);
                    System.out.print("Is this the contact you want to delete? ");
                    isMatch = true;
                    if (entry.YorN()) {
                        new ContactsManagerApp();
                        System.out.println("Contact deleted");
                    } else {
                        keepContact.add(removeContact);
                    }
                } else {
                    keepContact.add(removeContact);
                }
            }
            if (!isMatch) {
                System.out.println("404 not found");
            }
            writeContactsList(keepContact);
        } catch (IOException e) {
            System.err.println("ERROR");
        } finally {
            ContactsManagerApp.continueScript();
        }

    }

    public static void writeContactsList(List<String> anArray) {
        try {
            FileWriter fw = new FileWriter(dataFile.toString());
            for (String person : anArray) {
                fw.write(String.format("%s\n", person));
            }
            fw.close();
        } catch (IOException e) {
            System.err.println("ERROR");
        }
    }

    public static void showContactsHeader() {
        new ContactsManagerApp();
        System.out.printf("%-30s | %-15s\n", "Name", "Phone Number");
        System.out.println("-------------------------------------------");
    }

}