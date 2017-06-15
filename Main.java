
import java.util.Scanner;
import java.io.*;

/**
 * Brian Lee
 * CS202 - Fant
 * Program 4/5
 *
 * Implementation for the main class. The main class acts as the container or application for the
 * tree of cities (where each city has listings to stay in). When main starts, a brief description
 * of the program is displayed, followed by the main menu. The menu will continue to show after perform
 * the requested tasks until the user wishes to exit the program.
 *
 */


public class Main {

    protected static Scanner input = null;



    //Displays a brief intro to the application
    public static void info()
    {
        System.out.println("\n\n\n\n\t\t\tWelcome to Bit-BNB\n\n");
        System.out.println("Tired of getting stiffed by large hotel chains, or just prefer a more 'local' flavor when travelling?");
        System.out.println("Bit-BNB is the solution to your (lodging) related problems!");
        System.out.println("With this application you can browse a city for places to stay, plan your trip, host your place");
        System.out.println("and much, much more! How much more? Try using the application to find out!....Actually, that's pretty much it.");
    }


    //Displays a menu to the user with options to
    //select from. Method will return the option the
    //user selected, but it will not check that the
    //result is within the bounds of the menu choices.
    public static int menu()
    {
        int selection;

        System.out.println("\n\nChoose from an option below:");
        System.out.println("1 - Host your place");
        System.out.println("2 - Find/View a city");
        System.out.println("3 - Plan a trip");
        System.out.println("4 - List Cities");
        System.out.println("5 - View Itinerary");
        System.out.println("6 - View Reviews");
        System.out.println("7 - Exit");
        System.out.println("---------------------------");
        System.out.print("Enter selection: ");
        selection = input.nextInt();
        input.nextLine();
        System.out.print("\n\n");

        return selection;
    }



