
import java.io.*;


/**
 * Brian Lee
 * CS202 - Fant
 * Program 4/5
 *
 * Implementation file for the City Manager class. This class acts as a container/manager
 * for the AVL tree of Cities pointed to by root. When this object is instantiated, a
 * "prework" method is invoked that reads in listing data for a number of cities, where the
 * data is stored in external text files with the format "city name.text". If the files are
 * not found, a message displays that nothing was read from file.
 */



public class CityManager{
    private City root;  //City AVL tree root.
    private int total;  //total cities in the tree pointed to by root
    private String []fileArray;     //array of file names to be read from
    private File file = null; //refernece to a file class object
    private BufferedReader inFile = null;   //a reference to the file being read from.





    CityManager() {
        this.root = null;
        this.total = 0;

        try {
            this.preWork();
        } catch (Exception x) {
           System.out.println("Nothing Read From File\n\n\n\n");
        }
    }


    //Adds a City to the tree
    public int addCity(City to_add) {

        City c = root;
        City p = root;

        this.addCity(p, c, to_add);

        return total;

    }


    //Displays all cities in the tree pointed to
    //by root. Data is displayed In Order. Function
    //makes use of the City class method to display
    //a tree pointed to by a given City object in order.
    public int display() {
        if (root == null)
            return 0;

        else {
            root.display();
            return total;
        }
    }



    //If a city with a name matching the passed string is
    //found, the method will dislay all the listings in that
    //city.
    public void findCity(String to_find)
    {
        if(root == null)
            System.out.println("There are no cities for me to search!");

        else
        {
            this.findCity(root, to_find);
        }

    }


    public void addLocation(City to_add)
    {
       if(root == null)
       {
           root = new City(to_add);
           System.out.println("Your listing has been added.");
       }

       else
       {
            City temp = root;

       }
    }


    //Searches the city tree for a city with matching name
    //and returns true if found
    public boolean search(String s)
    {
        if(root == null)
            return false;

        else
            return search(root, s);
    }


    //Adds a Listing to an existing city object in
    //the tree. Returns true if the city was found,
    //and the listing was added to the city.
    public boolean addListingToCity(String n, Listing to_add)
    {
        if(root == null)
            return false;

        else
            return addListingToCity(root, n, to_add);
    }



    //Searches the tree of cities to find a matching city, if the mathcing
    //city is found, then the city's tree is search for a listing with
    //matching ID. If a matching ID is found, the Listing data is copied
    //and the method returns true.
    public boolean findLocation(String inCity, int targetID, StackBlock to_copy)
    {
        if(this.root == null)
            return false;

        else
            return this.findLocation(root, inCity, targetID, to_copy);
    }



    //Finds a review for a city with matching name and matching ID.
    public boolean findReview(String inCity, int id)
    {
       if(this.root == null)
           return false;

       else
           return this.findReview(root, inCity, id);
    }


    //----------------- PRIVATE MEMBERS BELOW -----------------




    //Private method to find a review in a matching city with
    //matching ID. If the city and ID are found, the reviews are displayed.
    private boolean findReview(City current, String inCity, int targetID)
    {
        if(current == null)
            return false;

        else
        {
            int result = inCity.compareTo(current.name);

            if(result == 0)
            {
                System.out.println("City Found!");
                return current.findReview(targetID);
            }

            else if(result < 0)
                return findReview(current.moveLeft(), inCity, targetID);

            else
                return findReview(current.moveRight(), inCity, targetID);
        }
    }


    //Private method that returns true if a matching city and matching listing
    //is found within the city. If the listing is found, the data is copied into the
    //Listing argument. If either the city or listing are not found, the method
    //return false.
    private boolean findLocation(City current, String inCity, int targetID, StackBlock to_copy)
    {
        if(current == null)
            return false;

        else
        {
            int result = inCity.compareTo(current.name);

            if(result == 0)
            {
                System.out.println("Matching City Found!");
                return (current.findListing(targetID, to_copy));
            }

            else if (result < 0)
                return findLocation(current.moveLeft(), inCity, targetID, to_copy);

            else
                return findLocation(current.moveRight(), inCity, targetID, to_copy);
        }
    }


