import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import commands.*;

public class Duke {
    private static final String dukeDataFile = "./duke.txt";

    private static Storage storage;

    private static TaskList tasks;

    private static Ui ui;

    public static ArrayList<Task> getTaskData() throws IOException {
        File f = new File(dukeDataFile);
        if(!f.exists()){
            return new ArrayList<>();
        }
        ArrayList<Task> currentArray = new ArrayList<>();

        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String currentLine = s.nextLine();
            String[] wordArray = currentLine.split("\\|");
            switch (wordArray[0]){
            case "T":{
                ToDo currTask = new ToDo(wordArray[2]);
                if (Integer.parseInt(wordArray[1]) == 1) {
                    currTask.markStatus();
                }
                currentArray.add(currTask);
                break;
            }
            case "D":{
                Deadline currTask = new Deadline(wordArray[2],wordArray[3]);
                if (Integer.parseInt(wordArray[1]) == 1) {
                    currTask.markStatus();
                }
                currentArray.add(currTask);
                break;
            }
            case "E":{
                Event currTask = new Event(wordArray[2],wordArray[3],wordArray[4]);
                if (Integer.parseInt(wordArray[1]) == 1) {
                    currTask.markStatus();
                }
                currentArray.add(currTask);
                break;
            }
            default:{
                System.out.println("Should not be here");
                break;
            }
            }
        }
        return currentArray;
    }

    /**
     * Checks the validity of input parameters for various task-related methods.
     *
     * @param methodName    The name of the method being called.
     * @param words         An array of words or arguments passed to the method.
     * @return True if the input parameters are valid; false otherwise.
     */
    public static boolean isValidInputs(String methodName, String[] words) {
        int numberOfItems = tasks.size();
        switch (methodName) {
        case "mark": {
            if (words.length == 1 || !words[1].matches("-?\\d+")) {
                ui.showError(DukeException.invalidInputForType(words[0]));
                return false;
            }
            System.out.println("mark " + words[1]);
            int index = Integer.parseInt(words[1]);
            // we need to check if it's out of bounds first
            if (index > numberOfItems || index <= 0) {
                ui.showError(DukeException.outOfBoundsError());
                return false;
            }
            break;
        }
        case "unmark": {
            if (words.length == 1 || !words[1].matches("-?\\d+")) {
                ui.showError(DukeException.invalidInputForType(words[0]));
                return false;
            }
            System.out.println("unmark " + words[1]);
            int index = Integer.parseInt(words[1]);
            // we need to check if it's out of bounds first
            if (index > numberOfItems || index <= 0) {
            ui.showError(DukeException.outOfBoundsError());
            return false;
        }
        break;
    }
    //im busy student... no time do, please give chance XD
        case "todo": {
        System.out.println("to do to be done");
        break;
    }
        case "deadline": {
        System.out.println("deadline to be done");
        break;
    }
        case "event": {
        System.out.println("event to be done");
        break;
    }
        case "delete": {
        System.out.println("delete to be done");
        break;
    }
    default: {
        System.out.println("invalid method name");
        break;
    }
}
        return true;

    }

    public static void main(String[] args) throws IOException {

        ui = new Ui();

        ui.showWelcome();

        try{
            tasks = new TaskList(getTaskData());
        }catch (IOException e){
            System.out.println("File not found");
            tasks = new TaskList(new ArrayList<>());
        }


        storage = new Storage(dukeDataFile,tasks.size());



        Scanner scanner = new Scanner(System.in);

        String userInput = scanner.nextLine();

        while (!userInput.equals("Bye")) {
            String[] words = userInput.split(" ");

            String initialWord = words[0];
            boolean validWord = Parser.parse(initialWord, words, tasks.size());

            if(validWord){
                switch (initialWord) {
                case "list":{
                    tasks.printList();
                    break;
                }

                case "help":{
                    System.out.println("Welcome to your to do list!. Here are the commands");
                    System.out.println("To add Deadlines, type in 'deadline <task name> /<deadline>");
                    System.out.println("example: 'deadline do 2113 assignment /tuesday 2pm");
                    System.out.println("To add Events, dltype in 'evmarkent <event name> /<event start time> /<event end time>");
                    System.out.println("example: 'deadline do 2113 assignment /tuesday 2pm/4pm");
                    break;
                }

                case "delete":{
                    int index = Integer.parseInt(words[1]);
                    Task item = tasks.get(index - 1);
                    storage.removeFromFile(index);
                    tasks.delete(index - 1);
                    System.out.println("Noted. I've removed this task: ");
                    System.out.println("[" + item.getType() + "]" + "[" + item.getStatusIcon() + "]" + item);
                    break;
                }

                case "find":{
                    //only a single keyword
                    String keyword = words[1];
                    AtomicBoolean matchFound = new AtomicBoolean(false);
                    System.out.println("Here are the tasks that contains the keyword '" + keyword + "'");
                    tasks.getAllTasks().stream()
                            .filter(task -> {
                                if(!task.getDescription().contains(keyword)){
                                    return false;
                                }
                                matchFound.set(true);
                                return true;
                            }).forEach(System.out::println);
                    if(!matchFound.get()){
                        System.out.println("No matching items found");
                    }
                    break;

                }

                case "mark": {

                    int index = Integer.parseInt(words[1]);
                    Task currTask = tasks.get(index - 1);
                    currTask.markStatus();
                    System.out.println("Nice! I've marked this task as done: ");
                    String message = String.format("[%s] %s", currTask.getStatusIcon(), currTask.getDescription());
                    System.out.println(message);
                    break;

                }
                case "unmark": {

                    // unmark an item
                    System.out.println("unmark " + words[1]);
                    int index = Integer.parseInt(words[1]);
                    // we need to check if it's out of bounds first
                    Task currTask = tasks.get(index - 1);
                    currTask.unmarkStatus();
                    System.out.println("Nice! I've unmarked this task: ");
                    String message = String.format("[%s] %s", currTask.getStatusIcon(), currTask.getDescription());
                    System.out.println(message);
                    break;

                }
                case "todo": {
                    String newWord = userInput.replace("todo ", "");
                    ToDo currTask = new ToDo(newWord);
                    tasks.add(currTask);
                    System.out.println("added: " + newWord);
                    storage.addToFile("T|0|"+newWord);
                    break;
                }
                case "deadline": {
                    String newWord = userInput.replace("deadline ", "");
                    String[] deadlineArray = newWord.split("/");
                    Deadline currTask = new Deadline(deadlineArray[0], deadlineArray[1]);
                    tasks.add(currTask);
                    System.out.println("added: " + deadlineArray[0]);
                    storage.addToFile("D|0|"+deadlineArray[0]+"|"+deadlineArray[1]);
                    break;

                }
                case "event": {
                    String newWord = userInput.replace("event ", "");
                    String[] firstSplit = newWord.split("/");
                    System.out.println(newWord);
                    Event currTask = new Event(firstSplit[0], firstSplit[1], firstSplit[2]);
                    tasks.add(currTask);

                    storage.addToFile("E|0|"+firstSplit[0]+"|"+firstSplit[1]+"|"+firstSplit[2]);

                    System.out.println("added: " + firstSplit[0] + ", you have now " + tasks.size() + " tasks");

                    break;

                }
                default: {
                    Task currTask = new Task(userInput);
                    tasks.add(currTask);
//                storageArray.add(currTask);
                    storage.addToFile("T|0|"+userInput);
                    System.out.println("added: " + userInput);
                    break;
                }
                }
            }

            userInput = scanner.nextLine();

        }
        String lineBreak = "------------------------------ \n";

        System.out.println(lineBreak + "Bye. Hope to see you again soon!\n");

        // Close the scanner when you're done with it
        scanner.close();

    }
}
