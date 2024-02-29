package Практика_6.Абстрактная_фабрика;

class ProductA implements Product
{
    @Override
    public void doSomething(){
        doPrint();
    }
    public void doPrint()
    {
        System.out.println("ProductA");
    }
}