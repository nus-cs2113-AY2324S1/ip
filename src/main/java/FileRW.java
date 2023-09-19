import java.util.Scanner;  // Import the Scanner class
import java.io.File; // Import the File class
import java.io.FileWriter; // Import the FileWriter class
import java.io.BufferedWriter; // Import the BufferedWriter class
import java.io.IOException; // Import the IOException class to handle errors

public class FileRW {
   
    //method that takes in input tasks and taskCount and reads the file and writes the tasks into the array
    public static void readFromFile(Task[] tasks, int taskCount)  {
        //check if there is a file to read from
        try{
            File file = new File("data/duke.txt");
            Scanner fileScan = new Scanner(file);
            while(fileScan.hasNextLine()){
                String line = fileScan.nextLine();
                String[] lineSplit = line.split(" \\| ");
                if(lineSplit[0].equals("T")){
                    tasks[taskCount] = new Todo(lineSplit[2]);
                    if(lineSplit.length > 3){
                        throw new DukeException("Your data file is corrupted");
                    }
                    if(lineSplit[1].equals("true")){
                        tasks[taskCount].markAsDone();
                    }
                    taskCount++;
                }else if(lineSplit[0].equals("D")){
                    if(lineSplit.length > 4){
                        throw new DukeException("Your data file is corrupted");
                    }
                    tasks[taskCount] = new Deadline(lineSplit[2], lineSplit[3]);
                    if(lineSplit[1].equals("true")){
                        tasks[taskCount].markAsDone();
                    }
                    taskCount++;
                }else if(lineSplit[0].equals("E")){
                    if(lineSplit.length > 4){
                        throw new DukeException("Your data file is corrupted");
                    }
                    tasks[taskCount] = new Event(lineSplit[2], lineSplit[3]);
                    if(lineSplit[1].equals("true")){
                        tasks[taskCount].markAsDone();
                    }
                    taskCount++;
                }
            }
            //print the tasks in the file
            
            fileScan.close();
        //otherwise create a new file
        } catch(Exception e){
            //Create a new file and directory
            try{
                File file = new File("data/duke.txt");
                file.getParentFile().mkdirs();
                file.createNewFile();
            }catch(Exception e1){
                e1.printStackTrace();
            }
        }
        
    }

    //method that takes in input tasks and taskCount and writes the tasks into the file
    public static void writeToFile(Task[] tasks, int taskCount)  {
        //Save the tasks to the file
        try{
            //Create a file writer
            FileWriter fw = new FileWriter("data/duke.txt");
            //Erase the file
            fw.write("");
            //Create a buffered writer
            BufferedWriter bw = new BufferedWriter(fw);
            //Write the tasks to the file
            for(int i=0;i<taskCount;i++){
                //if the task is a todo
                if(tasks[i] instanceof Todo){
                    //write the task to the file
                    bw.write("T | " + tasks[i].isDone + " | " + tasks[i].taskName);
                    bw.newLine();
                }
                //if the task is a deadline
                else if(tasks[i] instanceof Deadline){
                    //write the task to the file
                    bw.write("D | " + tasks[i].isDone + " | " + tasks[i].taskName + " | " + ((Deadline) tasks[i]).deadline);
                    bw.newLine();
                }
                //if the task is an event
                else if(tasks[i] instanceof Event){
                    //write the task to the file
                    bw.write("E | " + tasks[i].isDone + " | " + tasks[i].taskName + " | " + ((Event) tasks[i]).eventTime);
                    bw.newLine();
                }
            } 

            //Close the buffered writer
            bw.close();
    
            //Close the file writer
            fw.close();

        } catch(IOException e){
            System.out.println("Error writing to file.");
        }
        
    }

}
