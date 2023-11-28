package ui;

import model.Event;
import model.EventLog;
import model.exception.LogException;

/**
 * Represents a log printer for printing the log.
 */
public class LogPrinter {

    // EFFECTS: creates log printer object
    public LogPrinter() {
    }

    // EFFECTS: prints out log consisting of el
    //          throws exception if unable to log events
    public void printLog(EventLog el) throws LogException {
        try {
            for (Event next : el) {
                System.out.println(next.toString());
            }
        } catch (Exception e) {
            throw new LogException("Cannot log events");
        }
    }


}
