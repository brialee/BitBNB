
/**
 * Brian Lee
 * CS202 - Fant
 * Program4/5
 *
 * Implementation for the Reservation class. This class represents a single reservation object, where
 * each object has a stack of listing in the same city. Once the reservation's stack if full, no new
 * listings can be added to the reservation.
 */


public class Reservation
{
    protected Reservation previous = null;  //previous reference
    protected Reservation next = null;      //next reference
    protected String cityname;      //name of the city for the listings
    private int index = 0;      //index to push new listings onto stack
    private final int size = 5; //stack size
    private StackBlock []stack; //stack of StackBlock objects



    //Constructor with argument for city name
    Reservation(String n)
    {
        this.cityname = n;
        this.index = 0;

        stack = new StackBlock[size];
    }



    //Returns true if the calling object has a
    //non-null next reference
    public boolean hasNext()
    {
        if(this.next == null)
            return false;

        else
            return true;
    }


    //Returns true if the calling object has a
    //non-null previous reference
    public boolean hasPrevious()
    {
        if(this.previous == null)
            return false;

        else
            return true;
    }


    //Sets object's previous pointer to point
    //to the passed reservation object.
    public void setPrevious(Reservation r)
    {
        if(r == null)
            this.previous= null;

        else
            this.previous = r;
    }


    //Sets object's next pointer to point
    ///to the passed reservation object.
    public void setNext(Reservation r)
    {
        if(r == null)
            this.next = null;

        else
            this.next = r;
    }


    //Returns the calling object's previous reservation
    public Reservation movePrevious()
    {
        if(this.previous == null)
            return null;

        else
            return this.previous;
    }


    //Returns the calling object's next reservation
    public Reservation moveNext()
    {
        if(this.next== null)
            return null;

        else
            return this.next;
    }


    //Adds an item to the stack. If the stack is
    //full, method returns false. If returning false
    //another City, with the same name should be added
    //to the reservation DLL
    public boolean push(StackBlock to_add)
    {
       if(index == size)
           return false;

       else
       {
          stack[index] = new StackBlock(to_add);
          ++index;
          return true;
       }
    }


    //Iterates over available listings in the stack
    //and displays listing data
    public void showReservations()
    {
        for(int i=0; i < index; ++i)
            stack[i].display();
    }
}
