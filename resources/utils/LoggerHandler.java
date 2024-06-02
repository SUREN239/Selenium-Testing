package utils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.PatternLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerHandler {

	 private static final Logger logger = Logger.getLogger(LoggerHandler.class);

	    public static Logger getLogger() {
	        return logger;
	    }

    public static void initLog4j() {
    	 // Set the system property for currentTimestamp
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = sdf.format(new Date());
        System.setProperty("log.datePattern", "yyyy-MM-dd-HH-mm-ss");

        // Initialize Log4j using the properties file
        PropertyConfigurator.configure("src/main/resources/log4j.properties");

        // Obtain the "RollingAppender" and configure it
        Logger log = Logger.getLogger(LoggerHandler.class);
        DailyRollingFileAppender appender = (DailyRollingFileAppender) log.getAppender("RollingAppender");

        // Define the desired log file extension format
        String logFileExtension = ".logs"; // Modify this as needed

        // Configure the appender as needed
        appender.setFile("logs/logfile_" + timestamp + logFileExtension);
        PatternLayout layout = new PatternLayout();
        layout.setConversionPattern("%p %d %c %M - %m%n");
        appender.setLayout(layout);
        appender.activateOptions(); // Activate the new options
    }

    public  void logInfo(String message) {
        logger.info(message);
    }

    public  void logDebug(String message) {
        logger.debug(message);
    }

    public  void logWarn(String message) {
        logger.warn(message);
    }

    public  void logError(String message) {
        logger.error(message);
    }

    public  void logFatal(String message) {
        logger.fatal(message);
    }

    public  void logTrace(String message) {
        logger.trace(message);
    }
}
