public class TaskList {
    private static Task[] allTasks = {}; //array of inputs

    public static void addToTaskList(String input){
        Task[] newTaskList = new Task[allTasks.length+1];
        System.arraycopy(allTasks, 0, newTaskList, 0, allTasks.length);
        newTaskList[allTasks.length] = new Task(input);
        allTasks = newTaskList;
    }

    public static void printTaskList(){
        for(int i = 0; i<allTasks.length; i++) {
            if (allTasks[i].taskDone()) {
                System.out.printf("    %d: [X] %s%n", i, allTasks[i].taskInput());
            } else {
                System.out.printf("    %d: [] %s%n", i, allTasks[i].taskInput());
            }
        }
    }
    public static void markTaskAsDone(int index){
        if(index>allTasks.length-1){
            System.out.println("Ohnuuu! Please enter valid task number *sobs*");
        }
        else{
            allTasks[index].markAsDone();
        }

    }
    public static void markTaskAsNotDone(int index){
        if(index>allTasks.length-1){
            System.out.println("Ohnuuu! Please enter valid task number *sobs*");
        }
        else{
            allTasks[index].markAsNotDone();
        }
    }
    public static String viewTaskByIndex(int index){
        if(index>allTasks.length-1){
            System.out.println("Ohnuuu! Please enter valid task number *sobs*");
            return "**Task Not Found**";
        }
        else{
            return allTasks[index].taskInput();
        }
    }
}
