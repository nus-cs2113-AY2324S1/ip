import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
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
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> todoList = new ArrayList<>();
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
                    todoList.get(index-1).unmarkAsDone();
                }catch(IndexOutOfBoundsException e){
                    print("☹ OOPS!!! The task number is not in the list.");
                    continue;
                }
                print(MARK+"\n  "+todoList.get(index-1));
            }else if(input.matches("^unmark \\d+$")) {
                int index = Integer.parseInt(input.substring(7));
                try{
                    todoList.get(index-1).unmarkAsDone();
                }catch(IndexOutOfBoundsException e){
                    print("☹ OOPS!!! The task number is not in the list.");
                    continue;
                }
                print(UNMARK+"\n  "+todoList.get(index-1));
            }else if(input.split(" ")[0].equals("todo")){
                Todo todo;
                try{
                    todo = new Todo(input.substring(4, input.length()));
                }catch(DukeException e){
                    continue;
                }
                todoList.add(todo);
                print("Got it. I've added this task:\n  "+todo+"\nNow you have " + todoList.size() + " tasks in the list.");
            }else if(input.split(" ")[0].equals("deadline")){
                Deadline deadline;
                try {
                    deadline = new Deadline(input.split("/")[0].substring(9, input.split("/")[0].length() - 1), input.split("/")[1].replace("by ", ""));
                }catch (DukeException e){
                    continue;
                }
                todoList.add(deadline);
                print("Got it. I've added this task:\n  "+deadline+"\nNow you have " + todoList.size() + " tasks in the list.");
            }else if(input.split(" ")[0].equals("event")){
                Event event;
                try{
                    event = new Event(input.split("/")[0].substring(5, input.split("/")[0].length()-1), input.split("/")[1], input.split("/")[2]);
                }catch(DukeException e){
                    continue;
                }catch(StringIndexOutOfBoundsException e){
                    print("☹ OOPS!!! The description of a event should use \\ mark start time and end time.");
                    continue;
                }
                todoList.add(event);
                print("Got it. I've added this task:\n  "+event+"\nNow you have " + todoList.size() + " tasks in the list.");
            }else{
                print("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        scanner.close();
        System.out.println(EXIT);
        System.out.println(LINE);
    }
}