    //Searches the tree for a city with a name matching
    //the target. If the city is found, the listing is
    //added to the city and the method returns true.
    private boolean addListingToCity(City current, String target, Listing to_add)
    {
        if(current == null)
            return false;

        else
        {
            int result = target.compareTo(current.name);

            if(result == 0)
            {
                current.addListing(to_add);
                return true;
            }

            else if(result < 1)
                return addListingToCity(current.moveLeft(), target, to_add);

            else
                return addListingToCity(current.moveRight(), target, to_add);
        }
    }


    //Searches the city tree pointed to by root and returns
    //true if a city with a matching name is found. Methods does
    //not display the city's listings if found.
    private boolean search(City current, String target)
    {
        if(current == null)
            return false;

        else
        {
            int result = target.compareTo(current.name);

            if(result == 0)
                return true;

            else if(result < 1)
                return search(current.moveLeft(), target);

            else
                return search(current.moveRight(), target);
        }
    }



    //Recursively traverse the tree pointed to by root to
    //find a city with a name matching the passed target.
    private void findCity(City current, String target)
    {
        if(current == null) {
            System.out.println("Sorry, " + target + " not found");
        }

        else
        {
            //Compare current with argument
           int result = target.compareTo(current.name);

           //Match found
           if(result == 0)
           {
               System.out.println("CITY FOUND....DISPLAYING LISTINGS:");
               current.displayList();
           }

           //Traverse left
           else if(result < 0)
           {
               current = current.moveLeft();
               findCity(current, target);
           }

           //Traverse right
           else
           {
               current = current.moveRight();
               findCity(current, target);
           }
        }
    }




    //Private member function to add a City to the
    //tree pointed to by root. Return true if the
    //operation was successful.
    private void addCity(City previous , City current, City to_add) {
        if(current == null)
        {
            current = to_add;
            ++total;
        }

        else
        {
            //Need to traverse down left subtree
            if (to_add.name.compareTo(current.name) < 0)
            {
                //there is a left subtree to traverse down
                if(current.hasLeft())
                {
                    previous = current;
                    current = current.moveLeft();
                    this.addCity(previous, current, to_add);
                }

                //add at left
                else
                {
                    current.setLeft(new City(to_add));
                    to_add.setParent(current);
                    ++this.total;
                }
            }

            //Need to traverse down right subtree
            else
            {
                //there is a right subtree to traverse
                if(current.hasRight())
                {
                    previous = current;
                    current = current.moveRight();
                    this.addCity(previous, current, to_add);
                }

                //add at right
                else
                {
                    current.setRight(to_add);
                    to_add.setParent(current);
                    ++this.total;
                }
            }


        }

        //If the current node isn't root.
        if(current.parent != null)
        {
            //If the current node's parent has a balance greater than +/- 1, then
            //perform the appropriate rotation around the current node.
            if (current.parent.balance() > 1 || current.parent.balance() < -1) {
                current = current.rotate(current.findCase());
            }
        }

        //If the returning node after rotation does not have a
        //parent, then it is the new root
        if(current.parent == null)
            root = current;


    }




    //Attemps to read in listing data from an external file.
    //Throws exception if the files are not found.
    private void preWork() throws IOException
    {
        setArray(); //populate the []FileArray with file names to read from
        //City temp = null;   //reference to a city that will be added.

        //For each file name in the File Array, open the file, read in the
        //listing data, add the listings to the city, and add the city to
        //the tree pointed to by root. Finally, close the file.
        for(int i=0; i < fileArray.length -1; ++i)
        {
            City temp = new City(fileArray[i]);  //Create a new city with name
            //temp.stats();

            openFile(temp.name + ".txt");   //the file name is the city + .txt
            readFile(temp); //open the text file and read in listing data.

            //temp.displayList();
            this.addCity(temp);     //add the new city to the tree.
        }
    }



