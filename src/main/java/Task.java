
public class Task {

    private String description;
    private boolean isDone;
    private char type;
    private String time;

    public Task(String description, char type) {
        this.description = description;
        this.type = type;
        isDone = false;
        time = "";
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return (this.isDone ? "X" : " ");
    }

    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }

    public char getType() {
        return this.type;
    }

    public void setType(char type) {
        String s = "TDE";
        if(!s.contains(String.valueOf(type))) throw new IllegalArgumentException("Illegal value: " + type);
        this.type = type;
    }

    public void setDeadlineTime(String deadline) {
        time = "(by: " + deadline + ")";
    }

    public void setEventTime(String start, String end) {
        time = "(from: " + start + " to: " + end + ")";
    }

    @Override
    public String toString() {
        String result = "[" + type + "][" + getStatus() + "] " + description + " " + time;
        return result.replaceAll("\\s+$", "");
    }
    
}   