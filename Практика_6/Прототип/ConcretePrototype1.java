package Практика_6.Прототип;

class ConcretePrototype1 implements Prototype {
    int id = 1;
    public Prototype clone() {
        return new ConcretePrototype1();
    }
    public void getId(){
        System.out.println("ID: " + id);
    }
}