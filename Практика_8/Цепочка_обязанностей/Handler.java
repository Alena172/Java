package Практика_8.Цепочка_обязанностей;

// Абстрактный класс обработчика, который определяет базовую структуру для обработчиков запросов.
public abstract class Handler {
    protected Handler next; // Ссылка на следующий обработчик в цепочке.

    // Конструктор, принимающий следующий обработчик в цепочке.
    public Handler(Handler next) {
        this.next = next;
    }

    // Абстрактный метод, который должен быть реализован всеми конкретными обработчиками.
    public abstract void handleRequest(Request request);
}
