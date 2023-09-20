import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import commands.*;

public class Duke {
    private static ArrayList<Task> storageArray;
    private static String dukeDataFile = "src/main/java/data/duke.txt";
    private static void addToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(dukeDataFile,true);
        // Create a BufferedWriter for efficient writing
        BufferedWriter bufferedWriter = new BufferedWriter(fw);
        bufferedWriter.newLine();
        bufferedWriter.write(textToAdd);
        bufferedWriter.close();
        fw.close();
    }

    private static void removeFromFile(int lineToRemove){

        // Create a temporary file to write the modified content
        File tempFile = new File("temp.txt");

        try (
                BufferedReader reader = new BufferedReader(new FileReader(dukeDataFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        ) {
            String currentLine;
            int lineNumber = 0;

            while ((currentLine = reader.readLine()) != null) {
                lineNumber++;

                // Check if the current line is the one to remove
                if (lineNumber != lineToRemove) {
                    writer.write(currentLine);
                    writer.newLine(); // Add a new line after writing
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading/writing the file: " + e.getMessage());
        }

        // Replace the original file with the temporary file
        if (tempFile.renameTo(new File(dukeDataFile))) {
            System.out.println("Line removed successfully.");
        } else {
            System.err.println("Error renaming the temporary file.");
        }
    }



    public static ArrayList<Task> getTaskData() throws IOException {
        File f = new File(dukeDataFile);
        if(!f.exists()){
            System.out.println("File does not exist");
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
     * @param words         An array of words or arguments passed to the method.
     * @return True if the input parameters are valid; false otherwise.
     */
    public static boolean isValidInputs(String methodName, String[] words) {
        int numberOfItems = storageArray.size();
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

        try{
            storageArray = getTaskData();
        }catch (IOException e){
            System.out.println("File not found");
            storageArray = new ArrayList<>();
        }




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
                System.out.println("To add Events, dltype in 'evmarkent <event name> /<event start time> /<event end time>");
                System.out.println("example: 'deadline do 2113 assignment /tuesday 2pm/4pm");
                break;
            }

            case "delete":{
                if (!isValidInputs(initialWord, words)) {
                    break;
                }
                int index = Integer.parseInt(words[1]);
                Task item = storageArray.get(index - 1);
                removeFromFile(index);
                storageArray.remove(index - 1);
                System.out.println("Noted. I've removed this task: ");
                System.out.println("[" + item.getType() + "]" + "[" + item.getStatusIcon() + "]" + item);
                break;
            }

            case "mark": {
                if (!isValidInputs(initialWord, words)) {
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
                if (!isValidInputs(initialWord, words)) {
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
                addToFile("T|0|"+newWord);
                break;
            }
            case "deadline": {
                String newWord = userInput.replace("deadline ", "");
                String[] deadlineArray = newWord.split("/");
                Deadline currTask = new Deadline(deadlineArray[0], deadlineArray[1]);
                storageArray.add(currTask);
                System.out.println("added: " + deadlineArray[0]);
                addToFile("D|0|"+deadlineArray[0]+"|"+deadlineArray[1]);
                break;

            }
            case "event": {
                String newWord = userInput.replace("event ", "");
                String[] firstSplit = newWord.split("/");
                System.out.println(newWord);
                Event currTask = new Event(firstSplit[0], firstSplit[1], firstSplit[2]);
                storageArray.add(currTask);

                addToFile("E|0|"+firstSplit[0]+"|"+firstSplit[1]+"|"+firstSplit[2]);

                System.out.println("added: " + firstSplit[0] + ", you have now " + storageArray.size() + " tasks");

                break;

            }
            default: {
                Task currTask = new Task(userInput);
                storageArray.add(currTask);
                addToFile("T|0|"+userInput);
                System.out.println("added: " + userInput);
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
