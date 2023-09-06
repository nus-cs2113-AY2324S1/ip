public class Task {
    private String description;
    private boolean isCompleted;

    public Task(String description, boolean isCompleted) {
        setDescription(description);
        setCompleted(isCompleted);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public String getStatus(){
        String status = " ";
        if(this.isCompleted()){
            status = "X";
        }
        String line = "[" + status + "] " + getDescription();
        return line;
    }
}
