package Практика_5;

// Импорт статического метода assertEquals из библиотеки JUnit для проверки условий
import static org.junit.Assert.*;

// Класс MySingleton3, реализующий шаблон синглтона
public class MySingleton3 {

    // Создание и инициализация единственного экземпляра синглтона
    private static final MySingleton3 INSTANCE = new MySingleton3();

    // Приватный конструктор для предотвращения создания экземпляров извне класса
    private MySingleton3() {}

    // Статический метод для получения экземпляра синглтона
    public static MySingleton3 getInstance() {
        return INSTANCE;
    }

    // Метод main для демонстрации использования синглтона
    public static void main(String[] args) {
        // Получение экземпляра синглтона дважды
        MySingleton3 instance1 = MySingleton3.getInstance();
        MySingleton3 instance2 = MySingleton3.getInstance();

        // Проверка равенства двух экземпляров
        assertEquals(instance1, instance2);

        // Вывод сообщения о том, что объекты идентичны
        System.out.println("Объекты идентичны");
    }
}