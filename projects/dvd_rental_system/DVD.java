// Alexis Jennings
/*
 * Description:
 * This class serves to represent DVD titles in a DVD rental system. The class has variables to keep track
 * of the title, the number of DVDs of that title available to rent, and the number of DVDs of that title
 * currently rented out.
 * The class has an overridden compareTo method that incorporates the compareToIgnoreCase method.
 */

public class DVD implements Comparable<DVD>
{
    // member variables
    private String title;
    private int numAvailable;
    private int numRented;
    
    // overloaded constructor
    public DVD(String title, int numAvailable, int numRented)
    {
	this.title = title;
	this.numAvailable = numAvailable;
	this.numRented = numRented;
    }
    
    // accessors
    public String getTitle()
    {
	return this.title;
    }
    
    public int getAvailable()
    {
	return this.numAvailable;
    }
    
    public int getRented()
    {
	return this.numRented;
    }
    
    // mutators
    public void setTitle(String newTitle)
    {
	this.title = newTitle;
    }
    
    public void setAvailable(int newAvailable)
    {
	this.numAvailable = newAvailable;
    }
    
    public void setRented(int newRented)
    {
	this.numRented = newRented;
    }

    // overridden compareTo method
    @Override
    public int compareTo(DVD o)
    {
	if (this.title.compareToIgnoreCase(o.title) == 0)
	{
	    return 0;
	}
	else if (this.title.compareToIgnoreCase(o.title) > 0)
	{
	    return 1;
	}
	else
	{
	    return -1;
	}
    }
}