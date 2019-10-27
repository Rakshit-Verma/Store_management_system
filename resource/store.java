package resource;
import java.util.ArrayList;
import java.io.Serializable;

public class store implements Serializable
{
    private static final long serialVersionUID = 1L;
    ArrayList<category> str = new ArrayList<category>();
    
    public void addCat(category c)
    {
        this.str.add(c);
    }

    public int searcCat(String catname)
    {
        for(int i = 0; i < str.size(); i++)
        {
            if(this.str.get(i).category.equals(catname))
            {
                // System.out.println("The "+catname+" is present");
                return i;
            }
        }
        return -1;
    }

}