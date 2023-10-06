package command;

import duke.Duke;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword){
        super(false);
        this.keyword = keyword;
    }

    private String findResult() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Duke.taskCount; i++) {
            if(Duke.list[i].getDescription().contains(this.keyword)) {
                String taskAppend = (i +1) + ". " +  Duke.list[i].toString();
                sb.append(taskAppend).append("\n");
            }
        }
        return sb.toString();
    }


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
