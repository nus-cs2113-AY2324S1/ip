package UI;

import java.util.ArrayList;

/**
 * Provides user interface functionality for the Barbie-themed task manager.
 */
public class Ui {
    /**
     * Prints a horizontal line separator to the console.
     */
    public static void printLine() {
        System.out.println("_____________________________________________________________");
    }

    /**
     * Prints an array of text messages to the console.
     *
     * @param texts An array of text messages to be printed.
     */
    public static void printTexts(String[] texts) {
        printLine();
        for (String text : texts) {
            System.out.println("\t" + text);
        }
        printLine();
    }

    /**
     * Prints an array of task-related text messages to the console.
     *
     * @param startLine The initial line of text to be printed.
     * @param taskTexts An ArrayList of task-related text messages to be printed.
     */
    public static void printTexts(String startLine, ArrayList<String> taskTexts) {
        printLine();
        System.out.println("\t" + startLine);
        for (String text : taskTexts) {
            System.out.println("\t" + text);
        }
        printLine();
    }

    /**
     * Displays a greeting message with a Barbie-themed logo to console.
     */
    public static void greetUser() {
        String hiLogo = " ____  __.___________ _______\n"
                + "\t|    |/ _|\\_   _____/ \\      \\\n"
                + "\t|      <   |    __)_  /   |   \\\n"
                + "\t|    |  \\  |        \\/    |    \\\n"
                + "\t|____|__ \\/_________/\\____|__  /\n"
                + "\t\\/        \\/         \\/";
        printTexts(new String[] {
                "Greetings, fashionista! I'm",
                hiLogo,
                "your dream planner extraordinaire.",
                "Ready to make your day as fabulous as a Barbie runway show?"
        });
    }

    /**
     * Displays a farewell message with a Barbie-themed logo to the console.
     */
    public static void byeUser() {
        String byeLogo = "  ___________________  _____ __________ ____  __.____    .___ _______    ________\n"
                + "\t/   _____/\\______   \\/  _  \\\\______   \\    |/ _|    |   |   |\\      \\  /  _____/\n"
                + "\t \\_____  \\  |     ___/  /_\\  \\|       _/      < |    |   |   |/   |   \\/   \\  ___\n"
                + "\t /        \\ |    |  /    |    \\    |   \\    |  \\|    |___|   /    |    \\    \\_\\  \\\n"
                + "\t/_______  / |____|  \\____|__  /____|_  /____|__ \\_______ \\___\\____|__  /\\______  /\n"
                + "\t        \\/                  \\/       \\/        \\/       \\/           \\/        \\/";
        printTexts(new String[] {
                "Until we meet again, my fellow dream chaser! Keep",
                byeLogo,
        });
    }
}
