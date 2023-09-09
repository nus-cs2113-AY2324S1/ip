import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class TaskList {
    private static Task[] allTasks = {}; //array of inputs

    public static void addToTaskList(String input, Task.TaskType taskType){
        Task[] newTaskList = new Task[allTasks.length+1];
        System.arraycopy(allTasks, 0, newTaskList, 0, allTasks.length);
        switch (taskType){
            case TODO:
                String toDoDescription = input.split("todo")[1].strip();
                newTaskList[allTasks.length] = new ToDo(toDoDescription);
                break;
            case DEADLINE:
                String[] deadlineDetails = input.split("deadline")[1].strip().split("/");
                String deadlineDescription = deadlineDetails[0].strip();
                String deadlineTiming = deadlineDetails[1].strip().split("by")[1].strip();;
                newTaskList[allTasks.length] = new Deadline(deadlineDescription, deadlineTiming);
                break;
            case EVENT:
                String[] eventDetails = input.split("event")[1].strip().split("/");
                String eventDescription = eventDetails[0].strip();
                String eventStartTiming = eventDetails[1].strip().split("from")[1].strip();
                String eventEndTiming = eventDetails[2].strip().split("to")[1].strip();;
                newTaskList[allTasks.length] = new Event(eventDescription, eventStartTiming, eventEndTiming);
                break;
        }
        allTasks = newTaskList;
    }

    public static void printTaskList(){
        for(int i = 0; i<allTasks.length; i++) {
            switch(allTasks[i].getTaskType()) {
                case TODO:
                    if (allTasks[i].taskIsDone()) {
                        System.out.printf("    %d: [T][X] %s\n", i+1, allTasks[i].getTaskDescription());
                    } else {
                        System.out.printf("    %d: [T][] %s\n", i+1, allTasks[i].getTaskDescription());
                    }
                    break;
                case DEADLINE:
                    if (allTasks[i].taskIsDone()) {
                        System.out.printf("    %d: [D][X] %s %s\n", i+1, allTasks[i].getTaskDescription(), allTasks[i].getTaskTiming());
                    } else {
                        System.out.printf("    %d: [D][] %s %s\n", i+1, allTasks[i].getTaskDescription(), allTasks[i].getTaskTiming());
                    }
                    break;
                case EVENT:
                    if (allTasks[i].taskIsDone()) {
                        System.out.printf("    %d: [E][X] %s %s\n", i+1, allTasks[i].getTaskDescription(), allTasks[i].getTaskTiming());
                    } else {
                        System.out.printf("    %d: [E][] %s %s\n", i+1, allTasks[i].getTaskDescription(), allTasks[i].getTaskTiming());
                    }
                    break;
            }
        }
    }
    public static void markTaskAsDone(int index){
        if(index>allTasks.length){
            System.out.println("Ohnuuu! Please enter valid task number *sobs*");
        }
        else{
            allTasks[index-1].markAsDone();
        }

    }
    public static void markTaskAsNotDone(int index){
        if(index>allTasks.length){
            System.out.println("Ohnuuu! Please enter valid task number *sobs*");
        }
        else{
            allTasks[index-1].markAsNotDone();
        }
    }
    public static String viewTaskByIndex(int index){
        if(index>allTasks.length){
            return "**Task Not Found**";
        }
        else{
            switch(allTasks[index-1].getTaskType()) {
                case TODO:
                    return allTasks[index-1].getTaskDescription();
                case DEADLINE:
                case EVENT:
                    return allTasks[index-1].getTaskDescription() + " " + allTasks[index-1].getTaskTiming();
                default:
                    return "**Task Not Found**";
            }
        }
    }
    public static int getTaskListSize(){
        return allTasks.length;
    }
}