package resource;
import java.io.Serializable;

public class user implements Serializable
{
    private static final long serialVersionUID = 1L;
    String id;
    cart c;

    public user(String idin, cart cin)
    {
        id = idin;
        c = cin;
    }

    public String getid()
    {
        return id;
    }

    public cart getcart()
    {
        return c;
    }
}