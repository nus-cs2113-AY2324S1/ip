package chatbot;

import chatbot.command.Command;

import java.io.IOException;
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

        ArrayList<String> lines = storage.parseFile(this.taskList.getTaskList());
        for(String line : lines) {
            try {
                Command c = this.parser.parseCommand(line);
                c.execute(this.taskList.getTaskList(), false);
            } catch(ChatbotUnknownCommandException | ChatbotEmptyDescException e) {
                this.ui.showError(e.getMessage(), true);
                continue;
            } catch (Exception e) {
                this.ui.showError(e.getMessage(), false);
            }
        }

        Scanner in = new Scanner(System.in);
        String input = "";
        while(!input.equals("bye")) {
            try{
                input = in.nextLine();
                Command c = this.parser.parseCommand(input);
                c.execute(this.taskList.getTaskList(), true);
            } catch(ChatbotUnknownCommandException | ChatbotEmptyDescException e) {
                this.ui.showError(e.getMessage(), true);
                continue;
            } catch (Exception e) {
                this.ui.showError(e.getMessage(), false);
            }
        }
        this.ui.showByeMessage();
    }

    public static void main(String[] args) throws Exception {
        new Chatbot().run();
    }
}
