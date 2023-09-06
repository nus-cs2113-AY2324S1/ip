public class Task {
    protected boolean isMarked;
    protected String description;
    private static int count = 0;

    public Task (String description){
        this.description = description;
        this.isMarked = false;
        count++;
    }

    public void markTask(){
        isMarked = true;
    }

    public void unMarkTask(){
        isMarked = false;
    }

    public boolean getTaskStatus(){
        return isMarked;
    }

    public String getTaskDescription(){
        return description;
    }

    public static int getTaskCount(){
        return count;
    }

}
