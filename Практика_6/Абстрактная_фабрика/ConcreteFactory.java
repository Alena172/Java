package Практика_6.Абстрактная_фабрика;

public class ConcreteFactory implements AstractFactory {
    @Override
    public ProductB createProductB() {
        return new ProductB();
    }

    @Override
    public ProductA createProductA() {
        return new ProductA();
    }
}
