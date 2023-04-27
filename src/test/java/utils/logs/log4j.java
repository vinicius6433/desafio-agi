package utils.logs;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class log4j {
    private static final Logger logger = Logger.getLogger(log4j.class);

    public static void main(String[] args) {
        PatternLayout layout = new PatternLayout("\u001B[32m%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n\u001B[0m");
        ConsoleAppender appender = new ConsoleAppender(layout);
        logger.addAppender(appender);

        logger.debug("debug message");
        logger.info("info message");
        logger.warn("warn message");
        logger.error("error message");
        logger.fatal("fatal message");
    }
}