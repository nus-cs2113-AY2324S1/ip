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
    private TaskList taskList;

    public Chatbot() {
        this.ui = new Ui();
        this.storage = new Storage("./tasklist.txt");
        this.parser = new Parser();
        this.taskList = new TaskList();
    }


    /**
     * Runs the main routine of the app
     *
     * @author  Jeremy
     * @since   2023-10-06
     */
    public void run() throws IOException {

        this.ui.showGreetingMessage();

        try {
            ArrayList<String> lines = storage.parseFile(this.taskList.getTaskList());
            for(String line : lines) {
                Command c = this.parser.parseCommand(line);
                c.execute(this.taskList.getTaskList(), false);
            }

            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            while(!input.equals("bye")) {
                Command c = this.parser.parseCommand(input);
                c.execute(this.taskList.getTaskList(), true);
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
