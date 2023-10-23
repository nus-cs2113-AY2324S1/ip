package command;

import Oriento.Oriento;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword){
        super(false);
        this.keyword = keyword;
    }

    /**
     * the find method implement by comparing all the task descriptions with the keyword
     * @return string containing all lines of results found
     */
    private String findResult() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Oriento.taskCount; i++) {
            if(Oriento.list[i].getDescription().contains(this.keyword)) {
                String taskAppend = (i +1) + ". " +  Oriento.list[i].toString();
                sb.append(taskAppend).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * The find execution works by simple implementation of
     * checking if the description of a task contains the target
     */
    @Override
    public void executeCommand() {
        if(! this.findResult().isEmpty()){
            System.out.println("Great! I have find some similar result for you.");
            System.out.println(this.findResult());
        } else {
            System.out.println("I am sorry but I cannot find any result for you...");
        }

    }

}
