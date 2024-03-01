package Практика_8.Цепочка_обязанностей;

public class ConcreteHandler1 extends Handler {
    // Конструктор, принимающий следующий обработчик в цепочке.
    public ConcreteHandler1(Handler next) {
        super(next);
    }

    // Реализация метода обработки запроса.
    @Override
    public void handleRequest(Request request) {
        if (request.getType() == RequestType.TYPE1) {
            System.out.println("ConcreteHandler1 обработал запрос");
        } else {
            // Если запрос не может быть обработан текущим обработчиком, передаем его следующему обработчику в цепочке.
            if (next != null) {
                next.handleRequest(request);
            }
        }
    }
}
