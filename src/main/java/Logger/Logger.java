package Logger;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class Logger {
    private enum LoggerLevel {
        DEBUG,
        ERROR,
        WARNING,
        INFO,
    }

    private LoggerExecutor loggerExecutor;

    public Logger() {
        loggerExecutor = new LoggerExecutor();
        Thread executorThread = new Thread(loggerExecutor);
        loggerExecutor.start();
        executorThread.start();
        //loggerExecutor.start();
    }

    public void cleanup() {
        loggerExecutor.stop();
    }

    public void debug(String msg) {
        this.log(msg, LoggerLevel.DEBUG);
    }

    public void error(String msg) {
        this.log(msg, LoggerLevel.ERROR);
    }

    public void info(String msg) {
        this.log(msg, LoggerLevel.INFO);
    }

    public void warning(String msg) {
        this.log(msg, LoggerLevel.WARNING);
    }

    public void registerHandler(ILoggerHandler handler) {
        loggerExecutor.addHandler(handler);
    }

    public void registerHandler(List<ILoggerHandler> handlers) {
        loggerExecutor.addHandler(handlers);
    }

    private void log(String msg, LoggerLevel level) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        String className = stackTrace[stackTrace.length - 1].getClassName();
        String methodName = stackTrace[stackTrace.length - 1].getMethodName();
        int lineNumber = stackTrace[stackTrace.length - 1].getLineNumber();
        long threadId = Thread.currentThread().getId();

        String threadInfo = "[Thread ID: " + threadId + "]";
        String timestamp = getTimestamp();
        String finalLog = String.format("%s %s\t[%s]:\t\t[%s::%s:%d]\t%s\n", timestamp, threadInfo, level, className, methodName, lineNumber, msg);

        loggerExecutor.logMsg(finalLog);
    }
    private static String getTimestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
