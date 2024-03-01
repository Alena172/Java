package Практика_8.Цепочка_обязанностей;

public class Client {
    public static void main(String[] args) {
        // Создание цепочки обработчиков.
        // Первый обработчик в цепочке.
        Handler handler1 = new ConcreteHandler1(null);
        // Второй обработчик в цепочке, следующий за первым.
        Handler handler2 = new ConcreteHandler2(handler1);

        // Создание запросов.
        Request request1 = new Request(RequestType.TYPE1);
        Request request2 = new Request(RequestType.TYPE2);

        // Обработка запросов цепочкой обработчиков.
        // Обработка запроса первым обработчиком.
        handler2.handleRequest(request1);
        // Обработка запроса вторым обработчиком.
        handler2.handleRequest(request2);
    }
}
