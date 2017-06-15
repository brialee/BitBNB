

import java.util.Scanner;
/**
 * Brian Lee
 * CS202 - Fant
 * Program 4/5
 *
 * Implementation file for the Listing class. From the listing class the apartment, house
 * and room classes are derived. Listing are stored as an AVL tree in order of the listing
 * title. A listing root is part of each city, as each city will have its' own locations
 * to rent.
 *
 */



abstract class Listing
{

    protected String title;     //Listing's title
    protected String description;   //Description of space
    protected int price;    //price per night
    protected int guests;   //Max number of guests
    protected Listing parent = null;    //parent listing 'node'
    protected Listing left = null;      //left listing 'node'
    protected Listing right = null;     //right listing 'node'
    protected int type;     // 0=apt, 1=house, 2=room
    protected int ID;       //id associated with listing
    protected Review head;  //head for LLL of reviews
    protected int totalR = 0; //number of reviews for the Listing
    private static Scanner input = null;


    //Default constructor
    Listing()
    {
        this.title = null;
        this.description = null;
        this.price = 0;
        this.guests = 0;
        this.parent = null;
        this.left = null;
        this.right = null;
        this.head = null;
    }


    //Constructor with arguments for title, description, price, and max guests
    Listing(String t, String d, int p, int g)
    {
        this.title = t;
        this.description = d;
        this.price = p;
        this.guests = g;
        this.ID = this.setID();
        this.head = null;
    }


    //Copy constructor
    Listing(Listing to_copy)
    {
        this.title = to_copy.title;
        this.description = to_copy.description;
        this.price = to_copy.price;
        this.guests = to_copy.guests;
        this.ID = this.setID();
        this.head = null;
    }





    //Sets the left reference to point to the
    //passed Listing object. If the object is
    //null, the left reference is set to null.
    public void setLeft(Listing c) {
        if(c == null)
            this.left = null;

        else
            this.left =c;
    }



    //Sets the right reference to point to the
    //passed Listing object. If the object is
    //null, the right reference is set to null.
    public void setRight(Listing c) {
        if(c == null)
            this.right = null;

        else
            this.right = c;
    }



    //Sets the parent reference to point to
    //the passed Listing object. If the object
    //is null, the parent is set to null.
    public void setParent(Listing c)
    {
        if(c == null)
            this.parent = null;

        else
            this.parent = c;
    }

    //Returns the calling Listing object's
    //right reference.
    public Listing moveRight()
    {
        return this.right;
    }

    //Returns the calling Listing object's
    //left reference
    public Listing moveLeft()
    {
        return this.left;
    }



    //If the calling object has non-null
    //left reference, the method returns
    //true.
    public boolean hasLeft()
    {
        if(left == null)
            return false;

        else
            return true;
    }


    //If the calling objecth as a non-null
    //right reference, the method return false.
    public boolean hasRight()
    {
        if(right == null)
            return false;

        else
            return true;
    }





    //Returns the balance factor for a Listing object
    public int balance()
    {
        return -height(this.left) + height(this.right);
    }



    //Displays the data pointed to by the calling
    //Listing object in alphabetical order by title.
    public void display()
    {
        System.out.println("Total Listings: " + (this.inOrder() + 1));
    }



    //Returns the height of the calling Listing object
    public int height()
    {
        //Calling object is root
        if(this == null)
            return 0;

        else
            return height(this);

    }



    //Displays the listing's data
    abstract void _display();


    //Displays the stats for the calling Listing object.
    //This method was used to test the AVL tree insertions.
    //It has since been changed to display the Listing's data
    //fields.
    public void stats()
    {
        System.out.println("Listing ID: " + this.ID);
        System.out.println("Reviews: " + this.totalR);
        System.out.println("Title: " + this.title);
        System.out.println("Description: " + this.description);
        System.out.println("Price per night: " + this.price);
        System.out.println("Max Guests: " + this.guests);

    }


    //Passed string gets the value of the
    //Listing's title.
    public void retrieveName(String s)
    {
        s = this.title;
    }



    //Returns true if the calling Listing
    //has a valid ancestor.
    public boolean hasAncestor()
    {
        if(this.parent == null)
            return false;

        if(this.parent.parent == null)
            return false;

        else
            return true;
    }


    //Returns the calling Listing object's
    //ancestor.
    public Listing ancestor()
    {
        return this.parent.parent;
    }




    //Performs a single right rotation around the
    //calling Listing object. The argument is the calling
    //object's parent. Returns the "root" of the balanced tree.
    public Listing singleRight(Listing rent)
    {
        //If the calling object has a ancestor, set the
        //appropriate relationships.
        if(this.hasAncestor())
        {
            //Setting 'left' ancestor
            if(this.ancestor().left == this.parent)
            {
                this.ancestor().setLeft(this);
                this.setParent(this.ancestor());
            }

            //Setting 'right' ancestor
            else if(this.ancestor().right == this.parent)
            {
                this.ancestor().setRight(this);
                this.setParent(this.ancestor());
            }

            //If the calling object has a right subtree
            //the parent inherits the right subtree.
            if(this.hasRight())
            {
                this.right.setParent(rent);
                rent.setLeft(this.right);
                this.setRight(rent);

                this.right.setParent(this);
            }

            //Then, perform rotation.
            if(!this.hasRight())
            {
                this.setRight(rent);
                rent.setParent(this);
                rent.setLeft(null);
            }

            return this;
        }

        //There is no ancestor, this means that the returning
        //object should be the root of the tree now.
        else {
            this.parent.setLeft(this.right);
            this.setRight(this.parent);
            this.setParent(this.parent.parent);
            this.right.setParent(this);

            if(this.right.left != null)
                this.right.left.setParent(this.right);

            return this;
        }
    }


