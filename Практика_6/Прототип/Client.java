package Практика_6.Прототип;

class Client {
    Prototype operation()
    {
        Prototype prototype = new ConcretePrototype1();
        Prototype clone = prototype.clone();
        prototype = new ConcretePrototype2();
        clone = prototype.clone();
        return prototype;
    }
}
