public class Event  extends Task{

    public Event(String newTask,String start,String end) {
        super(newTask);
        taskType = new String[1];
        taskType[0] = "E";
        startTime = start;
        endTime= end;
    }
}
