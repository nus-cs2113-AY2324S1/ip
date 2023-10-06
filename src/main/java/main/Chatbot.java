package main;

import ascii.AsciiArt;
import data.LoadData;
import data.StoreData;
import java.util.Scanner;
/**
 * Main class that is initiated upon start up to welcome user and read inputs
 */
public class Chatbot {
    public static void main(String[] args) {
        System.out.println("Hello I'm Rias-chan!");
        System.out.println(AsciiArt.getArt("picture"));
        System.out.println("Welcome back goshujin-sama, what can I do for you?");
        System.out.println();
        Parser processor = new Parser();
        LoadData.load(processor);
        waitForResponse(processor);
        StoreData.store(processor);
        System.out.println("Bye masta! " + AsciiArt.getArt("kiss"));
    }

    /**
     * Wait for user to type and process the input
     *
     * @param processor The main processor of the code
     */
    public static void waitForResponse(Parser processor) {
        Scanner scanner = new Scanner(System.in);
        String response;
        do {
            response = scanner.nextLine();
            if (!"bye".equalsIgnoreCase(response)) {
                System.out.println(AsciiArt.getArt("line"));
                processor.process(response);
                System.out.println(AsciiArt.getArt("line"));
            }
        } while (!"bye".equalsIgnoreCase(response));

        scanner.close();
    }
}