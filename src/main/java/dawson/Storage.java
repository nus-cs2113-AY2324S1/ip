package dawson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

    private String filePathString;
    private File storageFile;

    public Storage(String filePath) {
        filePathString = filePath;
        storageFile = new File(filePath);
    }

    private void createDawsonFile() throws DawsonException {
        // Create the necessary parent directories for new file
        if (!storageFile.exists()) {
            storageFile.getParentFile().mkdirs();
        }

        // Create file if it does not exist
        try {
            storageFile.createNewFile();
        } catch (IOException e) {
            throw new DawsonException("Fatal: Error creating file: " + filePathString + " Exiting Dawson");
        }
    }

    public void save(TaskList taskList) throws DawsonException {
        createDawsonFile();

        try {
            FileWriter fileWriter = new FileWriter(storageFile);
            String taskListString = taskList.encodeTaskList();
            fileWriter.write(taskListString);
            fileWriter.close();
            
        } catch (IOException e) {
            throw new DawsonException("Unable to write to path: " + storageFile.getAbsolutePath());
        }
    }
}
