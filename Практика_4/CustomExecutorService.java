package Практика_4;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

public class CustomExecutorService{
    private final int numThreads;  // Количество потоков в исполнителе
    private final List<WorkerThread> threads;  // Список для хранения рабочих потоков
    private final Queue<Runnable> taskQueue;  // Очередь для хранения задач на выполнение
    private volatile boolean isShutdown;  // Флаг указывающий, завершен ли сервис

    // Конструктор для инициализации исполнителя с заданным количеством потоков
    public CustomExecutorService(int numThreads) {
        this.numThreads = numThreads;
        this.threads = new ArrayList<>(numThreads);
        this.taskQueue = new LinkedList<>();
        this.isShutdown = false;

        // Создание и запуск рабочих потоков
        for (int i = 0; i < numThreads; i++) {
            WorkerThread thread = new WorkerThread();
            thread.start();
            threads.add(thread);
        }
    }

    // Метод для добавления задачи в исполнитель
    public void submit(Runnable task) {
        if (isShutdown) {
            throw new IllegalStateException("ExecutorService is shutdown");
        }
        synchronized (taskQueue) {
            taskQueue.offer(task);  // Добавление задачи в очередь
            taskQueue.notify();    // Уведомление одного из ожидающих потоков для выполнения задачи
        }
    }

    // Метод для завершения работы исполнителя
    public void shutdown() {
        isShutdown = true;   // Установка флага завершения в true
        // Прерывание всех рабочих потоков для завершения работы
        for (WorkerThread thread : threads) {
            thread.interrupt();
        }
    }

    // Внутренний класс, представляющий рабочий поток
    private class WorkerThread extends Thread {
        @Override
        public void run() {
            while (!isShutdown) {   // Постоянно выполнять, пока сервис не завершится
                Runnable task;
                synchronized (taskQueue) {
                    // Ожидание, пока в очереди не появится задача или сервис не будет завершен
                    while (taskQueue.isEmpty() && !isShutdown) {
                        try {
                            taskQueue.wait();   // Ожидание появления задач
                        } catch (InterruptedException e) {
                            // Прервано во время ожидания, проверяем, вызван ли shutdown
                            if (isShutdown) {
                                return;   // Если был вызван shutdown, завершаем поток
                            }
                        }
                    }
                    task = taskQueue.poll();   // Получение задачи из очереди
                }
                if (task != null) {
                    try {
                        task.run();   // Выполнение задачи
                    } catch (RuntimeException e) {
                        // Журналирование или обработка неожиданных исключений времени выполнения
                    }
                }
            }
        }
    }
}
