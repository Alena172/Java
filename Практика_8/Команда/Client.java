package Практика_8.Команда;

public class Client {
    public static void main(String[] args) {
        // Создание получателя команды.
        Receiver receiver = new Receiver();
        // Создание конкретной команды, передавая ей получателя.
        Command command = new ConcreteCommand(receiver);
        // Создание вызывающего.
        Invoker invoker = new Invoker();
        // Установка команды в вызывающем.
        invoker.setCommand(command);
        // Выполнение команды.
        invoker.executeCommand();
    }
}
