package dev.fitzgerald.loggertests;

import dev.fitzgerald.utilities.Logger;
import dev.fitzgerald.utilities.LoggerList;
import org.junit.jupiter.api.Test;

public class LoggerTests {

    @Test
    void info_log_test(){
        Logger.log("Hello", LoggerList.INFO);
        Logger.log("Wassup", LoggerList.DEBUG);
    }
}
