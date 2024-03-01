package Практика_8.Цепочка_обязанностей;

public class ConcreteHandler2 extends Handler {
    // Конструктор, принимающий следующий обработчик в цепочке.
    public ConcreteHandler2(Handler next) {
        super(next);
    }

    // Реализация метода обработки запроса.
    @Override
    public void handleRequest(Request request) {
        if (request.getType() == RequestType.TYPE2) {
            System.out.println("ConcreteHandler2 обработал запрос");
        } else {
            // Если запрос не может быть обработан текущим обработчиком, передаем его следующему обработчику в цепочке.
            if (next != null) {
                next.handleRequest(request);
            }
        }
    }
}
