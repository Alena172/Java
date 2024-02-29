package Практика_6.Строитель;

class ConcreteBuilder implements Builder {
    private String part;
    public void buildPart() {
        part = "Constructed part";
    }

    public String getResult() {
        return part;
    }
}