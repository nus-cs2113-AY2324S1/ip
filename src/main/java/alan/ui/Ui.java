package alan.ui;

import java.util.Scanner;

public class Ui {
    public static final int DISPLAYED_INDEX_OFFSET = 1;
    private static final String horizontalDivider = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    Scanner scanner = new Scanner(System.in);

    public String getUserCommand() {
        System.out.print("Input: ");

        String userInput = scanner.nextLine();

        showToUser(horizontalDivider);

        return userInput;
    }

    public void showToUser(String... message) {
        for (String m : message) {
            System.out.println(m);
        }
    }
}
