package resource;

import java.io.Serializable;
import java.util.ArrayList;

public class cart implements Serializable
{
    private static final long serialVersionUID = 1L;
    ArrayList<product> Items = new ArrayList<product>();

    public void addToCart(product p, int quantityin)
    {
        if(p.quantity < quantityin)
        {
            System.out.println("Not enough quantity in the store");
            return;
        }
        p.modifyquantity(quantityin);
        Items.add(p);
        return;
    }

    public int checkout()
    {
        int totalprice = 0;
        for(int i = 0; i < Items.size(); i++)
        {
            totalprice += Items.get(i).getQuantity()*Items.get(i).getPrice();
            Items.get(i).printp();
        }
        return totalprice;
    }
}