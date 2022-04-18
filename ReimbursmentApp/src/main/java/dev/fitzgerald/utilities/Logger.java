package dev.fitzgerald.utilities;

import java.util.Date;

public class Logger {
    public static void log(String message, LogLevel level){

        String logMessage = level.name() + " " + message + " " + new Date() + "\n";
    }
}
