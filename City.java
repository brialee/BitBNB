
import java.util.Scanner;


/**
 * Brian Lee
 * CS202 - Fant
 * Program 4/5
 *
 * Implementation for the City class. Each City is a node in an AVL tree, where each City object
 * also contains a root pointer to a Listing AVL tree.
 */


public class City {

    protected City parent = null;   //parent node
    protected City left = null;     //left node
    protected City right = null;    //right node
    protected String name = null;   //city name
    private Listing root = null;    //Listing tree root
    private int total;              //total listings in tree



    //Constructor with argument for the
    //city's name.
    City(String s) {
        this.name = s;

    }



    //Copy Constructor. Does not copy the tree
    //in the passed object because there can only
    //be one copy of a city.
    City(City to_copy) {
        this.name = to_copy.name;
    }



    private void copyTree(Listing rootCopy)
    {
        if(rootCopy != null)
        {
            if(rootCopy.hasLeft())
                copyTree(rootCopy.moveLeft());

            if(rootCopy.hasRight());
                copyTree(rootCopy.moveRight());

            this.addListing(rootCopy);

        }
    }




    //Sets the left node to reference
    //the passed argument.
    public void setLeft(City c) {
        if(c == null)
            this.left = null;

        else
            this.left =c;
    }


    //Sets the right node to refernece
    //the passed argument.
    public void setRight(City c) {
        if(c == null)
            this.right = null;

        else
            this.right = c;
    }


    //Sets the parent node to reference
    //the passed argument.
    public void setParent(City c)
    {
        if(c == null)
            this.parent = null;

        else
            this.parent = c;
    }


    //Returns the calling object's right reference.
    public City moveRight()
    {
        return this.right;
    }

    //Returns the calling object's left reference.
    public City moveLeft()
    {
        return this.left;
    }


    //Returns true if the calling object
    //has a non-null left field.
    public boolean hasLeft()
    {
        if(left == null)
            return false;

        else
            return true;
    }

    //Returns true if the calling object
    //has a non-null right field.
    public boolean hasRight()
    {
        if(right == null)
            return false;

        else
            return true;
    }





    //Returns the balance factor for a City object
    public int balance()
    {
        return -height(this.left) + height(this.right);
    }

    //Displays the data pointed to by the calling
    //City object in alphabetical order by city name.
    public void display()
    {
        System.out.println("Total Cities: " + (this.inOrder() + 1));
    }



    //Returns the height of the calling City object
    public int height()
    {
        //Calling object is root
       if(this == null)
           return 0;

       else
           return height(this);
    }


    //Displays the name of the city and how many
    //listing are available in that city.
    public void _display()
    {
        System.out.println("City: " + this.name);
        System.out.println("Number of listings: " + this.total);
        System.out.println("---------------------------");
    }

    //Displays the stats for the calling City.
    //This function is mostly useful for debugging
    public void stats()
    {
       System.out.println("City: " + this.name);

       //Print the parent City's name
       if(this.parent == null)
           System.out.println("Parent: This node is root!");

       else
       {
           City temp = this.parent;
           System.out.println("Parent: " + temp.name);
       }

       System.out.println("Height: " + this.height());
       System.out.println("Balance: " + this.balance());

       if(this.left != null)
            System.out.println("Left: " + this.left.name);

       if(this.right != null)
            System.out.println("Right: " + this.right.name);


       System.out.println("-----------------------");

    }


    public void retrieveName(String s)
    {
        s = this.name;
    }


    //Returns true if the calling object has
    //an ancestor.
    public boolean hasAncestor()
    {
        if(this.parent == null)
            return false;

        if(this.parent.parent == null)
            return false;

        else
            return true;
    }

    //Returns the calling object's refernece.
    public City ancestor()
    {
        return this.parent.parent;
    }



    //Performs a single right rotation around
    //the calling object. The argument is the
    //parent of the calling object.
    public City singleRight(City rent)
    {
        //If the calling object has an ancestor,
        //set appropriate relationships.
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

            //If the calling object has a
           //right subtree, the parent inherits
           //the right subtree.
            if(this.hasRight())
            {
               this.right.setParent(rent);
               rent.setLeft(this.right);
               this.setRight(rent);

               this.right.setParent(this);
            }

            //Then, perform the rotation.
            if(!this.hasRight())
            {
                this.setRight(rent);
                rent.setParent(this);
                rent.setLeft(null);
            }

            return this;
       }

       //The calling object has no ancesor,
       //this means the returning object should be
       //the root of the tree.
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



