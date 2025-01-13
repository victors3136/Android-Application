package ubb.server.service;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.PrintStream;
import java.time.Instant;

public class LoggingService {
    private final PrintStream out;

    public enum Level {
        Info, Warn, Error;

        public String toString() {
            return switch (this) {
                case Info -> "INFO";
                case Warn -> "WARN";
                case Error -> "ERROR";
            };
        }
    }

    private synchronized void log(String msg, Level level) {
        out.println(Instant.now().toString() + " " + level.toString() + " : " + msg);
    }

    public void inform(String msg) {
        log(msg, Level.Info);
    }

    public void warn(String msg) {
        log(msg, Level.Warn);
    }

    public void error(String msg) {
        log(msg, Level.Error);
    }

    @Autowired
    public LoggingService(PrintStream out) {
        this.out = out;
    }
}

