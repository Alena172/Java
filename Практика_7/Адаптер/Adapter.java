package Практика_7.Адаптер;

// Адаптер, преобразующий интерфейс Adaptee в интерфейс Target
class Adapter implements Target {
    // Ссылка на адаптируемый объект.
    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        // Инициализация адаптируемого объекта через конструктор адаптера.
        this.adaptee = adaptee;
    }

    public void request() {
        // Вызов специфического метода адаптируемого объекта через интерфейс Target.
        adaptee.specificRequest();
    }
}