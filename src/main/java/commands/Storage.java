package commands;

import java.io.*;

public class Storage {

    private final String dukeDataFile;
    private int taskListSize;

    public Storage(String dukeDataFile,int taskListSize){
        this.dukeDataFile = dukeDataFile;
        this.taskListSize = taskListSize;
    }

    public void addToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(this.dukeDataFile,true);
        // Create a BufferedWriter for efficient writing
        BufferedWriter bufferedWriter = new BufferedWriter(fw);
        bufferedWriter.newLine();
        bufferedWriter.write(textToAdd);
        bufferedWriter.close();
        fw.close();
        this.taskListSize += 1;
    }
    public void removeFromFile(int lineToRemove){

        // Create a temporary file to write the modified content
        File tempFile = new File("temp.txt");

        try (
                BufferedReader reader = new BufferedReader(new FileReader(this.dukeDataFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        ) {
            String currentLine;
            int lineNumber = 0;

            while ((currentLine = reader.readLine()) != null) {
                lineNumber++;

                // Check if the current line is the one to remove
                if (lineNumber != lineToRemove) {
                    writer.write(currentLine);
                    //if it's the last item, we don't add a newLine to the next line
                    if(lineNumber == this.taskListSize){
                        break;
                    }
                    writer.newLine(); // Add a new line after writing
                }

            }
            this.taskListSize -= 1;
        } catch (IOException e) {
            System.err.println("Error reading/writing the file: " + e.getMessage());
        }

        // Replace the original file with the temporary file
        if (tempFile.renameTo(new File(dukeDataFile))) {
            System.out.println("Line removed successfully.");
        } else {
            System.err.println("Error renaming the temporary file.");
        }

    }

}
