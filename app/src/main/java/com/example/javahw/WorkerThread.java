package com.example.javahw;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class WorkerThread extends Thread {

    private final BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();
    private volatile boolean isRunning = true;

    /**
     * Метод для добавления задачи в очередь выполнения
     * @param task задача для выполнения
     * */
    public void execute(Runnable task) {
        if (isRunning) {
            taskQueue.offer(task);
        } else {
            throw new IllegalStateException("WorkerThread is stopped");
        }
    }

    /**
     * Метод для остановки выполнения текущих задач
     * */
    public void shutdown() {
        isRunning = false;
        this.interrupt();
    }

    @Override
    public void run() {
        while (isRunning || !taskQueue.isEmpty()) {
            try {
                Runnable task = taskQueue.take();
                task.run();
            } catch (InterruptedException e) {
                if (!isRunning && taskQueue.isEmpty()) {
                    break;
                }
            }
        }
    }

}
