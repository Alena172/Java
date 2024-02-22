package Практика_4;

public class Test {
    public static void main(String[] args) {
        CustomExecutorService executorService = new CustomExecutorService(3); // Создаем исполнитель с тремя потоками

        // Добавляем задачи в исполнитель
        for (int i = 0; i < 10; i++) {
            int taskId = i;
            executorService.submit(() -> {
                System.out.println("Задача " + taskId + " выполняется в потоке " + Thread.currentThread().getName());
            });
        }

        // Предполагаем, что все задачи выполнены через некоторое время
        try {
            Thread.sleep(2000); // Ждем 2 секунды для завершения задач
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Завершаем работу исполнителя
        executorService.shutdown();

        // Проверяем, что исполнитель завершил работу и больше не может принимать задачи
        try {
            executorService.submit(() -> System.out.println("Эта задача не должна выполняться"));
            // Если код дойдет до этой строки, значит тест провален, поскольку ExecutorService должен быть завершен и не должен принимать новые задачи.
            System.out.println("Тест провален");
        } catch (IllegalStateException e) {
            // Ожидаемое исключение, если ExecutorService был завершен, то он не должен принимать новые задачи
            System.out.println("Тест пройден: ExecutorService отклонил задачу после завершения работы.");
        }
    }
}
