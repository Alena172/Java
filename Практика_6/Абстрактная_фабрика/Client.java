package Практика_6.Абстрактная_фабрика;

class Client
{
    Product product;
    void doSomething()
    {
        product.doSomething();
    }

    public void setProduct(Product product) {

        this.product = product;
    }
}