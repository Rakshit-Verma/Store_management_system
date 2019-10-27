package resource;
import java.io.Serializable;
import java.util.ArrayList;

public class category implements Serializable
{
    private static final long serialVersionUID = 1L;
    String category;
    ArrayList<subcategory> cat = new ArrayList<subcategory>();

    public category(String catname, String subname, String namein, int quantityin, int pricein)
    {
        this.category = catname;
        subcategory sc = new subcategory(subname, namein, quantityin, pricein);
        this.cat.add(sc);
    }
    
    public void addSubcat(subcategory sc)
    {
        this.cat.add(sc);
    }

    public int searchSubcat(String subname)
    {
        for(int i = 0; i < cat.size(); i++)
        {
            if(this.cat.get(i).subcategory.equals(subname))
            {
                // System.out.println("The "+subname+" is present");
                return i;
            }
        }
        return -1;
    }
}