package main;

import ascii.AsciiArt;
import data.LoadData;
import data.StoreData;
import java.util.Scanner;

public class Chatbot {
    public static void main(String[] args) {
        System.out.println("Hello I'm Rias-chan!");
        System.out.println(AsciiArt.getArt("picture"));
        System.out.println("Welcome back goshujin-sama, what can I do for you?");
        System.out.println();
        ResponseProcessor processor = new ResponseProcessor();
        LoadData.load(processor);
        waitForResponse(processor);
        StoreData.store(processor);
        System.out.println("Bye masta! " + AsciiArt.getArt("kiss"));
    }

    public static void waitForResponse(ResponseProcessor processor) {
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