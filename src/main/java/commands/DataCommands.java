package commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;


public class DataCommands {
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

    public static void deleteLineFromFile(String filePath, int lineToDelete) {
        try {
            // Read the existing content of the file into memory.
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            List<String> fileContent = new ArrayList<>();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                fileContent.add(line);
            }

            // Close the BufferedReader to release resources.
            bufferedReader.close();

            // Remove the specified line from the list.
            fileContent.remove(lineToDelete);

            // Write the modified content back to the file.
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (String contentLine : fileContent) {
                bufferedWriter.write(contentLine);
                bufferedWriter.newLine(); // Add a newline character after each line.
            }

            // Close the BufferedWriter to release resources.
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}