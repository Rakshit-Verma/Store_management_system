package resource;

import java.io.Serializable;

public class portal implements Serializable
{
    private static final long serialVersionUID = 1L;
    store s = new store();
    public userdata database = new userdata();

    public void addp(String catname, String subname, String namein, int quantityin, int pricein)
    {
        int i, j, k;
        k = s.searcCat(catname);
        if(k != -1) //category exist
        {
            j = s.str.get(k).searchSubcat(subname);
            if(j != -1) //subcategory exist
            {
                i = s.str.get(k).cat.get(j).searchProduct(namein);
                if(i == -1) //product not present
                {
                    product p = new product(namein, quantityin, pricein);
                    s.str.get(k).cat.get(j).addProduct(p);
                }
                else
                {
                    System.out.println("The Given Product already exist!!!");
                }
            }
            else    //subcategory not present
            {
                subcategory sc = new subcategory(subname, namein, quantityin, pricein);
                s.str.get(k).addSubcat(sc);
            }
        }
        else    //category not present
        {
            category c = new category(catname, subname, namein, quantityin, pricein);
            s.addCat(c);
        }
    }

    public void deletesc(String catname ,String subname)
    {
        int j = s.searcCat(catname);
        if(j != -1) //category exist
        {
            int i = s.str.get(j).searchSubcat(subname);
            if(i != -1)
            {
                s.str.get(j).cat.remove(i);
                System.out.println("Sub-Category removed");
            }
            else
            {
                System.out.println("Sub-Category does not exist");
            }
        }
        else
        {
            System.out.println("Category does not exist");
        }
    }

    public void deletep(String catname, String subname, String namein)
    {
        int i, j, k;
        k = s.searcCat(catname);
        if(k != -1) //category exist
        {
            j = s.str.get(k).searchSubcat(subname);
            if(j != -1) //subcategory exist
            {
                i = s.str.get(k).cat.get(j).searchProduct(namein);
                if(i != -1) //product  present
                {
                    s.str.get(k).cat.get(j).sub.remove(i);
                }
                else
                {
                    System.out.println("Product does not exist!!!");
                }
            }
            else    //subcategory not present
            {
                System.out.println("Sub-Category does not exist");
            }
        }
        else    //category not present
        {
            System.out.println("Category does not exist");
        }
    }

    public product searchp(String namein)
    {
        product p = new product("-", 0, 0);
        for(int k = 0; k < s.str.size(); k++)
        {
            for(int j = 0; j < s.str.get(k).cat.size(); j++)
            {
                for(int i = 0; i < s.str.get(k).cat.get(j).sub.size(); i++)
                {
                    if(s.str.get(k).cat.get(j).sub.get(i).name.equals(namein))
                    {
                        System.out.println(s.str.get(k).category+" > "+s.str.get(k).cat.get(j).subcategory+" > "+ namein);
                        System.out.println("Quantity : "+s.str.get(k).cat.get(j).sub.get(i).quantity);
                        System.out.println("Price : "+s.str.get(k).cat.get(j).sub.get(i).price);
                        return s.str.get(k).cat.get(j).sub.get(i);
                    }
                }
            }
        }
        System.out.println(namein+" does not exist");
        return p;
    }

    public void modifyp(String namein, int pricein)
    {
        for(int k = 0; k < s.str.size(); k++)
        {
            for(int j = 0; j < s.str.get(k).cat.size(); j++)
            {
                for(int i = 0; i < s.str.get(k).cat.get(j).sub.size(); i++)
                {
                    if(s.str.get(k).cat.get(j).sub.get(i).name.equals(namein))
                    {
                        System.out.println(s.str.get(k).category+" > "+s.str.get(k).cat.get(j).subcategory+" > "+ namein);
                        System.out.println("Old Price : "+s.str.get(k).cat.get(j).sub.get(i).price);
                        s.str.get(k).cat.get(j).sub.get(i).modifyprice(pricein);
                        System.out.println("New Price : "+s.str.get(k).cat.get(j).sub.get(i).price);
                        return;
                    }
                }
            }
        }
        System.out.println(namein+" does not exist");
    }

    public void modifyq(String namein, int quantityin)
    {
        for(int k = 0; k < s.str.size(); k++)
        {
            for(int j = 0; j < s.str.get(k).cat.size(); j++)
            {
                for(int i = 0; i < s.str.get(k).cat.get(j).sub.size(); i++)
                {
                    if(s.str.get(k).cat.get(j).sub.get(i).name.equals(namein))
                    {
                        System.out.println(s.str.get(k).category+" > "+s.str.get(k).cat.get(j).subcategory+" > "+ namein);
                        System.out.println("Old Quantity : "+s.str.get(k).cat.get(j).sub.get(i).quantity);
                        s.str.get(k).cat.get(j).sub.get(i).modifyquantity(quantityin);
                        System.out.println("New Quantity : "+s.str.get(k).cat.get(j).sub.get(i).quantity);
                        return;
                    }
                }
            }
        }
        System.out.println(namein+" does not exist");
    }
    
    public void sale(cart basket)
    {
        for(int i = 0; i < basket.Items.size(); i++)
        {
            System.out.println("\nCurrent Status of the product in store");
            product p = new product(searchp(basket.Items.get(i).getName()));
            System.out.println("Product name and quantity sold\n"+basket.Items.get(i).getName());
            System.out.println(basket.Items.get(i).getQuantity());
            // System.out.println("P values \n"+p.getName()+"\n"+p.getQuantity());
            modifyq(basket.Items.get(i).getName(), p.getQuantity()-basket.Items.get(i).getQuantity());        
        }
        basket.Items.clear();
    }
}