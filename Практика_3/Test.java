package Практика_3;

import java.util.concurrent.CountDownLatch;

public class Test {
    public static void main(String[] args) {
        // Создание экземпляра SemaphoreList
        SemaphoreList<Integer> list = new SemaphoreList<>();

        // Количество потоков для тестирования
        int numThreads = 10;

        // Количество операций, которые каждый поток выполнит над списком
        int numOperationsPerThread = 1000;

        // Счетчик для ожидания завершения всех потоков
        CountDownLatch latch = new CountDownLatch(numThreads);

        // Создание и запуск потоков
        for (int i = 0; i < numThreads; i++) {
            new Thread(() -> {
                try {
                    // Выполнение операций добавления и удаления элементов в списке
                    for (int j = 0; j < numOperationsPerThread; j++) {
                        list.add(j);
                        list.remove(0);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // Уменьшение счетчика после завершения работы потока
                    latch.countDown();
                }
            }).start();
        }

        try {
            latch.await(); // Ожидание завершения всех потоков
            System.out.println("Проверка завершена.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Создание экземпляра SynchronizedMap
        SynchronizedMap<Integer, String> map = new SynchronizedMap<>();

        // Счетчик для ожидания завершения всех потоков для тестирования SynchronizedMap
        CountDownLatch latch1 = new CountDownLatch(numThreads);

        // Создание и запуск потоков для тестирования SynchronizedMap
        for (int i = 0; i < numThreads; i++) {
            new Thread(() -> {
                try {
                    // Выполнение операций добавления и удаления элементов в карту
                    for (int j = 0; j < numOperationsPerThread; j++) {
                        map.put(j, String.valueOf(j));
                        map.remove(j);
                    }
                } finally {
                    // Уменьшение счетчика после завершения работы потока
                    latch1.countDown();
                }
            }).start();
        }

        try {
            latch1.await(); // Ожидание завершения всех потоков
            System.out.println("Проверка завершена.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}







    // Создаем экземпляр потокобезопасной map
//        SynchronizedMap<Integer, String> synchronizedMap = new SynchronizedMap<>();
//
//        // Добавляем элементы в map
//        synchronizedMap.put(1, "Один");
//        synchronizedMap.put(2, "Два");
//        synchronizedMap.put(3, "Три");
//        // Выводим размер map
//        System.out.println("Количество элементов: " + synchronizedMap.size());
//        // Получаем и выводим значение по ключу
//        System.out.println("Значение для ключа 1: " + synchronizedMap.get(1));
//        // Удаляем элемент
//        synchronizedMap.remove(2);
//        // Выводим размер map после удаления
//        System.out.println("Количество элементов после удаления одного элемента: " + synchronizedMap.size());
//
//        //Создаем список
//        SemaphoreList<String> semaphoreList = new SemaphoreList<>();
//        try {
//            // Добавляем элементы в список
//            semaphoreList.add("Один");
//            semaphoreList.add("Два");
//            semaphoreList.add("Три");
//            // Получаем и выводим элемент по индексу
//            System.out.println("\nЭлемет с индексом 1: " + semaphoreList.get(1));
//            // Выводим количество элементов в списке
//            System.out.println("Количество элементов в списке: " + semaphoreList.size());
//            // Удаляем элемент по индексу
//            semaphoreList.remove(0);
//            // Выводим размер списка после удаления
//            System.out.println("Список после удаления элемента с индексом 0: " + semaphoreList.size());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