    //Performs a single left rotation around the
    //calling object. The argument is the calling
    //objects parent.
    public City singleLeft(City rent)
    {
        //If the calling object has an ancestor,
        //set the appropriate relationships.
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


            //Then, perform the rotation.
            if(!this.hasLeft())
            {
                this.setLeft(rent);
                rent.setParent(this);
                rent.setRight(null);
            }

            return this;
       }


       //The calling object does not have an
       //ancestor. This mean the returning object should
       //be the root of the tree.
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
    //of the calling City object.
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


    //Performs a rotation on the calling node
    //based on the argument passed. Returns the
    //'root' after rotation.
    public City rotate(int n)
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
            City temp = this.left;
            temp.singleRight(temp.parent);

            return temp.singleLeft(temp.parent);
        }

        //left-right rotation
        else if(n==4) {
            City temp = this.right;
            temp.singleLeft(temp.parent);
            return temp.singleRight(temp.parent);
        }

        //case not supported
        return null;
    }




    //Adds a Listing to the tree pointed to
    //by root. Displays the total after adding.
    public void addListing(Listing to_add) {

        Listing c = this.root;
        Listing p = this.root;

        this.addListing(p, c, to_add);
    }


    //Displays all cities in the tree pointed to
    //by root. Data is displayed In Order. Function
    //makes use of the City class method to display
    //a tree pointed to by a given City object in order.
    public int displayList() {
        if (root == null)
        {
            System.out.println("The are no listings in " + this.name);
            return 0;
        }

        else
        {
            System.out.println("Listings in " + this.name);
            System.out.println("_________________________________");
            root.display();
            return total;
        }
    }


    //----------------- PRIVATE MEMBERS BELOW -----------------


    //Private member function to add a Listing to the
    //tree pointed to by root. Return true if the
    //operation was successful.
    private void addListing(Listing previous , Listing current, Listing to_add) {
        if(current == null)
        {
            current = to_add;
            ++total;
        }

        else
        {
            //Need to traverse down left subtree
            if (to_add.title.compareTo(current.title) < 0)
            {
                //There is a left subtree to traverse
                if(current.hasLeft())
                {
                    previous = current;
                    current = current.moveLeft();
                    addListing(previous, current, to_add);
                }


                //adding at left
                else
                {
                    current.setLeft(to_add);
                    to_add.setParent(current);
                    ++total;
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
                    addListing(previous, current, to_add);
                }

                //adding at right
                else
                {
                    current.setRight(to_add);
                    to_add.setParent(current);
                    ++total;
                }
            }


        }

       //While the current node is not pointing to root.
        if(current.parent != null)
        {
            //If the parent of the calling node has a |balance| > 1 then perform
            //the appropriate rotation around the current node.
            if (current.parent.balance() > 1 || current.parent.balance() < -1) {
                current = current.rotate(current.findCase());
            }
        }

        //If the returning object has a null parent,
        //then it is the root of the treee.
        if(current.parent == null)
            root = current;


    }






    //Displays the City data pointed to by calling City
    //object in order of City Name.
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
    private int height(City root)
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



    public boolean findListing(int targetID, StackBlock to_copy)
    {
        if(this.root == null)
            return false;

        else if(this.findListing(this.root, targetID, to_copy) == 1)
            return true;

        else
            return false;
    }


    public boolean findReview(int target)
    {
        if(this.root == null)
            return false;

        else if(this.findReview(this.root, target) == 1)
            return true;

        else
            return false;
    }


    private int findListing(Listing current, int targetId, StackBlock to_copy)
    {
        int total = 0;

        if(current == null)
            return 0;

        else
        {
            if(current.hasLeft())
                total += findListing(current.moveLeft(), targetId, to_copy);

            if(current.hasRight())
                total += findListing(current.moveRight(), targetId, to_copy);

            if(current.checkID(targetId))
            {
                to_copy.update(current.title, current.description, current.price, current.guests);
                System.out.println("Listing Found!");
                ++total;
            }
        }
        return total;
    }




    private int findReview(Listing current, int targetId)
    {
        int total = 0;

        if(current == null)
            return 0;

        else
        {
            if(current.hasLeft())
                total += findReview(current.moveLeft(), targetId);

            if(current.hasRight())
                total += findReview(current.moveRight(), targetId);

            if(current.checkID(targetId))
            {
                current.displayReviews();
                current.leaveReview();
                ++total;
            }
        }
        return total;
    }
}
