import java.util.ArrayList;
import java.util.List;

public class taskProcessor {
    List<Task> taskList = new ArrayList<>();

    public void listProcessor(String response){
        int num = 0;
        for (Task task : taskList) {
            num += 1;
            System.out.println(num + ". " + task.getStatus());
        }
    }
    public void markProcessor(int number){
        taskList.get(number).setCompleted(true);
        System.out.println("Nicu! I have marked this as done master!");
        System.out.println(taskList.get(number).getStatus());
    }

    public void unmarkProcessor(int number){
        taskList.get(number).setCompleted(false);
        System.out.println("Okay master! I have marked this task as not done!");
        System.out.println(taskList.get(number).getStatus());
    }
    public void todoProcessor(String response) {
        taskList.add(new Todo(response));
        System.out.println("added: " + taskList.get(taskList.size() - 1).getStatus());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
    public void eventProcessor(String response){
        int indexFrom = response.indexOf("/from");
        int indexTo = response.indexOf("/to");
        if (isValidEvent(indexFrom, indexTo)) {
            String description = response.substring(0, indexFrom);
            String from = response.substring(indexFrom + 5, indexTo);
            String to = response.substring(indexTo + 3);
            if (!from.isEmpty() && !to.isEmpty() && !description.isEmpty()) {
                taskList.add(new Event(description, from, to));
                System.out.println("added: " + taskList.get(taskList.size() - 1).getStatus());
                System.out.println("Now you have " + taskList.size() + " tasks in the list masta");
            } else {
                System.out.println("Some of your inputs are empty uwu!");
            }
        }
        else {
            System.out.println("Insufficient commands for deadline masta!");
        }
    }

    public void deadlineProcessor(String response){
        int indexBy = response.indexOf("/by");
        if (isValidDeadline(indexBy)) {
            String description = response.substring(0, indexBy);
            String by = response.substring(indexBy + 3);
            if (!by.isEmpty() && !description.isEmpty()) {
                taskList.add(new Deadline(description, by));
                System.out.println("added: " + taskList.get(taskList.size() - 1).getStatus());
                System.out.println("Now you have " + taskList.size() + " tasks in the list masta");
            } else {
                System.out.println("Some of your inputs are empty uwu!");
            }
        }
        else {
            System.out.println("Insufficient commands for deadline masta!");
        }
    }

    public int getSize(){
        return taskList.size();
    }

    public boolean isValidDeadline(int indexBy){
        if(indexBy != -1){
            return true;
        }
        System.out.println("Invalid Deadline format masta!");
        return false;
    }
    public boolean isValidEvent(int indexFrom, int indexTo){
        if(indexFrom != -1 && indexTo != -1){
            return true;
        }
        System.out.println("Invalid Event format masta!");
        return false;
    }
}
