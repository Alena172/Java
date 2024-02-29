package Практика_5;

// Определение перечисления MySingleton2, которое будет использоваться для реализации синглтона
public enum MySingleton2 {

    INSTANCE; // Единственный элемент перечисления, представляющий экземпляр синглтона

    // Приватный конструктор, вызывается только один раз при инициализации INSTANCE
    private MySingleton2() {
        System.out.println("Singleton создан"); // Вывод сообщения о создании экземпляра синглтона
    }

    // Метод main для демонстрации использования синглтона
    public static void main(String[] args) {
        // Вывод экземпляра синглтона дважды. Должен выводиться один и тот же экземпляр
        System.out.println(MySingleton2.INSTANCE);
        System.out.println(MySingleton2.INSTANCE);
    }
}
