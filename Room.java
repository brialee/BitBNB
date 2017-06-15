/**
 * Brian Lee
 * CS202 - Fant
 * Program4/5
 *
 *
 * Imeplementation for the Room class. All rooms are derived from the abstract Listing class.
 */


public class Room extends Listing
{
    String rules; //Rules of the house that the room is a part of
    int deposit;    //Depsoit due at check-in
    boolean housekeeping;   //Does the room owner provide house keeping services?



    //Constructor with arguments for Listing class as well as specific data
    //for Room class objects.
    Room(String t, String d, int p, int g, String r, int dep, boolean h)
    {
        super(t,d,p,g);
        super.type = 2;
        this.rules = r;
        this.deposit = dep;
        this.housekeeping = h;
    }



    //Copy Constructor
    Room(Room to_copy)
    {
        super(to_copy.title, to_copy.description, to_copy.price, to_copy.guests);
        super.type = 2;
        this.rules = to_copy.rules;
        this.deposit = to_copy.deposit;
        this.housekeeping = to_copy.housekeeping;
    }



    void _display() {
        super.stats();
        System.out.println("Rules: " + this.rules);
        System.out.println("Deposit: " + this.deposit);
        System.out.println("Housekeeping: " + this.housekeeping);
        System.out.println("---------------------------------------");
    }


    void _copyData(Listing dest)
    {
        dest = new Room(this.title, this.description, this.price, this.guests, this.rules, this.deposit, this.housekeeping);
    }
}
