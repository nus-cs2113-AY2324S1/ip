public class Task {
    protected boolean isMarked;
    protected String name;
    private static int count = 0;

    public Task (String name){
        this.name = name;
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

    public String getTaskName(){
        return name;
    }

    public static int getTaskCount(){
        return count;
    }

}
