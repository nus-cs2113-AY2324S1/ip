package Ui;

import java.util.ArrayList;

/**
 * Class that handles the user interface
 */
public class Ui {
    private static final String[] greetingLines = {
            "Ah, another day for our delightful rendezvous, don't you agree?",
            "Greetings, mortals, may my presence grace your existence.",
            "Salutations, dear souls, ready to dance on the edge of destiny?",
            "Welcome to my realm, where shadows whisper and secrets beckon.",
            "Hark, for the orchestrator of mayhem has arrived. Ready to play?",
            "A toast to this peculiar meeting – may it be as intriguing as the last.",
            "I trust you've brought your curiosity, for we're about to embark on quite the journey."
    };
    private void printHorizontalLine() {
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Prints the given text with a horizontal line above and below it
     * @param text Text to be printed
     */
    public void printText(String text) {
        printHorizontalLine();
        System.out.println("\t" + text);
        printHorizontalLine();
    }

    /**
     * Prints the given texts with a horizontal line above and below it
     * @param texts Texts to be printed in a list
     */
    public void printMultipleText(String[] texts) {
        printHorizontalLine();
        for (String text : texts) {
            System.out.println("\t" + text);
        }
        printHorizontalLine();
    }


    public void printMultipleText(ArrayList<String> texts) {
        printHorizontalLine();
        for (String text : texts) {
            System.out.println("\t" + text);
        }
        printHorizontalLine();
    }
  
    /**
     * Prints a random greeting message from a list of possible greeting messages
     */
    public void greet() {

        String randomGreetingLine = greetingLines[(int) (Math.random() * greetingLines.length)];

        printMultipleText(new String[] {
                "Hello, I'm",
                "▄▀▄▄▄▄   ▄▀▀▀▀▄  ▄▀▀▀▀▄   ▄▀▀▄▀▀▀▄  ▄▀▀▀█▀▀▄",
                "█ █    ▌ █ █   ▐ █        █   █   █ █    █  ▐",
                "▐ █         ▀▄   █    ▀▄▄ ▐  █▀▀▀▀  ▐   █",
                "  █      ▀▄   █  █     █ █   █         █",
                " ▄▀▄▄▄▄▀  █▀▀▀   ▐▀▄▄▄▄▀ ▐ ▄▀        ▄▀",
                "█     ▐   ▐      ▐        █         █",
                "▐                         ▐         ▐",
                "",
                randomGreetingLine,
                "What can I do for you?"
        });
    }
}
