import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class Duke {
    protected final static String line = "_______________________________________________";
    private static void print(String message){
        System.out.println(line);
        System.out.println(message);
        System.out.println(line);
    }
    public static void main(String[] args) {
        String greet = "Hello! I'm Elwin\n" + "What can I do for you?";
        //String line = "_______________________________________________";
        String exit = "Bye. Hope to see you again soon!";
        String listRequirements = "Here are the tasks in your list:";
        String markRequirements = "Nice! I've marked this task as done:";
        String unmarkRequirements = "OK, I've marked this task as not done yet:";
        String markFormat = "^mark \\d+$";
        String unmarkFormat = "^unmark \\d+$";
        System.out.println(line);
        System.out.println(greet);
        System.out.println(line);
        Scanner scanner = new Scanner(System.in);
        List<Task> todoList = new ArrayList<>();
        while(true){
            String input = scanner.nextLine();
            if(input.equals("bye")){
                break;
            }else if(input.equals("list")){
                System.out.println(line);
                System.out.println(listRequirements);
                for(int i = 0; i < todoList.size(); i++){
                    System.out.println(i+1 + "." + todoList.get(i));
                }
                System.out.println(line);
            }else if(input.matches(markFormat)) {
                int index = Integer.parseInt(input.substring(5));
                try{
                    todoList.get(index-1).unmarkAsDone();
                }catch(IndexOutOfBoundsException e){
                    print("☹ OOPS!!! The task number is not in the list.");
                    continue;
                }
                print(markRequirements+"\n  "+todoList.get(index-1));
            }else if(input.matches(unmarkFormat)) {
                int index = Integer.parseInt(input.substring(7));
                try{
                    todoList.get(index-1).unmarkAsDone();
                }catch(IndexOutOfBoundsException e){
                    print("☹ OOPS!!! The task number is not in the list.");
                    continue;
                }
                print(unmarkRequirements+"\n  "+todoList.get(index-1));
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
        System.out.println(exit);
        System.out.println(line);
    }
}
