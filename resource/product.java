package resource;
import java.io.Serializable;

public class product implements Serializable
{
    private static final long serialVersionUID = 1L;
    String name;
    int quantity;
    int price;
    
    public product(product another)
    {
        this.name = another.name;
        this.quantity= another.quantity;
        this.price = another.price;
    }

    public product(String namein, int quantityin, int pricein)
    {
        this.name = namein;
        this.quantity = quantityin;
        this.price = pricein;
    }

    public void modifyquantity(int quantityin)
    {
        this.quantity = quantityin;
    }

    public void modifyprice(int pricein)
    {
        this.price = pricein;
    }
    
    public String getName()
    {
        return this.name;
    }

    public int getQuantity()
    {
        return this.quantity;
    }

    public int getPrice()
    {
        return this.price;
    }

    public void printp()
    {
        System.out.println("Name\t\t:\t"+this.name+"\nQuantity\t:\t"+this.quantity+"\nPrice\t\t:\t"+this.price);
        return;
    }
}
