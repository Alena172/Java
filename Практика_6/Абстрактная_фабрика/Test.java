package Практика_6.Абстрактная_фабрика;

public class Test {
    public static void main(String[] args) {
        Client client = new Client();
        ConcreteFactory concreteFactory = new ConcreteFactory();
        client.setProduct(concreteFactory.createProductB());
        client.doSomething();
        client.setProduct(concreteFactory.createProductA());
        client.doSomething();
    }
}
