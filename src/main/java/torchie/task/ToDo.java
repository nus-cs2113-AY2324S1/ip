package torchie.task;

public class ToDo extends Task {
    public ToDo(String description){
        super(description);
    }

    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }

    @Override
    public void announceTaskAdd(){
        super.announceTaskAdd();
        printTask(this.toString());
    }


    @Override
    public String toFileFormat() {
        return ("T | " + super.toFileFormat());
    }
}
