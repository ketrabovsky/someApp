package Logger;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class LoggerExecutor implements Runnable {
    private List<ILoggerHandler> handlers;
    private Queue<String> msgQueue;
    private boolean running;

    public LoggerExecutor() {
        this.handlers = new ArrayList<>();
        this.msgQueue = new LinkedBlockingQueue<>();
    }

    public void addHandler(ILoggerHandler handler) {
        handlers.add(handler);
    }

    public void addHandler(Collection<ILoggerHandler> handlers) {
        this.handlers.addAll(handlers);
    }

    public void logMsg(String msg) {
        msgQueue.add(msg);
    }

    public void start() {
        running = true;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            try {
                String msg = msgQueue.remove();
                passToHandlers(msg);
            } catch (NoSuchElementException e) {
                // Handle exception
            }

        }
    }

    private void passToHandlers(String msg) {
        for (ILoggerHandler handler : handlers) {
            handler.log(msg);
        }
    }
}
