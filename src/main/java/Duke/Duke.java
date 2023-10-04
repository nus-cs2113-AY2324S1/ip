package Duke;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    public static void main(String[] args) {        
        final String LOGO = "       _       _        \n"
                + "      | |     | |       \n"
                + "      | | __ _| | _____ \n"
                + "  _   | |/ _` | |/ / _ \\\n"
                + " | |__| | (_| |   <  __/\n"
                + "  \\____/ \\__,_|_|\\_\\___|\n";

        // Bot Greet Message
        System.out.println(LOGO);
        System.out.println("Hello! I'm Jake");
        System.out.println("What can I do for you?");
        Scanner inputReader = new Scanner(System.in);
        String userInput = "";
        ArrayList<Task> botMemory = new ArrayList<Task>();

        // Bot Text File to store botMemory
        File botMemoryFile = new File("data/botMemory.txt");
        String botMemoryFilePath = botMemoryFile.getAbsolutePath();
        System.out.println("botMemory file location:" + botMemoryFilePath);
        System.out.println("Does the botMemory file exist?: " + botMemoryFile.exists());
        
        try { 
            loadFileContents(botMemoryFilePath, botMemory); 
        } catch (FileNotFoundException e) { 
            System.out.println("Error! The botMemory file is not found"); 
        }

        while (true) {
            System.out.print("User: ");
            userInput = inputReader.nextLine();
            String text[] = userInput.split(" ");

            try {
                validateInput(userInput); 
            } catch (DukeException e) { 
                System.out.println("DukeException: " + e.getMessage()); 
                continue; 
            }

            // Bot Exit Message
            if (text[0].equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                inputReader.close();
                break;
            }

            // Bot List feature = iterate botMemory then print everything
            else if (text[0].equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < botMemory.size(); i++) {
                    System.out.println(i + 1 + ". " + botMemory.get(i).getDescription());
                }
            }

            // Bot Mark task feature = set task status to done with markTask();
            else if (text[0].equalsIgnoreCase("mark")) {
                int index = Integer.parseInt(text[1]) - 1;
                if (checkIndexExist(botMemory, index)) {
                botMemory.get(index).markTask();
                System.out.println("Nice! I've marked this task done:");
                System.out.println(botMemory.get(index).getDescription());
                } else {
                    continue;
                }
            }

            // Bot Unmark task feature = unset task status with unMarkTask();
            else if (text[0].equalsIgnoreCase("unmark")) {
                int index = Integer.parseInt(text[1]) - 1;
                if (checkIndexExist(botMemory, index)) {
                    botMemory.get(index).unMarkTask();
                    System.out.println("Ok, I've unmarked the following task:");
                    System.out.println(botMemory.get(index).getDescription());
                } else {
                    continue;
                }
            }

            // Bot Todo task feature (use: todo [desc])
            else if (text[0].equalsIgnoreCase("todo")) {
                int descriptionIndex = userInput.indexOf(text[1]);
                String description = userInput.substring(descriptionIndex);
                Task t = new Todo(description);
                botMemory.add(t);
                System.out.println("Added the following task:");
                System.out.println(t.getDescription());
                System.out.println("Now there are " + Task.getTaskCount() + " tasks in the botMemory list!");
            }

            // Bot Event task feature (use: event [desc] /from [start] /to [end])
            else if (text[0].equalsIgnoreCase("event")) {
                int startIndex = userInput.indexOf("/from ");
                int endIndex = userInput.indexOf("/to ");
                String startTime = userInput.substring(startIndex + 6, endIndex);
                String endTime = userInput.substring(endIndex + 4);
                int descriptionIndex = userInput.indexOf(text[1]);
                String description = userInput.substring(descriptionIndex, startIndex);
                Task t = new Event(description, startTime, endTime);
                botMemory.add(t);
                System.out.println("Added the following task:");
                System.out.println(t.getDescription());
                System.out.println("Now there are " + Task.getTaskCount() + " tasks in the botMemory list!");
            }

            // Bot Deadline task feature (use: deadline [desc] /by [when])
            else if (text[0].equalsIgnoreCase("deadline")) {
                int whenIndex = userInput.indexOf("/by");
                String when = userInput.substring(whenIndex + 4);
                int descriptionIndex = userInput.indexOf(text[1]);
                String description = userInput.substring(descriptionIndex, whenIndex);
                Task t = new Deadline(description, when);
                botMemory.add(t);
                System.out.println("Added the following task:");
                System.out.println(t.getDescription());
                System.out.println("Now there are " + Task.getTaskCount() + " tasks in the botMemory list!");
            }

            // Bot Delete task feature (use: delete [index])
            else if (text[0].equalsIgnoreCase("delete")) {
                int index = Integer.parseInt(text[1]) - 1;
                if (checkIndexExist(botMemory, index)) {
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(botMemory.get(index).getDescription());
                    Task.removeTask(botMemory, index);
                    System.out.println("Now there are " + Task.getTaskCount() + " tasks in the botMemory list!");
                } else {
                    continue;
                }
            }
            
            // Bot default reaction to unknown action
            else {
                System.out.println("I'm sorry, but I don't know what that means :[");
            }
            
            // Execute writeToFile to save the botMemory after every transaction
            try { 
                writeToFile(botMemoryFilePath, botMemory); 
            } catch (IOException e) { 
                System.out.println("Something went wrong: " + e.getMessage()); 
            }
        }
    }

    // Method to check if chosen index is within the botMemory range
    public static boolean checkIndexExist(ArrayList<Task> taskList, int taskIndex) {
        if (taskIndex > taskList.size() - 1 || (taskIndex + 1) < 1) { 
            System.out.println("I'm sorry, but item " + (taskIndex + 1) + " doesn't exist");
            return false;
        }
        else {
            return true;
        }
    }
    
    // Method to validate user input and throw DukeException if it doesn't match requirements
    public static void validateInput(String userInput) throws DukeException {
        String text[] = userInput.split(" ");
        if (text[0].equalsIgnoreCase("todo") && text.length < 2) {
            throw new DukeException("☹ The 'todo' requires a description (use: todo [desc])");
        } else if (text[0].equalsIgnoreCase("event") && (text.length < 5 || !userInput.contains("/from") || !userInput.contains("/to"))) {
            throw new DukeException("☹ The 'event' requires a description, start time, and end time (use: event [desc] /from [start] /to [end])");
        } else if (text[0].equalsIgnoreCase("deadline") && (text.length < 4 || !userInput.contains("/by"))) {
            throw new DukeException("☹ The 'deadline' requires a description and due date (use: deadline [desc] /by [when])");
        } else if (text[0].equalsIgnoreCase("mark") && (text.length != 2 || !isInteger(text[1]))) {
            throw new DukeException("☹ The 'mark' requires an integer to represent an existing task (use: mark [task no.])");
        } else if (text[0].equalsIgnoreCase("unmark") && (text.length != 2 || !isInteger(text[1]))) {
            throw new DukeException("☹ The 'unmark' requires an integer to represent an existing task (use: unmark [task no.])");
        }
    }

    // Method to check if the specified String is Integer
    public static boolean isInteger(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    // Method to load the contents of botMemoryFile into the botMemory ArrayList
    private static void loadFileContents(String filePath, ArrayList<Task> taskList) throws FileNotFoundException {
        File file = new File(filePath); // create a File for the given file path
        Scanner fileReader = new Scanner(file);
        while (fileReader.hasNextLine()) {
            String task[] = fileReader.nextLine().split(";");
            int taskIndex = Integer.parseInt(task[0]);
            String type = task[1];
            int mark = Integer.parseInt(task[2]);
            String desc = task[3];
            switch (type) {
                case "T":
                    Task todo = new Todo(desc);
                    taskList.add(todo);
                    break;
                case "E":
                    String start = task[4];
                    String end = task[5];
                    Task event = new Event(desc, start, end);
                    taskList.add(event);
                    break;
                case "D":
                    String when = task[4];
                    Task deadline = new Deadline(desc, when);
                    taskList.add(deadline);
                    break;
                default:
                    System.out.println("Error the specified task type doesn't exist");
            }
            if (mark == 1) {
                taskList.get(taskIndex-1).markTask();
            }
        }
        fileReader.close();
    }

    // Method to save the contents of botMemory ArrayList into the botMemoryFile
    private static void writeToFile(String filePath, ArrayList<Task> taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        int taskIndex;
        String type;
        int mark;
        String desc;
        for (int i = 0; i < taskList.size(); i++) {
            taskIndex = i + 1;
            type = taskList.get(i).getTaskType();
            mark = taskList.get(i).getTaskStatus() == "X" ? 1 : 0;
            desc = taskList.get(i).getDescriptionText();
            switch (type) {
                case "T":
                    fileWriter.write(taskIndex + ";" + type + ";" + mark + ";" + desc + ";" + System.lineSeparator());
                    break;
                case "E":
                    String start = taskList.get(i).getStart();
                    String end = taskList.get(i).getEnd();
                    fileWriter.write(taskIndex + ";" + type + ";" + mark + ";" + desc + ";" + start + ";" + end + ";" + System.lineSeparator());
                    break;
                case "D":
                    String when = taskList.get(i).getWhen();
                    fileWriter.write(taskIndex + ";" + type + ";" + mark + ";" + desc + ";" + when + ";" + System.lineSeparator());
                    break;
                default:
                    System.out.println("Error the specified task type doesn't exist");
            }
        }
        fileWriter.close();
    }
}