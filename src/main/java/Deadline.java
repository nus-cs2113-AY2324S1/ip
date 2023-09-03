public class Deadline  extends Task{

    public Deadline(String newTask,String date) {
        super(newTask);
        dueDate= date;
        taskType = new String[1];
        taskType[0] = "D";
    }
}
