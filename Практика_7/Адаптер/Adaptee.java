package Практика_7.Адаптер;

// Адаптируемый класс
class Adaptee {
    public void specificRequest() {
        System.out.println("Метод specificRequest() Adaptee вызван"); // Специфический метод, который не соответствует интерфейсу Target.
    }
}
