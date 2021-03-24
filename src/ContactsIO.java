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
            System.err.println("Contacts file not found.");
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
            areaCode = "(xxx) ";
            firstThree = aNum.substring(0, 3) + "-";
            lastFour = aNum.substring(3);
        }
        return new StringBuilder().append(areaCode).append(firstThree).append(lastFour).toString();
    }

    public static void addContact() {
        Input entry = new Input();
        isMatch = false;
        System.out.print("Enter your new contacts name: ");
        String name = entry.getString();
        new ContactsManagerApp();
        try {
            List<String> currentList = Files.readAllLines(dataFile);
            for (String contact : currentList) {
                if (contact.toLowerCase().contains(name.toLowerCase())) {
                    isMatch = true;
                    System.out.println(contact);
                    new ContactsManagerApp();
                    break;
                }
            }
            if (isMatch) {
                System.out.println("A name match was found in your contact's list.\n" +
                        "Would you like to continue adding a new contact?");
                if (!entry.YorN()) {
                    ContactsManagerApp.continueScript();
                }
            }

        } catch (IOException e) {
            System.err.println("Contact file not found.");
            ContactsManagerApp.continueScript();
        }
        System.out.print("Enter new contact's phone number:(numbers only, please) ");
        String phoneNum = formatPhoneNum(entry.getString());
        try {
            Files.write(
                    dataFile,
                    Arrays.asList(String.format("%-20s | %-20s", name, phoneNum)),
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            System.err.println("Unable to create new contact. Target file not found.");
        } finally {
            ContactsManagerApp.continueScript();
        }
    }

    public static void searchContactsByName() {
        Input entry = new Input();
       isMatch = false;
        System.out.print("Enter the name you're looking for: ");
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
            System.err.println("Unable to locate contacts.");
        } finally {
            ContactsManagerApp.continueScript();
        }
    }

    public static void deleteContact() {
        Input entry = new Input();
        isMatch = false;
        System.out.print("Enter contact to delete ");
        String search = entry.getString().toLowerCase();
        if (search.isEmpty()) {
            System.out.println("Enter contact's information ");
            deleteContact();
        }
        try {
            List<String> hereForNow = Files.readAllLines(dataFile);
            List<String> keepers = new ArrayList<>();
            for (String potentialVictim : hereForNow) {
                if (potentialVictim.toLowerCase().contains(search)) {
                    System.out.println(potentialVictim);
                    System.out.print("Is this the contact you want to delete? ");
                    isMatch = true;
                    if (entry.YorN()) {
                        new ContactsManagerApp();
                        System.out.println("Contact deleted. ");
                    } else {
                        keepers.add(potentialVictim);
                    }
                } else {
                    keepers.add(potentialVictim);
                }
            }
            if (!isMatch) {
                System.out.println("404 not found.");
            }
            writeContactsList(keepers);
        } catch (IOException e) {
            System.err.println("Contacts file not found.");
        } finally {
            ContactsManagerApp.continueScript();
        }

    }

    public static void writeContactsList(ArrayList<Contact> anArray) {
        try {
            FileWriter fw = new FileWriter(dataFile.toString());
            for (Contact person : anArray) {
                fw.write(String.format("%-20s | %-20s\n", person.getName(), person.getPhoneNumber()));
            }
            fw.close();
        } catch (IOException e) {
            e.getMessage();
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
            System.err.println("Error. Contacts file could not be created or overwritten");
        }
    }

    public static void showContactsHeader() {
        new ContactsManagerApp();
        System.out.printf("%-20s | %-20s\n", "Name", "Phone Number");
        System.out.println("-------------------------------------------");
    }

}