
/**
 * Brian Lee
 * CS202 - Fant
 * Program 4/5
 *
 *
 * Implementation file for the Apartment class which is derived from the abstract Listing class
 */


public class Apartment extends Listing
{

    private int rooms;  //How many rooms does the apartment have?
    private int tax;    //Applicable state/city/regional taxes
    private int fees;   //Fees for renting


    //Default Constructor
    Apartment()
    {
       super();
       this.rooms =0;
       this.tax =0;
       this.fees =0;
    }

    //Constructor with arguments for super class data as well as Apartment specifc data
    Apartment(String t, String d, int p, int g, int r, int tx, int f)
    {
        super(t, d, p, g);
        super.type = 0;
        this.rooms = r;
        this.tax = tx;
        this.fees = f;
    }


    //Copy Constructor
    Apartment(Apartment to_copy)
    {
        super(to_copy.title, to_copy.description, to_copy.price, to_copy.guests);
        super.type = 0;
        this.rooms = to_copy.rooms;
        this.tax = to_copy.tax;
        this.fees = to_copy.fees;
    }


    void _display()
    {
        super.stats();
        System.out.println("Rooms: " + this.rooms);
        System.out.println("Taxes: " + this.tax);
        System.out.println("Fees: " + this.fees);
        System.out.println("----------------------------------------");
    }


}
