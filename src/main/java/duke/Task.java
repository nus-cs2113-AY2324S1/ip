package duke;

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

    public void setType(char type) throws DukeException {
        String types = "TDE";
        if(!types.contains(String.valueOf(type))) {
            throw new DukeException("Illegal value for task type:" + type);
        }
        this.type = type;
    }

    public void setDeadlineTime(String deadline) {
        time = "(by: " + deadline + ")";
    }

    public void setEventTime(String start, String end) {
        time = "(from: " + start + " to: " + end + ")";
    }

    public String serializeToString() {
        // Create a string representation of the Task object
        StringBuilder serializedTask = new StringBuilder();
    
        // Append the task type, status, and description to the string
        serializedTask.append(type); // Task type (e.g., 'T', 'D', 'E')
        serializedTask.append(" | "); // Separator
        serializedTask.append(isDone ? "1" : "0"); // 1 for done, 0 for not done
        serializedTask.append(" | "); // Separator
        serializedTask.append(description); // Task description
    
        // Append deadline or event details if available
        if (!time.isEmpty()) {
            serializedTask.append(" | "); // Separator
            serializedTask.append(time.substring(1, time.length() - 1)); // Event or deadline details
        }
    
        return serializedTask.toString();
    }

    public static Task deserializeFromString(String taskString) throws DukeException {
        try {
            // Split the taskString into its components
            String[] components = taskString.split("\\|");
    
            if (components.length < 3 || components.length > 4) {
                throw new DukeException("Invalid task format.");
            }
    
            char type = components[0].charAt(0);
            int isDone = Integer.parseInt(components[1].trim());
            String description = components[2].trim();
    
            Task task = new Task(description, type);
            task.setStatus(isDone == 1); // Convert isDone to a boolean
            task.time = (components.length == 4 ? "(" + components[3].trim() + ")" : "");
    
            return task;
        } catch (Exception e) {
            throw new DukeException("Error parsing task from string: " + e.getMessage());
        }
    }
    
    @Override
    public String toString() {
        String result = "[" + type + "][" + getStatus() + "] " + description + " " + time;
        return result.replaceAll("\\s+$", "");
    }
}   