    public static void main(String[] args)
    {
        input = new Scanner(System.in);
        CityManager bitBNB_locations = new CityManager();       //reference to the city managing class
        boolean exit = false;       //for exiting the program
        ReservationManager bitBNB_reservations = new ReservationManager();


        //Displays intro
        info();



        //Whlie the user has not request to exit
        do {


            //Display the menu
            int selection = menu();

            //Option 1, Host your place
            if(selection == 1)
            {
                Listing to_add = null;
                City hostCity = null;
                String inCity;
                String t;   //title
                String d;   //description
                int p;      //price
                int g;      //max guests
                int type;   //type of listing

                String unique;  //string unique to listing
                int x;      //for storing int
                int y;
                int z;
                boolean b;  //used for house/room objects


                System.out.println("Great! Thanks for helping Bit-BNB expand!");
                System.out.print("What city are you hosting in: ");
                inCity = input.nextLine();

                if(!bitBNB_locations.search(inCity))
                {
                    hostCity = new City(inCity);
                    System.out.println("Hey, that's a new city for us!...First Post!!");
                }

                else
                {
                    System.out.println("Okay, looks like we have listings already in that");
                    System.out.println("location, let's add to it!!\n");
                }




                System.out.println("What type of space would you like to host?");
                System.out.println("1 - Apartment");
                System.out.println("2 - House");
                System.out.println("3 - Room");
                System.out.print("Enter selection: ");
                type = input.nextInt();
                input.nextLine();

                //Types start with 0
                --type;

                //Gather info needed to all types
                System.out.print("Give your space a title: ");
                t = input.nextLine();

                System.out.print("How about a description: ");
                d = input.nextLine();

                System.out.print("Enter the price per night: ");
                p = input.nextInt();
                input.nextLine();

                System.out.print("Enter max number of guests: ");
                g = input.nextInt();
                input.nextLine();

                //Gather apartment info
                if(type == 0)
                {
                   System.out.print("How many rooms: ");
                   x = input.nextInt();
                   input.nextLine();

                   System.out.print("Applicable taxes: ");
                   y = input.nextInt();
                   input.nextLine();

                   System.out.print("Applicable fees: ");
                   z = input.nextInt();
                   input.nextLine();

                   to_add = new Apartment(t,d,p,g,x,y,z);
                }

                //Gather House info
                else if(type == 1)
                {
                    String r;

                    System.out.print("Enter accommodations: ");
                    unique = input.nextLine();

                    System.out.print("Enter deposit amount: ");
                    x = input.nextInt();
                    input.nextLine();

                    System.out.print("Garage? Enter y/n: ");
                    r = input.nextLine();

                    if(r.startsWith("y") || r.startsWith("y"))
                        b = true;

                    else
                        b = false;

                    to_add = new House(t,d,p,g,unique,x,b);

                }

                //Gather room info
                else
                {
                    String r;

                    System.out.print("Enter the owner's rules: ");
                    unique = input.nextLine();

                    System.out.print("Enter deposit amount: ");
                    x = input.nextInt();
                    input.nextLine();

                    System.out.print("Housekeeping? Enter y/n: ");
                    r = input.nextLine();

                    if(r.startsWith("y") || r.startsWith("y"))
                        b = true;

                    else
                        b = false;


                    to_add = new Room(t,d,p,g,unique,x,b);
                }


                //User is adding listing to existing city
                if(hostCity == null)
                {
                    if(bitBNB_locations.addListingToCity(inCity, to_add))
                        System.out.println("Listing added!");

                    else
                        System.out.println("Listing was not added");
                }

                //user is adding listing to a new city
                else
                {
                    hostCity.addListing(to_add);
                    hostCity.displayList();

                    bitBNB_locations.addCity(hostCity);


                    bitBNB_locations.addListingToCity(inCity, to_add);
                    System.out.println("Listing Added!");
                }




            }


            //Option 2. If the city is found, the listings are displayed
            if(selection == 2)
            {
                String search = new String();

                System.out.println("To search for a city please provide the city name.");
                System.out.println("If the city is found, the available listing in that");
                System.out.println("city will be displayed");
                System.out.print("Enter city name: ");
                search = input.nextLine();

                bitBNB_locations.findCity(search);
            }



            //Option 3, plan a trip
            if(selection == 3)
            {
                String targetCity;
                int targetID;
                StackBlock to_find = new StackBlock();
                int days;

                System.out.println("\n\nPlan your escape!");
                System.out.print("What city is your listing in: ");
                targetCity = input.nextLine();

                System.out.print("What is the ID for the listing: ");
                targetID = input.nextInt();
                input.nextLine();

                System.out.print("How many days are you reserving the listing for: ");
                days = input.nextInt();
                input.nextLine();


                //If a city with a matching name is found, and if a listing with a
                //matching ID is found in that city, then the listing is added to
                //the itinerary. After method, to_find will have basic data needed
                //in order to add the listing (abstract listing data), however the
                //number of days the user is staying and the price for that reservation
                //are updated after gathering basic info.
                if(bitBNB_locations.findLocation(targetCity, targetID, to_find)) {
                    to_find.setDays(days);  //set days/price
                    System.out.println("Listing Below: ");
                    to_find.display();
                    System.out.println("\nThanks, Let's continue....\n");
                }

                else
                    System.out.println("Sorry, the listing was not found!");


                //Peform the actual addition to the reservation list
                bitBNB_reservations.addReservation(targetCity, to_find);
            }



            //List Available cities
            if(selection == 4)
            {
                System.out.println("Available Cities: ");
                bitBNB_locations.display();
            }



            //Option 5, view itinerary
            if(selection == 5)
                bitBNB_reservations.displayTrip();


            //Option 6, view reviews
            if(selection == 6)
            {
                String inCity;
                int id;


                System.out.print("What City is the review in: ");
                inCity = input.nextLine();

                System.out.print("What is the ID number: ");
                id = input.nextInt();
                input.nextLine();

                bitBNB_locations.findReview(inCity, id);
            }

            //Option 7, EXIT
            if(selection == 7)
                exit = true;


            //User's input invalid
            if(selection < 1 || selection > 7)
                System.out.println("INVALID SELECTION");


        }while(exit == false);


        System.out.println("\n\nGoodbye!\n\n");
    }


}
