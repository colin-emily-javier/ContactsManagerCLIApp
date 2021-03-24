import java.util.Scanner;

public class Input {
    private final Scanner scanner;

    public int getInt;

    public Input(){
        this.scanner = new Scanner(System.in);
    }

    public String getString(){
        return this.scanner.nextLine();
    }

    public boolean YorN() {
        System.out.print(" Yes[y] or No[n] ? ");
        String input = getString().trim().toLowerCase();
        char checkValue = input.charAt(0);
        return checkValue == 'y';
    }

    public int getInt(int min, int max) {
        int input;
        boolean exceptionFound;
        boolean outOfBounds = false;
        do {
            exceptionFound = false;
            System.out.println("Enter a number between " + min + " and " + max + ": ");
            input = Integer.valueOf(getString());
            try {
                if (input < min || input > max) {
                    System.out.println("Number not within specifications.");
                    outOfBounds = true;
                } else {
                    outOfBounds = false;
                }
            } catch( Exception e ) {
                System.out.println("Invalid input");
                System.out.println();
                exceptionFound = true;
            }

        } while (exceptionFound || outOfBounds);

        return input;
    }

    public int getInt() {
        System.out.print("Enter a number ");
        int input = 0;
        try {
            input = Integer.valueOf(getString());
        }catch (Exception e){
            System.out.println("Invalid input.\n");
            getInt();
        }
        return input;
    }

    public int getInt(String prompt){
        System.out.print(prompt);
        int input = 0;
        try {
            input = Integer.valueOf(getString());
        }catch (Exception e){
            System.out.println("Invalid input \n");
            getInt();
        }
        return input;
    }

    public double getDouble(double min, double max) {
        double input = 0.0;
        System.out.println("Enter a number between " + min + " and " + max + ".");
        try {
            input = Double.valueOf(getString());
        } catch (Exception e) {
            System.out.println("Invalid input");
            getDouble(min, max);
        }
        if (input < min || input > max) {
            System.out.println("Invalid input");
            getDouble(min, max);
        }
        return input;
    }

    public double getDouble() {
        System.out.println("Enter a number ");
        double input = 0.0;
        try {
            input = Double.valueOf(getString());
        } catch (Exception e) {
            System.out.println("Invalid input");
            getDouble();
        }
        return input;
    }
}