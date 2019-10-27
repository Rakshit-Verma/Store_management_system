import java.util.*;
import java.io.*;
import resource.*;

class Amacon
{
    public static void serialize(Object obj, String filename){
        try{

            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);
            // int [] a = new int[100];
            // for(int i = 0; i < 100; i++)
            // {
            //     a[i] = i+100;
            // }
            out.writeObject(obj);
            out.close();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Object deserialize(String filename) 
    {
        Object o = null;
        if(new File(filename).exists())
        {
            try
            {
                // int [] b = new int [100];
                // for(int i = 0; i < 299; i++)
                // {
                //     b[i] = i*10;
                // }
                FileInputStream file = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(file);

                o = in.readObject();
                in.close();
                return o;

            } 
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return o;
    }
    public static void main(String[] args)
    {
        int totalsold = 0;
        Scanner input = new Scanner(System.in);
        boolean t1 = true, t2, t3, t4;
        String catname, subname, namein;
        int quantityin, pricein;
        cart basket;
        int funds = 0;
        portal element = null;
        String filename = "Account_details";
        if(new File(filename).exists())
            element = (portal)deserialize(filename);
        else
            element = new portal();
        while(t1)
        {
            System.out.println("Please Select the mode of Use\n1.Admin\n2.Customer\n3.Exit");
            int choice = input.nextInt();
            if(choice == 1)
            {
                t2 = true;
                while(t2)
                {
                    System.out.println("\nADMIN :");
                    System.out.println("Please Select :\n1.Insert\n2.Delete\n3.Search\n4.Modify\n5.Exit As Admin");
                    int choice1 = input.nextInt();
                    switch(choice1)
                    {
                        case 1:{
                        System.out.println("Please provide the path, name, quantity and price of the product");
                        catname = input.next();
                        input.next();
                        subname = input.next();
                        namein = input.next();
                        quantityin = input.nextInt();
                        pricein = input.nextInt();
                        element.addp(catname, subname, namein, quantityin, pricein);}
                        break;
                        
                        case 2:
                        System.out.println("Please provide the path");
                        System.out.println("Please Select :\n1.Delete Subcategory\n2.Delete Product");
                        int choice2 = input.nextInt();
                        if(choice2 == 1)
                        {
                            System.out.println("Please provide the path");
                            catname = input.next();
                            input.next();
                            subname = input.next();
                            element.deletesc(catname, subname);
                        }
                        else if(choice2 == 2)
                        {
                            System.out.println("Please provide the path");
                            catname = input.next();
                            input.next();
                            subname = input.next();
                            namein = input.next();
                            element.deletep(catname, subname, namein);
                        }
                        break;
                        
                        case 3:
                            System.out.println("Please provide the name");
                            namein = input.next();
                            product p = element.searchp(namein);
                            break;
        
                        case 4:
                            System.out.println("Please Select :\n1.Modify Quantity\n2.Modify Price");
                            int choice3 = input.nextInt();
                            if(choice3 == 1)
                            {
                                System.out.println("Please provide name and quantity of the product");
                                namein = input.next();
                                quantityin = input.nextInt();
                                element.modifyq(namein, quantityin);
                            }
                            else if(choice3 == 2)
                            {
                                System.out.println("Please provide name and price of the product");
                                namein = input.next();
                                pricein = input.nextInt();
                                element.modifyp(namein, pricein);
                            }
                            break;
                        case 5:
                            System.out.println("\nAdmin session ended\n*******\n");
                            t2 = false;
                            break;
                    }
                }
            }
            else if(choice == 2)
            {
                String idin = "";
                int dataadr;
                t4 = true;
                while(t4)
                {
                    System.out.println("Please Select : \n1.Existing user\n2.New user");
                    int select = input.nextInt();
                    if(select == 1)
                    {
                        System.out.println("Please provide the user id");
                        idin = input.next();
                        dataadr = element.database.searchuser(idin);
                        if(dataadr == -1)
                        {
                            System.out.println("Please try again or add a new user");
                        }
                        else
                        {
                            System.out.println("Logged in succesfully");
                            t4 = false;
                        }
                    }
                    else if(select == 2)
                    {
                        System.out.println("Please prvoide the new user id");
                        idin = input.next();
                        cart cin = new cart();
                        user u = new user(idin, cin);
                        dataadr = element.database.adduser(u);
                        if(dataadr == -1)
                        {
                            System.out.println("Please try again or log in as an existing user");
                        }
                        else if(dataadr == 1)
                        {
                            t4 = false;
                        }
                    }
                }
                System.out.println("Please add funds to your account :");
                funds = input.nextInt();
                dataadr = element.database.searchuser(idin);
                basket = element.database.userdata.get(dataadr).getcart();
                t3 = true;
                while(t3)
                {

                    System.out.println("\nCUSTOMER :");
                    System.out.println("Please Select :\n1.Add a product to cart\n2.Check Out\n3.Exit as customer");
                    int choice1 = input.nextInt();
                    switch(choice1)
                    {
                        case 1:
                            System.out.println("Please provide the name of the product and quantity");
                            namein = input.next();
                            quantityin = input.nextInt();
                            product p = new product(element.searchp(namein));
                            if(p.getName().equals("-")  || p.getQuantity() <= 0)
                                System.out.println("Product can't be added to cart");
                            else
                                basket.addToCart(p, quantityin);
                            break;
                        
                        case 2:
                            System.out.println("You have checked out \nBill:");
                            int totalcost = basket.checkout();
                            totalsold+=totalcost;
                            funds = funds - totalcost;
                            System.out.println("Total Cost : "+totalcost+"\nTotal funds remaining : "+funds);
                            element.sale(basket);
                            t3 = false;
                            break;
    
                        case 3:
                            System.out.println("\nCustomer session ended\n*******\n");
                            t3 = false;
                            break;
                    }
                }
            }
            else
            {
                System.out.println("Total revenue generated by Amacon\n"+totalsold);
                serialize(element, filename);
                t1 = false;
            }
        }
        input.close();
        return;
    }
}