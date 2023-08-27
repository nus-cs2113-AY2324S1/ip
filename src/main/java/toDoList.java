import java.util.Arrays;

public class toDoList {
    private Task[] taskList;
    private String[] markedTask;
    private int taskListTracker;
    public String checker;
    public String marker;
    public String unmarked;

public toDoList(){
    taskList = new Task[100];
    markedTask = new String[100];
    taskListTracker = 0;
    checker = "list";
    marker = "mark";
    unmarked = "unmark";
}
public void addTask(String incomingTask) {
    Task newTask = new Task(incomingTask);
    taskList[taskListTracker] = newTask;
    taskListTracker += 1;
    System.out.println("added: " + newTask.toBeDone);

}

public void listTask(){
    int count = 0;
    for(int i = 0; i < taskList.length; i +=1) {
        if (taskList[i] == null) {
            count += 1;
        }
    }
    Task[] answer = (Arrays.copyOf(taskList,taskList.length - count));

    System.out.println("Here are the tasks in your list");
    for(int j = 0; j < answer.length; j += 1){

        System.out.println( j + 1 + "." + Arrays.toString(answer[j].markAsDone) + " " + answer[j].toBeDone);

    }
}


    public void mark(String input){
    if(input.startsWith("mark")) {
        int dividerPosition = input.indexOf(" ");
        String taskNumberString = input.substring(dividerPosition + 1);
        int taskNumber = Integer.parseInt(taskNumberString);
        Task taskToBeMarked = taskList[taskNumber - 1];
        taskToBeMarked.setDone();
        System.out.println("Task Completed!");
        System.out.println(Arrays.toString(taskToBeMarked.markAsDone) + " " + taskToBeMarked.toBeDone);

    }

    }
    public void unmark(String input){
        if(input.startsWith("unmark")){
            int dividerPosition = input.indexOf(" ");
            String taskNumberString = input.substring(dividerPosition + 1);
            int taskNumber = Integer.parseInt(taskNumberString);
            Task taskToBeUnmarked = taskList[taskNumber-1];
            taskToBeUnmarked.setNotDone();
            System.out.println("Task marked as uncompleted!");
            System.out.println(Arrays.toString(taskToBeUnmarked.markAsDone)+ " " + taskToBeUnmarked.toBeDone);


        }
    }
}
