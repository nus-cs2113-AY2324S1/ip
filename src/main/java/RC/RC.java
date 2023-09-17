package RC;

import RC.command.RCCommand;
import RC.storage.Storage;

import java.io.IOException;
import java.util.Scanner;

public class RC {
    private Storage storage;
    private TaskList tasks;

    public RC(String filePath) {
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (RCException e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        System.out.println("\tHello! I'm RC\n\tWhat can I do for you?\n");
        Scanner in = new Scanner(System.in);
        String input;
        boolean isExit = false;

        while (!isExit) {
            input = in.nextLine().trim();
            try {
                RCCommand command = RCCommand.getCommand(input, tasks);
                command.execute();
                isExit = RCCommand.isExit();
                storage.save(tasks);
            } catch (RCException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("\tBye. Hope to see you again soon!\n");
    }

    public static void main(String[] args) {
        new RC("data").run();
    }
}
