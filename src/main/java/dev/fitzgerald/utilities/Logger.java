package dev.fitzgerald.utilities;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

public class Logger {


    /**
     * Method to log an adverse event in the app
     * @param message the information about the event
     * @param level the severity of the event
     * */
    public static void log(String message, LoggerList level){
        // LOG LEVEL + message + TimeStamp
        String logMessage = level.name() +" " +  message + " " + new Date() + "\n";

        try {   //Try to add to the applogs
            Files.write(Paths.get("C:\\Users\\Owner\\Downloads\\Mary_Fitzgerald_p1-main\\Mary_Fitzgerald_p1-main\\ReimbursmentApp\\applogs.log"),
                    logMessage.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}