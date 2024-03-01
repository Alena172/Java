package Практика_7.Мост;

class RefinedAbstraction extends Abstraction {
    // Конструктор, принимающий объект реализации.
    public RefinedAbstraction(Implementor implementor) {
        super(implementor);
    }

    // Реализация абстрактного метода operation().
    public void operation() {
        // Вызов метода реализации объекта Implementor.
        implementor.operationImpl();
    }
}