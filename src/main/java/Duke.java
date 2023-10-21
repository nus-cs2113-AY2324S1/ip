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


    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.getTaskData());
        } catch (IOException e) {
            System.out.println("File not found");
            tasks = new TaskList(new ArrayList<>());
        }

        storage.addTaskSize(tasks.size());

    }

    public void run() throws IOException {

        ui.showWelcome();

        Scanner scanner = new Scanner(System.in);

        String userInput = scanner.nextLine();

        while (!userInput.equals("Bye")) {

            String[] words = userInput.split(" ");

            String initialWord = words[0];

            boolean validWord = Parser.validateInput(initialWord, words, tasks.size());

            if (validWord) {
                switch (initialWord) {
                case "list": {
                    tasks.printList();
                    break;
                }

                case "delete": {
                    int index = Integer.parseInt(words[1]);
                    Task item = tasks.get(index - 1);
                    storage.removeFromFile(index);
                    tasks.delete(index - 1);
                    System.out.println("Noted. I've removed this task: ");
                    System.out.println("[" + item.getType() + "]" + "[" + item.getStatusIcon() + "]" + item);
                    break;
                }

                case "find": {
                    //only a single keyword
                    String keyword = words[1];
                    AtomicBoolean matchFound = new AtomicBoolean(false);
                    System.out.println("Here are the tasks that contains the keyword '" + keyword + "'");
                    tasks.getAllTasks().stream()
                            .filter(task -> {
                                if (!task.getDescription().contains(keyword)) {
                                    return false;
                                }
                                matchFound.set(true);
                                return true;
                            }).forEach(System.out::println);
                    if (!matchFound.get()) {
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
                    storage.updateMark(index, 1);
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
                    storage.updateMark(index, 0);
                    break;

                }
                case "todo": {
                    String newWord = userInput.replace("todo ", "");
                    ToDo currTask = new ToDo(newWord);
                    tasks.add(currTask);
                    System.out.println("added: " + newWord);
                    storage.addToFile("T|0|" + newWord);
                    break;
                }
                case "deadline": {

                    String newWord = userInput.replace("deadline ", "");
                    String[] deadlineArray = newWord.split("/");
                    if (!Parser.validateTimedTasks("deadline", deadlineArray)) {
                        break;
                    }
                    Deadline currTask = new Deadline(deadlineArray[0], deadlineArray[1]);
                    tasks.add(currTask);
                    System.out.println("added: " + deadlineArray[0]);
                    storage.addToFile("D|0|" + deadlineArray[0] + "|" + deadlineArray[1]);
                    break;

                }
                case "event": {
                    String newWord = userInput.replace("event ", "");
                    String[] firstSplit = newWord.split("/");
                    if (!Parser.validateTimedTasks("event", firstSplit)) {
                        break;
                    }
                    Event currTask = new Event(firstSplit[0], firstSplit[1], firstSplit[2]);
                    tasks.add(currTask);

                    storage.addToFile("E|0|" + firstSplit[0] + "|" + firstSplit[1] + "|" + firstSplit[2]);

                    System.out.println("added: " + firstSplit[0] + ", you have now " + tasks.size() + " tasks");

                    break;

                }
                default: {
                    System.out.println("Invalid input, valid task adding commands are: event, deadline and todo");
                    break;
                }
                }
            }

            userInput = scanner.nextLine();

        }

        ui.showGoodbye();

        // Close the scanner when you're done with it
        scanner.close();
    }

    public static void main(String[] args) throws IOException {

        new Duke(dukeDataFile).run();

    }
}
