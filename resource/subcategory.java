package resource;
import java.util.ArrayList;
import java.io.Serializable;

public class subcategory implements Serializable
{
    private static final long serialVersionUID = 1L;
    String subcategory;
    ArrayList<product> sub = new ArrayList<product>();

    public subcategory(String subname, String namein, int quantityin, int pricein)
    {
        this.subcategory = subname;
        product p = new product(namein, quantityin, pricein);
        this.sub.add(p);
    }
    
    public void addProduct(product p)
    {
        this.sub.add(p);
    }
    
    public int searchProduct(String namein)
    {
        for(int i = 0; i < sub.size(); i++)
        {
            if(this.sub.get(i).name.equals(namein))
            {
                System.out.println("\nCustom Exception\nThe "+namein+" is present");
                return i;
            }
        }
        return -1;
    }
}
