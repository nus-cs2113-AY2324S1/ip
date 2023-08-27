import java.util.Arrays;

public class toDoList {
    private String[] taskList;
    private int taskListTracker;
    public String checker;

public toDoList(){
    taskList = new String[100];
    taskListTracker = 0;
    checker = "list";
}
public void addTask(String task) {
    taskList[taskListTracker] = task;
    taskListTracker += 1;
    System.out.println("added: " + task);
}

public void listTask(){
    int count = 0;
    for(int i = 0; i < taskList.length; i +=1) {
        if (taskList[i] == null) {
            count += 1;
        }
    }
    String[] answer = (Arrays.copyOf(taskList,taskList.length - count));

    for(int j = 0; j < answer.length; j += 1){
        System.out.println( j + 1 + ". " + answer[j]);

}

    }
}
