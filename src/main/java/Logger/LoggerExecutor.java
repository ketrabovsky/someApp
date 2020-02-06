package Logger;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LoggerExecutor implements Runnable {
    private List<ILoggerHandler> handlers;
    private BlockingQueue<String> msgQueue;
    private boolean running;
    private Lock lock;
    private Condition resourceArrivedCondition;

    public LoggerExecutor() {
        this.handlers = new ArrayList<>();
        this.msgQueue = new LinkedBlockingQueue<>();
        this.lock = new ReentrantLock();
        this.resourceArrivedCondition = lock.newCondition();
    }

    public void addHandler(ILoggerHandler handler) {
        handlers.add(handler);
    }

    public void addHandler(Collection<ILoggerHandler> handlers) {
        this.handlers.addAll(handlers);
    }

    public void logMsg(String msg) {
        lock.lock();
        try {
            msgQueue.add(msg);
            resourceArrivedCondition.signal();
        } finally {
            lock.unlock();
        }
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
            lock.lock();
            try {
                this.resourceArrivedCondition.await(10, TimeUnit.MILLISECONDS);
                String msg = msgQueue.remove();
                passToHandlers(msg);
            } catch (NoSuchElementException | InterruptedException e) {
                // nothing to do here
            } finally {
                lock.unlock();
            }

        }
    }

    private void passToHandlers(String msg) {
        for (ILoggerHandler handler : handlers) {
            handler.log(msg);
        }
    }
}
