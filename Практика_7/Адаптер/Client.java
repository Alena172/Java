package Практика_7.Адаптер;

// Клиентский код
public class Client {
    public static void main(String[] args) {
        // Создание экземпляра адаптируемого класса.
        Adaptee adaptee = new Adaptee();
        // Создание экземпляра адаптера, передавая ему адаптируемый объект.
        Target adapter = new Adapter(adaptee);
        // Вызов метода request() через интерфейс Target, что приводит к вызову specificRequest() у адаптируемого объекта.
        adapter.request();
    }
}
