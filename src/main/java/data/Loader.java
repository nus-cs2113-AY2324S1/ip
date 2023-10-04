package data;

import task.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class that contains method to read simon.txt into an ArrayList
 */
public class Loader {
    /**
     * Takes in the location of simon.txt and loads the contents of the
     * file to an ArrayList
     *
     * @param filePath location of simon.txt
     * @return ArrayList containing tasks stored in simon.txt
     */
    public static ArrayList<Task> readToList (String filePath) {
        ArrayList<Task> newList = new ArrayList<>();

        try {
            // Create a FileReader and BufferedReader to read the file.
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;

            // Read lines from the file and add them to the ArrayList.
            while ((line = bufferedReader.readLine()) != null) {
                String[] splitInputs = line.split(" \\| ");
                switch(splitInputs[0]) {

                case "T":
                    newList.add(new Todo(splitInputs[2]));
                    if (splitInputs[1].equals("X")) {
                        newList.get(Task.getNumberOfTask() - 1).markAsDone();
                    }
                    break;
                case "D":
                    newList.add(new Deadline(splitInputs[2], splitInputs[3]));
                    if (splitInputs[1].equals("X")) {
                        newList.get(Task.getNumberOfTask() - 1).markAsDone();
                    }
                    break;
                case "E":
                    String[] timings = splitInputs[3].split(" - ");
                    newList.add(new Event(splitInputs[2], timings[0], timings[1]));
                    if (splitInputs[1].equals("X")) {
                        newList.get(Task.getNumberOfTask() - 1).markAsDone();
                    }
                    break;
                default:

                }
            }

            // Close the BufferedReader to release resources.
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("An IOException occurred: " + e.getMessage());
        }

        return newList;
    }
}
