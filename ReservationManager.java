
/**
 * Brian Lee
 * CS202 - Fant
 * Program 4/5
 *
 *
 * Implementation of the Reservation Manager class. This class manages all the reservations the user
 * wishes to make as part of their trip. The reservations are arranged as a DLL, organized by first city
 * added to the users travel itinerary. As the user adds more listings to their stay, if the listing
 * is in a city the user has already requested to stay in, the listing is added to the stack of listing
 * for that existing city. Otherwise a new city is added to the DLL.
 *
 */


public class ReservationManager
{
    private Reservation head;   //head reference to DLL of reservations
    private int total_cost;     //total cost of trip
    private int total_days;     //total days in trip


    //Constructor
    ReservationManager() {
        this.total_cost = 0;
        this.total_days = 0;
        this.head = null;
    }



    //Adds a reservation to the DLL pointed to by head.
    public void addReservation(String loc, StackBlock to_add)
    {
        if(this.head == null)
        {
            this.head = new Reservation(loc);
            if(this.head.push(to_add))
                System.out.println("Your Reservation has been added to " + loc);

            else
                System.out.println("Reservation failed to add");
        }

        else
            addReservation(this.head, loc, to_add);
    }


    //Displays all reservations in the trip
    public void displayTrip()
    {
        if(this.head == null)
            System.out.println("Your Itinerary Is Empty!");

        else
        {
            Reservation temp = head;
            while(temp != null)
            {
                temp.showReservations();
                temp = temp.moveNext();
            }
        }
    }




    //Private method to search the DLL for a matching city. If found, the Stackblock
    //object is added to that city's listings.
    private void addReservation(Reservation current, String loc, StackBlock to_add)
    {
        if(loc.compareTo(current.cityname) == 0)
        {
            if(!current.push(to_add))
                System.out.println("Reservation failed to add");
        }

        else
        {
            if(current.hasNext())
                addReservation(current.moveNext(), loc, to_add);

            else
            {
                current.setNext(new Reservation(loc));
                current.next.setPrevious(current);
                if(!current.push(to_add))
                    System.out.println("Reservation failed to add");
            }
        }

    }

}
