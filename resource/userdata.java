package resource;
import java.util.ArrayList;
import java.io.Serializable;

public class userdata implements Serializable
{
    private static final long serialVersionUID = 1L;
    public ArrayList<user> userdata = new ArrayList<user>();

    public int adduser(user u)
    {
        int index = searchuser(u.getid());
        if(index == -1)
        {
            userdata.add(u);
            System.out.println("User added");
            return 1;
        }
        System.out.println("User already present");
        return -1;
    }

    public int searchuser(String idin)
    {
        for(int i = 0; i < userdata.size(); i++)
        {
            if(userdata.get(i).getid().equals(idin))
            {
                System.out.println("User found in database");
                return i;
            }
        }
        System.out.println("User not present in database");
        return -1;
    }
}