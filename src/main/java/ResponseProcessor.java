import java.util.ArrayList;
import java.util.List;
public class ResponseProcessor {
    List<Task> taskList = new ArrayList<>();
    public void process(String response) {
        if ("list".equalsIgnoreCase(response)) {
            int num = 0;
            for (Task task : taskList) {
                num += 1;
                System.out.println(num + ". " + task.getStatus());
            }
        } else if (response.toLowerCase().startsWith("mark".toLowerCase())) {
            String value = response.split(" ")[1];
            if (isInt(value)) {
                int number = Integer.parseInt(value) - 1;
                if ((0 <= number) && (number < taskList.size())) {
                    taskList.get(number).setCompleted(true);
                    System.out.println("Nicu! I have marked this as done master!");
                    System.out.println(taskList.get(number).getStatus());
                } else {
                    System.out.println("That is not a valid number masta!");
                }
            } else {
                System.out.println("Please put in a number masta!");
            }
        } else if (response.toLowerCase().startsWith("unmark".toLowerCase())) {
            String value = response.split(" ")[1];
            if (isInt(value)) {
                int number = Integer.parseInt(value) - 1;
                if ((0 <= number) && (number < taskList.size())) {
                    taskList.get(number).setCompleted(false);
                    System.out.println("Okay master! I have marked this task as not done!");
                    System.out.println(taskList.get(number).getStatus());
                } else {
                    System.out.println("That is not a valid number masta!");
                }
            } else {
                System.out.println("Please put in a number masta!");
            }
        } else {
            taskList.add(new Task(response, false));
            System.out.println("added: " + response);
        }
    }

    public Boolean isInt(String value){
        try {
            int num = Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
