package Logger.Handlers;

import Logger.ILoggerHandler;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileHandler implements ILoggerHandler {
    private final String filename;
    private FileOutputStream logFile = null;

    public FileHandler(String filename) {
        this.filename = filename;
        try {
            logFile = new FileOutputStream(this.filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getFilename() {
        return filename;
    }

    @Override
    public void log(String msg) {
        try {
            logFile.write(msg.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
