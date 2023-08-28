import java.util.ArrayList;
import java.util.List;
public class ResponseProcessor {
    List<Task> taskList = new ArrayList<>();
    public void process(String response) {
        if("list".equalsIgnoreCase(response)){
            int num = 0;
            for (Task task :taskList){
                num += 1;
                System.out.println(num+". "+task.getDescription());
            }
        }
        else{
            taskList.add(new Task(response, false));
            System.out.println("added: "+ response);
        }
    }
}
