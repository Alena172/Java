package Практика_6.Строитель;

public class Test {
    public static void main(String[] args) {
        // Создаем экземпляр ConcreteBuilder
        ConcreteBuilder concreteBuilder = new ConcreteBuilder();

        // Создаем директора и передаем ему строителя
        Director director = new Director(concreteBuilder);

        // Вызываем метод construct() директора, который в свою очередь вызывает метод buildPart() строителя
        director.construct();

        // Получаем результат от строителя
        String result = concreteBuilder.getResult();

        // Выводим результат
        System.out.println("Результат: " + result);
    }
}