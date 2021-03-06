package se.kth.pointofsale.logs;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * Created by Josef on 2019-05-15.
 */
public class FileLogger implements PosLogger{
    private static final String LOG_FILE = "point_of_sale";
    private PrintWriter logFile;

    public FileLogger() throws IOException{
        logFile = new PrintWriter(new FileWriter(LOG_FILE+"-"+DateAndTimeUtility.getTimeStamp()+".log"),true);
    }

    @Override
    public void logErrorMessage(String msg, Exception e) {
        String logEntry = DateAndTimeUtility.getCurrentTime() + ": Exception was thrown: " + e.getMessage();
        if (e.getCause() != null){
            logEntry += "UI Message: " + msg + "\nCause: " + e.getCause().getMessage();
        }
        logFile.println(logEntry);
    }
}