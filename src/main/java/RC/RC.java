package RC;

import RC.task.Deadline;
import RC.task.Event;
import RC.task.Task;
import RC.task.Todo;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class RC {
    private static final String FILE_PATH = "data/tasks.txt";
    private static void createDirectory(Path path) throws IOException {
        System.out.println("\tDirectory doesn't exist. Creating directory...");
        Files.createDirectories(path);
    }

    private static void loadFile(ArrayList<Task> tasks, String filePath) throws IOException, RCException {
        BufferedReader inputFile = new BufferedReader(new FileReader(filePath));
        String line;
        System.out.println("\tLoading existing file...");

        while ((line = inputFile.readLine()) != null) {
            String[] split = line.split("\\|");
            String command = split[0].toLowerCase().trim();
            String inputCommand;
            boolean isDone;

            switch (command) {
            case "t":
                inputCommand = split[2].trim();
                Todo todo = new Todo(inputCommand);
                isDone = split[1].trim().equals("1");
                if (isDone) {
                    todo.markAsDone();
                }
                tasks.add(todo);
                break;
            case "d":
                inputCommand = split[2].trim();
                String byCommand = split[3].trim();
                Deadline deadline = new Deadline(inputCommand, byCommand);
                isDone = split[1].trim().equals("1");
                if (isDone) {
                    deadline.markAsDone();
                }
                tasks.add(deadline);
                break;
            case "e":
                inputCommand = split[2].trim();
                String fromString = split[3].trim();
                String toString = split[4].trim();
                Event event = new Event(inputCommand, fromString, toString);
                isDone = split[1].trim().equals("1");
                if (isDone) {
                    event.markAsDone();
                }
                tasks.add(event);
                break;
            default:
                System.out.println("\tUnknown command");
            }
        }
        inputFile.close();
        System.out.println("\tLoading is complete.");
    }

    private static void saveFile(ArrayList<Task> tasks, String filePath) throws RCException {
        RCCommand.writeToFile(filePath, tasks);
    }

    public static void main(String[] args) {
        System.out.println("\tHello! I'm RC\n\tWhat can I do for you?\n");
        Scanner in = new Scanner(System.in);
        String input;
        ArrayList<Task> tasks = new ArrayList<>();
        boolean isExit = false;

        Path path = Paths.get("data");
        if (!Files.isDirectory(path)) {
            try {
                createDirectory(path);
            } catch (IOException e) {
                System.out.println("\terror: " + e.getMessage());
            }
        }

        try {
            loadFile(tasks, FILE_PATH);
        } catch (IOException e) {
            System.out.println("\tFile not found.\n\tNew file will be created.");
        } catch (RCException e) {
            System.out.println(e.getMessage());
        }

        while (!isExit) {
            input = in.nextLine().trim();
            try {
                RCCommand.handleCommand(input, tasks);
                isExit = RCCommand.isExit();
                saveFile(tasks, FILE_PATH);
            } catch (RCException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("\tBye. Hope to see you again soon!\n");
    }
}
