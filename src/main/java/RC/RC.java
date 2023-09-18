package RC;

import RC.command.Exit;
import RC.command.RCCommand;
import RC.storage.Storage;

import java.util.Scanner;

public class RC {
    private Storage storage;
    private TaskList taskList;

    public RC(String filePath) {
        this.storage = new Storage(filePath);
        taskList = new TaskList();
        try {
            storage.load(taskList);
        } catch (RCException e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() {
        System.out.println("\tHello! I'm RC\n\tWhat can I do for you?\n");
        Scanner in = new Scanner(System.in);
        String input;
        RCCommand command = null;

        while (!(command instanceof Exit)) {
            input = in.nextLine().trim();
            try {
                command = RCCommand.getCommand(input);
                command.execute(taskList);
            } catch (RCException e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            storage.save(taskList);
        } catch (RCException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\tBye. Hope to see you again soon!\n");
    }

    public static void main(String[] args) {
        new RC("data").run();
    }
}
