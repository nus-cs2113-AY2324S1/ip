package Objects;

public class toDos extends task {

    public toDos(String description) {
        super(description);
        this.isDone = false;
        this.type = 'T';
    }
    
    @Override
     public String toString() {
         return String.format(this.description);
     }
}
