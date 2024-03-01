package Практика_8.Цепочка_обязанностей;

// Класс для представления запроса.
public class Request {
    private RequestType type; // Тип запроса.

    // Конструктор, принимающий тип запроса.
    public Request(RequestType type) {
        this.type = type;
    }

    // Метод для получения типа запроса.
    public RequestType getType() {
        return type;
    }

    // Метод для установки типа запроса.
    public void setType(RequestType type) {
        this.type = type;
    }
}
