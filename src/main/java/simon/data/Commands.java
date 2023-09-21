package simon.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.nio.file.*;


public class Commands {
    public static void addTextToFile(String filePath, String textToAdd) {
        try {
            // Create a FileWriter object with the specified file path in append mode (true).
            FileWriter fileWriter = new FileWriter(filePath, true);

            // Create a BufferedWriter to efficiently write text.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Write the text to the file.
            bufferedWriter.write(textToAdd);

            // Write a newline character to separate lines.
            bufferedWriter.newLine();

            // Close the BufferedWriter to release resources.
            bufferedWriter.close();

            //System.out.println("Text added to the file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void editTextFile(String filePath, String newText, int lineNumber) {
        try {
            // Read the existing content of the file into memory.
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            StringBuilder fileContent = new StringBuilder();
            String line;

            int currentLine = 1;

            while ((line = bufferedReader.readLine()) != null) {
                if (currentLine == lineNumber) {
                    // Replace the line if the line number matches.
                    fileContent.append(newText).append("\n");
                } else {
                    fileContent.append(line).append("\n");
                }
                currentLine++;
            }

            // Close the BufferedReader to release resources.
            bufferedReader.close();

            // Write the modified content back to the file.
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(fileContent.toString());

            // Close the BufferedWriter to release resources.
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static boolean isFileExist(String filePath) {
        Path path = Paths.get(filePath);
        return Files.exists(path);
    }
}