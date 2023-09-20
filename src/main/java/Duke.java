import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Scanner;

import commands.*;

public class Duke {
//    private static ArrayList<Task> storageArray;
    private static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter("src/main/java/data/duke.txt",true);
        // Create a BufferedWriter for efficient writing
        BufferedWriter bufferedWriter = new BufferedWriter(fw);
        bufferedWriter.newLine();
        bufferedWriter.write(textToAdd);
        bufferedWriter.close();
        fw.close();
    }
    public static ArrayList<Task> getTaskData() throws IOException {
        File f = new File("src/main/java/data/duke.txt");
        if(!f.exists()){
            System.out.println("File does not exist");
            return new ArrayList<>();
        }
        ArrayList<Task> currentArray = new ArrayList<>();

        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String currentLine = s.nextLine();
            String[] wordArray = currentLine.split("\\|");
            System.out.println(currentLine);
            System.out.println(wordArray[0]);
            System.out.println(wordArray[1]);
            System.out.println(wordArray[2]);
            System.out.println(wordArray.length);
            switch (wordArray[0]){
            case "T":{
                ToDo currTask = new ToDo(wordArray[2]);
                if (Integer.parseInt(wordArray[1]) == 1) {
                    currTask.markStatus();
                }
                currentArray.add(currTask);
                System.out.println("To do added");
                break;
            }
            case "D":{
                Deadline currTask = new Deadline(wordArray[2],wordArray[3]);
                if (Integer.parseInt(wordArray[1]) == 1) {
                    currTask.markStatus();
                }
                currentArray.add(currTask);
                System.out.println("Deadline added");
                break;
            }
            case "E":{
                Event currTask = new Event(wordArray[2],wordArray[3],wordArray[4]);
                if (Integer.parseInt(wordArray[1]) == 1) {
                    currTask.markStatus();
                }
                currentArray.add(currTask);
                System.out.println("Event added");
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
     * Prints a list of tasks with their status icons and descriptions.
     *
     * @param storageArray An array of Task objects representing the list of tasks.
     */

    public static void printList(ArrayList<Task> storageArray) {
        System.out.println("Here are the tasks in your list:");
        int count = 1;
        for(Task task: storageArray){
            System.out.println(
                    count + "." + "[" + task.getType() + "]" + "[" + task.getStatusIcon() + "]" + task);
            count += 1;
        }
    }

    /**
     * Checks the validity of input parameters for various task-related methods.
     *
     * @param methodName    The name of the method being called.
     * @param numberOfItems The total number of items in a list.
     * @param words         An array of words or arguments passed to the method.
     * @return True if the input parameters are valid; false otherwise.
     */
    public static boolean isValidInputs(String methodName, int numberOfItems, String[] words) {
        switch (methodName) {
        case "mark": {
            if (words.length == 1 || !words[1].matches("-?\\d+")) {
                System.out.println(DukeException.invalidInputForType(words[0]));
                return false;
            }
            System.out.println("mark " + words[1]);
            int index = Integer.parseInt(words[1]);
            // we need to check if it's out of bounds first
            if (index > numberOfItems || index <= 0) {
                System.out.println(DukeException.outOfBoundsError());
                return false;
            }
            break;
        }
        case "unmark": {
            if (words.length == 1 || !words[1].matches("-?\\d+")) {
                System.out.println(DukeException.invalidInputForType(words[0]));
                return false;
            }
            System.out.println("unmark " + words[1]);
            int index = Integer.parseInt(words[1]);
            // we need to check if it's out of bounds first
            if (index > numberOfItems || index <= 0) {
                System.out.println(DukeException.outOfBoundsError());
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

        String logoRyan = " ____  _          \n"
                + "|  _ \\| | ___   __\n"
                + "| |_) | |/ _ \\ / _|\n"
                + "|  __/| | (_) | (__\n"
                + "|_|   |_|\\___/ \\___|\n";

        ArrayList<Task> storageArray;
        try{
            storageArray = getTaskData();
        }catch (IOException e){
            System.out.println("File not found");
            storageArray = new ArrayList<>();
        }

        int numOfItems = 0;



        String line = "Hello! I'm Ryan Loh \nWhat can I do for you?\nType anything to be added to your magic list :D\n";
        System.out.println(line + logoRyan);
        Scanner scanner = new Scanner(System.in);

        String userInput = scanner.nextLine();

        while (!userInput.equals("Bye")) {
            String[] words = userInput.split(" ");

            String initialWord = words[0];

            switch (initialWord) {
            case "list":{
                printList(storageArray);
                break;
            }

            case "help":{
                System.out.println("Welcome to your to do list!. Here are the commands");
                System.out.println("To add Deadlines, type in 'deadline <task name> /<deadline>");
                System.out.println("example: 'deadline do 2113 assignment /tuesday 2pm");
                System.out.println("To add Events, type in 'evmarkent <event name> /<event start time> /<event end time>");
                System.out.println("example: 'deadline do 2113 assignment /tuesday 2pm/4pm");
                break;
            }



            case "mark": {
                if (!isValidInputs(initialWord, numOfItems, words)) {
                    break;
                }
                int index = Integer.parseInt(words[1]);
                Task currTask = storageArray.get(index - 1);
                currTask.markStatus();
                System.out.println("Nice! I've marked this task as done: ");
                String message = String.format("[%s] %s", currTask.getStatusIcon(), currTask.getDescription());
                System.out.println(message);
                break;

            }
            case "unmark": {
                if (!isValidInputs(initialWord, numOfItems, words)) {
                    break;
                }
                // unmark an item
                System.out.println("unmark " + words[1]);
                int index = Integer.parseInt(words[1]);
                // we need to check if it's out of bounds first
                Task currTask = storageArray.get(index - 1);
                currTask.unmarkStatus();
                System.out.println("Nice! I've unmarked this task: ");
                String message = String.format("[%s] %s", currTask.getStatusIcon(), currTask.getDescription());
                System.out.println(message);
                break;

            }
            case "todo": {
                String newWord = userInput.replace("todo ", "");
                ToDo currTask = new ToDo(newWord);
                storageArray.add(currTask);
                System.out.println("added: " + newWord);
                numOfItems += 1;
                writeToFile("T|0|"+newWord);
                break;
            }
            case "deadline": {
                String newWord = userInput.replace("deadline ", "");
                String[] deadlineArray = newWord.split("/");
                Deadline currTask = new Deadline(deadlineArray[0], deadlineArray[1]);
                storageArray.add(currTask);
                System.out.println("added: " + deadlineArray[0]);
                writeToFile("D|0|"+deadlineArray[0]+"|"+deadlineArray[1]);
                numOfItems += 1;
                break;

            }
            case "event": {
                String newWord = userInput.replace("event ", "");
                String[] firstSplit = newWord.split("/");
                System.out.println(newWord);
                Event currTask = new Event(firstSplit[0], firstSplit[1], firstSplit[2]);
                storageArray.add(currTask);

                numOfItems += 1;
                writeToFile("E|0|"+firstSplit[0]+"|"+firstSplit[1]+"|"+firstSplit[2]);

                System.out.println("added: " + firstSplit[0] + ", you have now " + numOfItems + " tasks");

                break;

            }
            default: {
                Task currTask = new Task(userInput);
                storageArray.add(currTask);
                writeToFile("T|0|"+userInput);
                System.out.println("added: " + userInput);
                numOfItems += 1;
                break;
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