    //Performs a single right rotation around the
    //calling Listing object. The argument is the calling
    //object's parent. Returns the "root" of the balanced tree.
    public Listing singleLeft(Listing rent)
    {
        //If the calling object has an ancestor, set the
        //appropriate relationships.
        if(this.hasAncestor())
        {

            //Setting 'left' ancestor
            if(this.ancestor().left == this.parent)
            {
                this.ancestor().setLeft(this);
                this.setParent(this.ancestor());
            }

            //Setting 'right' ancestor
            else if(this.ancestor().right == this.parent)
            {
                this.ancestor().setRight(this);
                this.setParent(this.ancestor());
            }

            //If the calling object has a left subtree,
            //the parent inherits the left subtree.
            if(this.hasLeft())
            {
                this.left.setParent(rent);
                rent.setRight(this.left);
                this.setLeft(rent);


                this.left.setParent(this);
            }

            //Then, perform the rotation
            if(!this.hasLeft())
            {
                this.setLeft(rent);
                rent.setParent(this);
                rent.setRight(null);
            }

            return this;
        }


        //The calling object doesn't have an ancestor,
        //this means it should be the root of the list now.
        else
        {

            this.parent.setRight(this.left);
            this.setLeft(this.parent);
            this.setParent(this.ancestor());
            this.left.setParent(this);

            if(this.left.right != null)
                this.left.right.setParent(this.left);

            return this;
        }
    }




    //Returns an integer based on the arrangement
    //of the calling Listing object.
    public int findCase()
    {
        //left rotation
        if(this.parent.balance() > 1 && this.balance() > 0)
            return 1;

            //right rotation
        else if(this.parent.balance() < -1 && this.balance() < 0)
            return 2;

            //right-left rotation
        else if(this.parent.balance() > 1 && this.balance() < 0)
            return 3;

            //left-right rotation
        else if(this.parent.balance() < -1 && this.balance() > 0)
            return 4;

        else
            return 99;
    }



    //Performs a rotation on the calling Listing
    //object depending on the case (n) passed. Returns
    //the 'root' after the rotation (of the tree/subtree).
    public Listing rotate(int n)
    {
        //left rotation
        if(n==1)
        {
            return this.singleLeft(this.parent);
        }

        //right rotation
        else if(n==2)
        {
            return this.singleRight(this.parent);
        }

        //right-left rotation
        else if(n==3)
        {
            Listing temp = this.left;
            temp.singleRight(temp.parent);

            return temp.singleLeft(temp.parent);
        }

        //left-right rotation
        else if(n==4) {
            Listing temp = this.right;
            temp.singleLeft(temp.parent);
            return temp.singleRight(temp.parent);
        }

        //case not supported
        return null;
    }


    //Adds a review to the LLL pointed to
    //by head. Reviews are always added at
    //head.
    public int addReview(Review to_add)
    {
        if (this.head == null)
        {
            head = new Review(to_add);
            ++totalR;
            return totalR;
        }
        else
        {
            to_add.setNext(head);
            head = to_add;
            ++totalR;
            return totalR;
        }
    }


    //Displays all reviews for a given
    //location.
    public void displayReviews()
    {
        Review temp = head;

        while (temp != null) {
            temp.display();
            temp = temp.moveNext();
        }

        if(head == null)
            System.out.println("No Reviews For This Listing");
    }


    public void leaveReview()
    {
        String response;
        String text;
        int num;
        Review to_add;
        input = new Scanner(System.in);

        System.out.print("Would you like to leave a review: ");
        response = input.nextLine();

        if(response.charAt(0) == 'Y' || response.charAt(0) == 'y')
        {
            System.out.print("Enter review: ");
            text = input.nextLine();

            System.out.print("Enter rating, 1-10: ");
            num = input.nextInt();
            input.nextLine();

            to_add = new Review(text, num);

            this.addReview(to_add);
        }

    }



    //---------------- PRIVATE MEMBERS BELOW ------------




    //Displays the data pointed to by calling Listing
    //object in order of Listing title.
    public int inOrder()
    {
        int total = 0;

        if(this.hasLeft())
        {
            total += this.left.inOrder() + 1;
        }

        this._display();

        if(this.hasRight())
            total += this.right.inOrder() + 1;

        return total;
    }



    //Does the work the traversing the tree
    //recursively to determine the height of the
    //tree.
    private int height(Listing root)
    {
        if(root == null)
            return -1;

        int leftHeight = height(root.moveLeft());
        int rightHeight = height(root.moveRight());

        if(leftHeight > rightHeight)
            return leftHeight +1;

        else
            return rightHeight +1;

    }


    //Creates an ID for the listing based off
    //of the listing title.
    private int setID()
    {
        int total = 0;

        for(int i=0; i < this.title.length(); ++i)
            total += this.title.charAt(i);

        return (total / this.title.length()) * this.title.length();
    }


    //Returns true if the argument matches
    //the objects ID
    public boolean checkID(int match)
    {
        return (this.ID == match);
    }


}
