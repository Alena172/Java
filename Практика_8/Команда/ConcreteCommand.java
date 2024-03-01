package Практика_8.Команда;

// Конкретная команда, которая реализует интерфейс Command и содержит ссылку на получателя.
public class ConcreteCommand implements Command {
    private Receiver receiver; // Получатель команды.

    // Конструктор, принимающий получателя команды.
    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    // Реализация метода execute() для выполнения команды.
    @Override
    public void execute() {
        receiver.action(); // Вызов метода действия получателя.
    }
}
