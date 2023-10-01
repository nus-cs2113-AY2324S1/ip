import java.io.IOException;
import java.util.Scanner;

import duke.*;

public class Duke {

    private final TaskList taskList = new TaskList();
    private final FileManager writer;

    public void run() throws IOException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            String[] userInput = input.trim().split("\\s+");
            Parser parser = new Parser(userInput, taskList, writer);
            parser.parseInput();
        }
    }

    public Duke() throws IOException {
        new Ui().printGreeting();
        writer = new FileManager();
        TaskList.taskList = writer.read();
    }

    public static void main(String[] args) throws IOException {
        new Duke().run();
    }
}