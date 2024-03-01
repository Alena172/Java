package Практика_7.Мост;

// Клиентский код
public class Test {
    public static void main(String[] args) {
        // Создание конкретной реализации A.
        Implementor implementorA = new ConcreteImplementorA();
        // Создание конкретной реализации B.

        Implementor implementorB = new ConcreteImplementorB();
        // Создание расширенной абстракции с реализацией A.
        Abstraction abstractionA = new RefinedAbstraction(implementorA);
        // Создание расширенной абстракции с реализацией B.
        Abstraction abstractionB = new RefinedAbstraction(implementorB);
        // Вызов операции для абстракции A.
        abstractionA.operation();
        // Вызов операции для абстракции B.
        abstractionB.operation();
    }
}