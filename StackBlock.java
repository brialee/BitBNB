

/**
 * Brian Lee
 * CS202 - Fant
 * Program4/5
 *
 * Implementation for the Stack Block class. This class represents the object that will be pushed onto
 * the reservation stack. The object contains basic data about a listing (name, description, price, max guests).
 *
 *
 */




public class StackBlock
{
    private String title;   //listing title
    private String des;     //listing description
    private int price;      //listing price
    private int guests;     //max guests allowed in listing
    private int days;       //how may days is the user staying at the listing
    private int total;      //total price of reservation based on number of days & price


    //Default Constructor
    StackBlock() {
        this.title = null;
        this.des = null;
        this.price = 0;
        this.guests = 0;
        this.days = 0;
        this.total = 0;
    }


    //Constructor with arguments
    StackBlock(String t, String dsc, int p, int g, int d) {
        this.title = t;
        this.des = dsc;
        this.price = p;
        this.guests = g;
        this.days = d;
        this.total = p * d;
    }

    //Constructor with arguments
    StackBlock(String t, String dsc, int p, int g) {
        this.title = t;
        this.des = dsc;
        this.price = p;
        this.guests = g;
    }

    //Copy Constructor
    StackBlock(StackBlock to_copy) {
        this.title = to_copy.title;
        this.des = to_copy.des;
        this.price = to_copy.price;
        this.guests = to_copy.guests;
        this.days = to_copy.days;
        this.total = to_copy.days * to_copy.price;
    }





    //Displays the data for the stack block
    public void display() {
        System.out.println("Title: " + this.title);
        System.out.println("Description: " + this.des);
        System.out.println("Price Per Night: $" + this.price);
        System.out.println("Max Guests: " + this.guests);
        System.out.println("Length of stay: " + this.days + " days");
        System.out.println("Total for stay: $" + this.total);
        System.out.println("-----------------------------------");
    }


    //Sets the number of days the user is staying at the listing.
    //This also sets the total cost for the listing.
    public void setDays(int n) {
        this.days = n;
        this.total = this.days * this.price;
    }


    //Update object with passed arguments
    public void update(String t, String d, int p, int g)
    {
        this.title = t;
        this.des = d;
        this.price = p;
        this.guests = g;
    }


}
