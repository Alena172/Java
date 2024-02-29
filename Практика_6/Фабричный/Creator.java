package Практика_6.Фабричный;

// Абстрактный класс фабрики
abstract class Creator {
    public abstract Product factoryMethod();

    public void anOperation() {
        Product product = factoryMethod();
        product.doSomething();
    }
}