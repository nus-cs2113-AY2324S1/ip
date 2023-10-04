package chatbot;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;

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

    public static void readFile() throws FileFormatException{
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
        else{
            try(BufferedReader br = new BufferedReader(new FileReader("data.txt"))){
                String line;
                while ((line = br.readLine()) != null) {
                    //Do something
                    String [] words = line.split("\\|");

                    for(int i = 0; i < words.length; i++){
                        System.out.println(words[i]);
                    }

                    boolean isDone = (words[0].equals("true"));
                    if (words.length == 2) {
                        taskList.add(new ToDo(words[1], isDone));
                    }
                    else if (words.length == 3) {
                        taskList.add(new Deadline(words[1], isDone, words[2]));
                    }
                    else if (words.length == 4){
                        taskList.add(new Event(words[1], isDone, words[2], words[3]));
                    }
                    else {
                        throw new FileFormatException("Save file corrupted");
                    }
                }
            }
            catch (IOException e) {
                System.out.println("Error reading file!");
            }
        }
    }

    public static void main(String[] args) throws InputException, FileFormatException{

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
                taskList.add(new ToDo(description, false));

                System.out.println("Created new ToDo:");
                System.out.println(description);
            }

            else if (keyword.equals("deadline")) {
                if(!response.contains("/by")){
                    throw new InputException("Input Exception: Please ensure that the deadline input is in the correct format\ndeadline [description] /by [date]");
                }

                int dateIndex = response.indexOf("/by") + 3;
                String date = response.substring(dateIndex);
                String description = response.substring(8, dateIndex - 3);

                taskList.add(new Deadline(description, false, date));

                System.out.println("Created new Deadline:");
                System.out.println(description + " (by:" + date + ")");
            }

            else if (keyword.equals("event")) {
                if(!(response.contains("/from") && response.contains("/to"))){
                    throw new InputException(("Input Exception: Please ensure that the event input is in the correct format\nevent [description] /from [date] /to [date]"));
                }

                int fromIndex = response.indexOf("/from") + 5;
                int toIndex = response.indexOf("/to") + 3;

                String fromDate = response.substring(fromIndex, toIndex - 3);
                String toDate = response.substring(toIndex);

                String description = response.substring(5, fromIndex - 5);

                taskList.add(new Event(description, false, fromDate, toDate));

                System.out.println("Created new Event:");
                System.out.println(description + " (from:" + fromDate + " to:" + toDate + ")");
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