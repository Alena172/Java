package Практика_6.Абстрактная_фабрика;

class ProductB implements Product
{
    public void doSomething(){
        doPrint();
    }
    public void doPrint()
    {
        System.out.println("ProductB");
    }
}