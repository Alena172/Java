package Практика_8.Команда;

// Вызывающий, который содержит ссылку на команду и выполняет ее.
public class Invoker {
    private Command command; // Команда для выполнения.

    // Метод для установки команды.
    public void setCommand(Command command) {
        this.command = command;
    }

    // Метод для выполнения команды.
    public void executeCommand() {
        command.execute(); // Выполнение команды.
    }
}
