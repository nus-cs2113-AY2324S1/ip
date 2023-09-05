import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        String greet = "Hello! I'm Elwin\n" + "What can I do for you?";
        String line = "_______________________________________________";
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
                    System.out.println(i+1 + ".[" + todoList.get(i).getStatusIcon() + "] " + todoList.get(i).getDescription());
                }
                System.out.println(line);
            }else if(input.matches(markFormat)) {
                int index = Integer.parseInt(input.substring(5));
                todoList.get(index-1).markAsDone();
                System.out.println(line);
                System.out.println(markRequirements);
                System.out.println("  [" + todoList.get(index-1).getStatusIcon() + "] " + todoList.get(index-1).getDescription());
                System.out.println(line);
            }else if(input.matches(unmarkFormat)) {
                int index = Integer.parseInt(input.substring(7));
                todoList.get(index-1).unmarkAsDone();
                System.out.println(line);
                System.out.println(unmarkRequirements);
                System.out.println("  [" + todoList.get(index-1).getStatusIcon() + "] " + todoList.get(index-1).getDescription());
                System.out.println(line);
            }else{
                Task newTask = new Task(input);
                todoList.add(newTask);
                System.out.println(line);
                System.out.println("added: " + input);
                System.out.println(line);
            }
        }
        scanner.close();
        System.out.println(exit);
        System.out.println(line);
    }
}