    //Sets string values for the fileArray. The file names below
    //are file names that are known to contain data. In future versions
    //the application, upon exiting should write any changes to external
    //text files so that a 'database' can be acquired over time.
    private void setArray()
    {
        fileArray = new String[10];
        fileArray[0] = new String("denver");
        fileArray[1] = new String("havana");
        fileArray[2] = new String("nashville");
        fileArray[3] = new String("oakland");
        fileArray[4] = new String("paris");
        fileArray[5] = new String("portland");
        fileArray[6] = new String("seattle");
        fileArray[7] = new String("tehran");
        fileArray[8] = new String("vancouver");
    }



    //Attempt to open the file, if unable throw an
    //exception.
    private void openFile(String filename) {
        file = new File(filename);

        try {
            inFile = new BufferedReader(new FileReader(file));
        } catch (Exception x) {
            System.out.println("Error...unable to open file " + filename);
        }
    }


    //Reads in data from an external text file and uses
    //that data to create new Listing objects. Those listings
    //are then added to a City object.
    private void readFile(City to_add) throws IOException
    {
        String s1;  //first line in trio
        String s2;  //second line in trio
        String s3;  //third line in trio
        int type = 0;   //type of listing to instantiate


        //read in first line from file
        String line = inFile.readLine();

        //make sure the line isn't empty before continuing
        while(line != null)
        {
            //if true, this mean the Listing type will follow
            if(line.startsWith(">"))
            {
                //determine type
                //Apartment
                if(line.compareTo(">Apartment") == 0)
                    type = 0;

                    //House
                else if(line.compareTo(">House") == 0)
                    type = 1;

                    //Room
                else
                    type = 2;
            }


            //After determining type of Listing, read in data.
            //(which starts on the line after the indicator)
            line = s1 = inFile.readLine();

            //while the end of Listing Type indicator has not been reached
            while(line.compareTo("<") != 0)
            {
                s2 = inFile.readLine();
                s3 = inFile.readLine();
                Listing frmFile = null;

                //after reading in trio of lines, parse data
                String []title_des = s1.split("[:]");
                String []$guest = s2.split("[:]");
                String []specific = s3.split("[:]");


                //$guests data converted to integers (also makes arguments more clear)
                //this data is the same format for all Listing types
                int p = Integer.parseInt($guest[0]);
                int g = Integer.parseInt($guest[1]);


                //Parse to Listing specifics, and create Listing
                //based on indicator found at beginning of read operation

                //Reading apartment data
                if(type == 0)
                {
                    int r = Integer.parseInt(specific[0]);
                    int tx = Integer.parseInt(specific[1]);
                    int f = Integer.parseInt(specific[2]);

                    frmFile = new Apartment(title_des[0], title_des[1], p, g, r, tx, f);
                    to_add.addListing(frmFile);
                }


                //Both the House and Room Listing types have the same types of data
                //as their specifics, but the types of data (int, String, boolean, etc.)
                //represent different things between the Room and House types. Therefore,
                //the data can be parsed the same between House and Room, but the correct
                //Listing still needs to be instantiated.

                else
                {
                    String a = specific[0];
                    int d = Integer.parseInt(specific[1]);
                    boolean b;

                    if(specific[2] == "y" || specific[2] == "Y")
                        b = true;

                    else
                        b = false;

                    //Reading house data
                    if(type == 1)
                    {
                        frmFile = new House(title_des[0], title_des[1], p, g, a, d, b);
                        to_add.addListing(frmFile);
                    }


                    //Reading Room data
                    else
                    {
                        frmFile = new Room(title_des[0], title_des[1], p, g, a, d, b);
                        to_add.addListing(frmFile);
                    }
                }

                //read in the next line, to be checked against "<"
                s1 = line = inFile.readLine();


            }

            //read in the next line to check against null
            line = inFile.readLine();
        }

        //close the file
        this.close();

    }


    //Closes the File class object referenced by
    //inFile.
    private void close() throws IOException {
        inFile.close();
    }
}


