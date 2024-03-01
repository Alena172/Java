package Практика_7.Мост;

// Абстракция
abstract class Abstraction {
    // Ссылка на объект реализации.
    protected Implementor implementor;

    // Конструктор, принимающий объект реализации.
    public Abstraction(Implementor implementor) {
        this.implementor = implementor;
    }

    // Абстрактный метод, который должен быть реализован подклассами.
    public abstract void operation();
}
