package Практика_6.Прототип;

class ConcretePrototype2 implements Prototype {
    int id = 2;
    public Prototype clone() {
        return new ConcretePrototype2();
    }
    public void getId(){
        System.out.println("ID: " + id);
    }
}