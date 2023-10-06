package chatbot;

import chatbot.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Chatbot {
    private Storage storage;
    private Parser parser;
    private Ui ui;

    public Chatbot() {
        this.ui = new Ui();
        this.storage = new Storage("./tasklist.txt");
        this.parser = new Parser();
    }

    public void run() throws IOException {
        ArrayList<Task> tasks = new ArrayList<Task>(100);
        this.ui.showGreetingMessage();;

        try {
            ArrayList<String> lines = storage.parseFile(tasks);
            for(String line : lines) {
                Command c = this.parser.parseCommand(line);
                c.execute(tasks, false);
            }

            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            while(!input.equals("bye")) {
                Command c = this.parser.parseCommand(input);
                c.execute(tasks, true);
                input = in.nextLine();
            }
        } catch(ChatbotUnknownCommandException e) {
            this.ui.showError(e.getMessage(), true);
        } catch(ChatbotEmptyDescException e) {
            this.ui.showError(e.getMessage(), true);
        } catch (Exception e) {
            this.ui.showError(e.getMessage(), false);
        }

        this.ui.showByeMessage();;
    }

    public static void main(String[] args) throws Exception {
        new Chatbot().run();
    }
}
