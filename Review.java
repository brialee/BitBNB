
/**
 * Brian Lee
 * CS202 - Fant
 * Program 4/5
 *
 * Implementation for the Review class. Class object represents a single review for a given
 * Listing class object. Listings are intended to be arranged in a LLL.
 */


public class Review
{
    private String text;
    private int rating;
    private Review next;

    //Constuctor with arguments for the text
    //and rating of a review
    Review(String t, int r)
    {
        this.text = t;
        this.rating = r;
        this.next = null;
    }


    //Copy constructor
    Review(Review to_copy)
    {
        this.text = to_copy.text;
        this.rating = to_copy.rating;
        this.next = null;
    }



    //Sets the next reference to
    //point to the passed review.
    public void setNext(Review r)
    {
        if(r == null)
            this.next = null;

        else
            this.next = null;
    }


    //Returns true if calling object
    //has a non-null next reference.
    public boolean hasNext()
    {
        if(this.next == null)
            return false;

        else
            return true;
    }


    //Returns the calling objects next
    //reference
    public Review moveNext()
    {
        if(this.next == null)
            return null;

        else
            return this.next;
    }


    //Displays the review data
    public void display()
    {
        System.out.println("Review: " + this.text);
        System.out.println("Rating: " + this.rating);
        System.out.println("--------------------------------");
    }
}
