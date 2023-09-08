public class ToDos extends Task {
    public ToDos(String description){
        super(description);
    }

    @Override
    public void printTask() {
        System.out.print("[T]");
        super.printTask();
    }

    @Override
    public void announceTaskAdd(){
        super.announceTaskAdd();
        printTask();
    }
}
