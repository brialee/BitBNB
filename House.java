/**
 * Brian Lee
 * CS202 - Fant
 * Program 4/5
 *
 *
 * Implementation file for the House class, which is derived from the abstract Listing class.
 */



public class House extends Listing
{

    private String accom;   //Accommodations that come with the house
    private int deposit;    //Depsoit due on check-in
    private boolean garage; //Does the house come with a garage?




    //Constructor with arguments for super class data and data specific to the House class
    House(String t, String d, int p, int g, String ac, int dep, boolean gar)
    {
        super(t,d,p,g);
        super.type = 1;
        this.accom = ac;
        this.deposit = dep;
        this.garage = gar;
    }


    //Copy Constructor
    House(House to_copy)
    {
        super(to_copy.title, to_copy.description, to_copy.price, to_copy.guests);
        super.type = 1;
        this.accom = to_copy.accom;
        this.deposit = to_copy.deposit;
        this.garage = to_copy.garage;
    }


    void _display() {
        super.stats();
        System.out.println("Accommodations: " + this.accom);
        System.out.println("Deposit: " + this.deposit);
        System.out.println("Garage: " + this.garage);
        System.out.println("-------------------------------------");
    }


    void _copyData(Listing dest)
    {
        dest = new House(this.title, this.description, this.price, this.guests, this.accom, this.deposit, this.garage);
    }
}
