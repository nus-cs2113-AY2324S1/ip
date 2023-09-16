package RC;

import RC.task.Task;


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
    private static int fileSize = 0;
    private static void createDirectory(Path path) throws IOException {
        Files.createDirectories(path);
    }

    private static void loadFile(ArrayList<Task> tasks, String filePath) throws IOException, RCException {
        BufferedReader inputFile = new BufferedReader(new FileReader(filePath));
        String line;
        System.out.println("\tLoading existing file.");
        int listIndex = 1;
        while ((line = inputFile.readLine()) != null) {
            String[] split = line.split("\\|");
            String command = split[0].toLowerCase().trim();
            String inputCommand;

            switch (command) {
            case "t":
                inputCommand = "todo " + split[2].trim();
                RCCommand.handleCommand(inputCommand, tasks);
                break;
            case "d":
                inputCommand = "deadline " + split[2].trim() + " /by " + split[3].trim();
                RCCommand.handleCommand(inputCommand, tasks);
                break;
            case "e":
                String[] fromToSplit = split[3].split("-");
                inputCommand = "event " + split[2].trim() + " /from " + fromToSplit[0].trim() + " /to " + fromToSplit[1].trim();
                RCCommand.handleCommand(inputCommand, tasks);
                break;
            default:
                System.out.println("Unknown command");
                //delete line
            }
            if (split[1].trim().equals("1")) {
                RCCommand.handleCommand("mark " + listIndex, tasks);
            }
            listIndex++;
            fileSize++;
        }
        inputFile.close();
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
            System.out.println("\tFile not found.");
        } catch (RCException e) {
            System.out.println(e.getMessage());
        }

        while (!isExit) {
            input = in.nextLine().trim();
            try {
                RCCommand.handleCommand(input, tasks);
                isExit = RCCommand.isExit();
                if (tasks.size() > fileSize) {
                    saveFile(tasks, FILE_PATH);
                }
            } catch (RCException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("\tBye. Hope to see you again soon!\n");
    }
}
