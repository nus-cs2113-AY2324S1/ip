import java.io.FileReader;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
public class Duke {
    protected final static String LINE = "_______________________________________________";
    protected final static String GREET = "Hello! I'm Elwin\n" + "What can I do for you?";
    protected final static String EXIT = "Bye. Hope to see you again soon!";
    protected final static String LIST = "Here are the tasks in your list:";
    protected final static String MARK = "Nice! I've marked this task as done:";
    protected final static String UNMARK = "OK, I've marked this task as not done yet:";

    private static void print(String message){
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }
    private static void writeToFile(List<Task> list){
        try{
            FileWriter fileWriter = new FileWriter("./data/duke.txt");
            for(Task task : list){
                fileWriter.write(task.toFile()+"\n");
            }
            fileWriter.flush();
            fileWriter.close();
        }catch(IOException e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> todoList = new ArrayList<Task>();
        try{
            FileReader fileReader = new FileReader("./data/duke.txt");
            Scanner fileScanner = new Scanner(fileReader);
            while(fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();
                if(line.equals("\n")){
                    break;
                }
                String[] task = line.split(" \\| ");
                if(task[0].equals("T")){
                    todoList.add(new Todo (task[2]));
                }else if(task[0].equals("D")){
                    todoList.add(new Deadline (task[2], "by "+task[3]));
                }else if(task[0].equals("E")){
                    todoList.add(new Event (task[2], "from "+task[3], "to "+task[4]));
                }
                if(task[1].equals("1")){
                    todoList.get(todoList.size()-1).markAsDone();
                }
            }
            fileScanner.close();
            fileReader.close();
        }catch(IOException e){
            try{
                File file = new File("./data/duke.txt");
                file.getParentFile().mkdirs();
                file.createNewFile();
            }catch(IOException ex){
                System.out.println("☹ OOPS!!! The file has existed.");
            }
        }catch(DukeException e){
            System.out.println("☹ OOPS!!! Wrong format in file.");
        }
        print(GREET);
        while(true){
            String input = scanner.nextLine();
            if(input.equals("bye")){
                break;
            }else if(input.equals("list")){
                System.out.println(LINE);
                System.out.println(LIST);
                for(int i = 0; i < todoList.size(); i++){
                    System.out.println(i+1 + "." + todoList.get(i));
                }
                System.out.println(LINE);
            }else if(input.matches("^mark \\d+$")) {
                int index = Integer.parseInt(input.substring(5));
                try{
                    todoList.get(index-1).markAsDone();
                }catch(IndexOutOfBoundsException e){
                    print("☹ OOPS!!! The task number is not in the list.");
                    continue;
                }
                writeToFile(todoList);
                print(MARK+"\n  "+todoList.get(index-1));
            }else if(input.matches("^unmark \\d+$")) {
                int index = Integer.parseInt(input.substring(7));
                try{
                    todoList.get(index-1).unmarkAsDone();
                }catch(IndexOutOfBoundsException e){
                    print("☹ OOPS!!! The task number is not in the list.");
                    continue;
                }
                writeToFile(todoList);
                print(UNMARK+"\n  "+todoList.get(index-1));
            }else if(input.split(" ")[0].equals("todo")){
                Todo todo;
                try{
                    todo = new Todo(input.substring(5, input.length()));
                }catch(DukeException e){
                    continue;
                }catch(StringIndexOutOfBoundsException e){
                    print("☹ OOPS!!! The description of a todo cannot be empty.");
                    continue;
                }
                todoList.add(todo);
                writeToFile(todoList);
                print("Got it. I've added this task:\n  "+todo+"\nNow you have " + todoList.size() + " tasks in the list.");
            }else if(input.split(" ")[0].equals("deadline")){
                Deadline deadline;
                try {
                    deadline = new Deadline(input.split(" /")[0].substring(9), input.split(" /")[1]);
                }catch (DukeException e){
                    continue;
                }catch (ArrayIndexOutOfBoundsException e){
                    print("☹ OOPS!!! The description of a deadline should use / mark time.");
                    continue;
                }catch(StringIndexOutOfBoundsException e){
                    print("☹ OOPS!!! The description of a deadline cannot be empty.");
                    continue;
                }
                todoList.add(deadline);
                writeToFile(todoList);
                print("Got it. I've added this task:\n  "+deadline+"\nNow you have " + todoList.size() + " tasks in the list.");
            }else if(input.split(" ")[0].equals("event")) {
                Event event;
                try {
                    event = new Event(input.split(" /")[0].substring(6), input.split(" /")[1], input.split(" /")[2]);
                }catch(DukeException e) {
                    continue;
                }catch(ArrayIndexOutOfBoundsException e) {
                    print("☹ OOPS!!! The description of a event should use / mark start time and end time.");
                    continue;
                }catch(StringIndexOutOfBoundsException e){
                    print("☹ OOPS!!! The description of a event cannot be empty.");
                    continue;
                }
                writeToFile(todoList);
                todoList.add(event);
                print("Got it. I've added this task:\n  " + event + "\nNow you have " + todoList.size() + " tasks in the list.");
            }else if(input.split(" ")[0].equals("delete")){
                int index = Integer.parseInt(input.substring(7));
                try{
                    Task task = todoList.get(index-1);
                    todoList.remove(index-1);
                    writeToFile(todoList);
                    print("Noted. I've removed this task:\n  "+task+"\nNow you have " + todoList.size() + " tasks in the list.");
                }catch(IndexOutOfBoundsException e){
                    print("☹ OOPS!!! The task number is not in the list.");
                }
            }else{
                print("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        scanner.close();
        System.out.println(EXIT);
        System.out.println(LINE);
    }
}
