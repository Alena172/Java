package Практика_5;

public class MySingleton1 {
    // Статическая переменная для хранения единственного экземпляра класса
    private static MySingleton1 instance;
    // Приватный конструктор, чтобы предотвратить создание экземпляров класса извне
    private MySingleton1() {
        // Вывод сообщения о создании экземпляра класса
        System.out.println("Singleton создан");
    }
    // Статический метод для получения экземпляра класса
    public static synchronized MySingleton1 getInstance() {
        // Проверяем, был ли уже создан экземпляр класса
        if (instance == null) {
            // Если экземпляр еще не создан, создаем его
            instance = new MySingleton1();
        }
        // Возвращаем единственный экземпляр класса
        return instance;
    }

    public static void main(String[] args) {
        // Получаем первый экземпляр класса
        MySingleton1 firstInstance = MySingleton1.getInstance();
        // Получаем второй экземпляр класса
        MySingleton1 secondInstance = MySingleton1.getInstance();
        // Проверяем, являются ли они одним и тем же объектом
        if(firstInstance == secondInstance){
            System.out.println("Объекты идентичны");
        }
    }
}