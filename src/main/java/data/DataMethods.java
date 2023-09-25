package data;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

import static UI.Printer.line;


public class DataMethods {
    public static void createSimonTxt(String userDirectory) {
        String dataDirectory = userDirectory + "/" + "data";
        createDirectory(dataDirectory);
        createFileInDirectory(dataDirectory, "simon.txt");
    }
    public static void createDirectory(String folderPath) {

        File folder = new File(folderPath);
        if (folder.mkdir()) {
            System.out.println("\tFolder created successfully.");
        } else {
            System.out.println("\tFolder already exists");
        }
    }

    public static void createFileInDirectory(String directoryPath, String fileName) {

        // Create the full path to the text file
        String filePath = directoryPath + "/" + fileName;

        File file = new File(filePath);

        try {
            // Create the file
            if (file.createNewFile()) {
                System.out.println("\tText file created successfully at: " + filePath);
            } else {
                System.out.println("\tFile already exists.");
            }
        } catch (IOException e) {
            System.out.println("\tAn IOException occurred: " + e.getMessage());
        }
    }


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
            System.out.println("An IOException occurred: " + e.getMessage());
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
            System.out.println("An IOException occurred: " + e.getMessage());
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
            System.out.println("An IOException occurred: " + e.getMessage());
        }
    }

}