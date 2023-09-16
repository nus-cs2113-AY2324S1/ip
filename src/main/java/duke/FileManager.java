package duke;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

public class FileManager {

    public FileWriter writer;
    public File file;

    public FileManager() throws IOException {
        this.writer = new FileWriter("./test.txt", true);
        this.file = new File("./test.txt");
    }

    public void read(){
        try {
            FileReader fileReader = new FileReader("test.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                // Split the line using ":" as the delimiter
                String[] parts = line.split("\\|");
                String keyword = parts[0].trim();
                String status = parts[1].trim();

                String key = keyword + " - " + status;
                System.out.println(key);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String text){
        try {
            this.writer.write(text);
            this.writer.flush();
        }
        catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }

    public void closeFile() throws IOException {
        writer.close();
    }
}