package Практика_6.Прототип;

public class Test {
    public static void main(String[] args)
        {
            Prototype prototype1 = new ConcretePrototype1();
            Prototype clonedprot = prototype1.clone();
            prototype1.getId();
            clonedprot.getId();

            prototype1 = new ConcretePrototype2();
            clonedprot=prototype1.clone();
            prototype1.getId();
            clonedprot.getId();
        }
}