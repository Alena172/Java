package Практика_3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class SemaphoreList<E> {
    private final List<E> list; // Список элементов
    private final Semaphore semaphore; // Семафор для синхронизации доступа

    public SemaphoreList() {
        this.list = new ArrayList<>(); // Инициализация списка
        this.semaphore = new Semaphore(1); // Инициализация семафора с одним доступным разрешением
    }

    // Метод добавления элемента в список с учетом семафора
    public void add(E element) throws InterruptedException {
        semaphore.acquire(); // Захват разрешения семафора
        try {
            list.add(element); // Добавление элемента в список
        } finally {
            semaphore.release(); // Освобождение разрешения семафора
        }
    }

    // Метод получения элемента из списка по индексу с учетом семафора
    public E get(int index) throws InterruptedException {
        semaphore.acquire(); // Захват разрешения семафора
        try {
            return list.get(index); // Получение элемента из списка по индексу
        } finally {
            semaphore.release(); // Освобождение разрешения семафора
        }
    }

    // Метод удаления элемента из списка по индексу с учетом семафора
    public void remove(int index) throws InterruptedException {
        semaphore.acquire(); // Захват разрешения семафора
        try {
            list.remove(index); // Удаление элемента из списка по индексу
        } finally {
            semaphore.release(); // Освобождение разрешения семафора
        }
    }

    // Метод получения размера списка с учетом семафора
    public int size() throws InterruptedException {
        semaphore.acquire(); // Захват разрешения семафора
        try {
            return list.size(); // Получение размера списка
        } finally {
            semaphore.release(); // Освобождение разрешения семафора
        }
    }
}