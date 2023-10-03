package chatbot;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;

public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void updateFile() {
        String filepath = "data.txt";

        try (FileWriter filewriter = new FileWriter(filepath)) {
            for(int i = 0; i < taskList.size(); i++){
                Task task = taskList.get(i);
                String description = task.getDescription();
                String isDone = task.getDone();

                if(task instanceof ToDo){
                    filewriter.write(isDone + "|" + description + "\n");
                    continue;
                }

                String time = task.getTime();
                filewriter.write(isDone + "|" + description + "|" + time + "\n");
            }
        }
        catch (IOException e){
            System.out.println("Error writing file");
        }
    }

    public static void readFile() {
        String filepath = "data.txt";

        File file = new File(filepath);

        if (!file.exists()) {
            try {
                file.createNewFile();
            }
            catch (IOException e){
                System.out.println("File creation failed");
            }
        }
    }

    public static void main(String[] args) throws InputException{

        System.out.println("Hello! I'm TheChattyFatty");

        readFile();

        Scanner scanner = new Scanner(System.in);

        while(true) {
            updateFile();
            printLine();
            System.out.println("What can I do for you?");
            System.out.println("Functionality:");
            System.out.println("todo [task name] (Creates a new todo)");
            System.out.println("deadline [task name] (Creates a new deadline)");
            System.out.println("event [event name] (Creates a new event)");
            System.out.println("mark [task number] (Marks todo/event/deadline as done");
            System.out.println("unmark [task number (Marks todo/event/deadline as not done yet");
            System.out.println("list (displays all todos/events/deadlines)");
            String response = scanner.nextLine();

            // For handling keyword responses with multiple words
            String[] words = response.split(" ");

            String keyword = words[0];

            // Handle "bye" keyword
            if(response.equals("bye")) {
                break;
            }

            // Handle "list" keyword
            else if (response.equals("list")) {
                for (int i = 0; i < taskList.size(); i++) {
                    taskList.get(i).show();
                }
            }

            else if (keyword.equals("mark")) {
                // Check exception: number of words is not 2
                if(words.length != 2){
                    throw new InputException("Input Exception: Please enter with correct format (mark [Integer])");
                }
                // Check exception: second word cannot be converted to integer or integer out of bounds
                try{
                    int markIndex = Integer.parseInt(words[1]);

                    if(markIndex < 1 || markIndex > taskList.size()){
                        throw new InputException("Input Exception: Please enter a positive integer less than or equal to current number of tasks (" + taskList.size() + " task(s))");
                    }

                    taskList.get(markIndex - 1).mark();
                }
                catch(NumberFormatException e){
                    throw new InputException("Input Exception: Please enter with correct format (mark [Integer])");
                }
            }

            else if (keyword.equals("unmark")) {
                // Check exception: number of words is not 2
                if(words.length != 2){
                    throw new InputException("Input Exception: Please enter with correct format (unmark [Integer])");
                }

                // Check exception: second word cannot be converted to integer or integer out of bounds
                try{
                    int markIndex = Integer.parseInt(words[1]);

                    if(markIndex < 1 || markIndex > taskList.size()){
                        throw new InputException("Input Exception: Please enter a positive integer less than or equal to current number of tasks (" + taskList.size() + ")");
                    }

                    taskList.get(markIndex - 1).unmark();
                }
                catch(NumberFormatException e){
                    throw new InputException("Input Exception: Please enter with correct format (unmark [Integer])");
                }
            }

            else if (keyword.equals("todo")) {
                String description = response.substring(5);
                taskList.add(new ToDo(description));

                System.out.println("Created new ToDo:");
                System.out.println(description);
            }

            else if (keyword.equals("deadline")) {
                String description = response.substring(9);
                System.out.println("Please enter the deadline:");
                String deadline = scanner.nextLine();

                taskList.add(new Deadline(description, deadline));

                System.out.println("Created new Deadline:");
                System.out.println(description);
                System.out.println("Due: " + deadline);
            }

            else if (keyword.equals("event")) {
                String description = response.substring(6);
                System.out.println("Please enter event start period:");
                String start = scanner.nextLine();
                System.out.println("Please enter event end period:");
                String end = scanner.nextLine();

                taskList.add(new Event(description, start, end));

                System.out.println("Created new Event:");
                System.out.println(description);
                System.out.println("From: " + start);
                System.out.println("To: " + end);
            }

            else if(keyword.equals("delete")) {
                if (words.length != 2) {
                    throw new InputException("Input Exception: Please enter with correct format (delete [Integer])");
                }

                try{
                    int markIndex = Integer.parseInt(words[1]);

                    if(markIndex < 1 || markIndex > taskList.size()){
                        throw new InputException("Input Exception: Please enter a positive integer less than or equal to current number of tasks (" + taskList.size() + " task(s))");
                    }

                    taskList.remove(markIndex - 1);
                    System.out.println("Task at index: " + markIndex + " successfully deleted");
                }
                catch(NumberFormatException e){
                    throw new InputException("Input Exception: Please enter with correct format (mark [Integer])");
                }
            }

            else{
                throw new InputException("Input Exception: Invalid input keyword");
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}