import java.util.Scanner;

public class Input {
    private final Scanner scanner;

//    public int getInt;

    public Input() {
        this.scanner = new Scanner(System.in);
    }

    public String getString() {
        return this.scanner.nextLine();
    }

    public boolean YorN() {
        System.out.print(" Yes[y] or No[n] ? ");
        String input = getString().trim().toLowerCase();
        char checkValue = input.charAt(0);
        return checkValue == 'y';
    }

    public int getInt() {
        System.out.print("Enter a number ");
        int input = 0;
        try {
            input = Integer.valueOf(getString());
        } catch (Exception e) {
            System.out.println("Invalid input.\n");
            getInt();
        }
        return input;
    }

    public int getInt(String prompt) {
        System.out.print(prompt);
        int input = 0;
        try {
            input = Integer.valueOf(getString());
        } catch (Exception e) {
            System.out.println("Invalid input \n");
            getInt();
        }
        return input;
    }
}