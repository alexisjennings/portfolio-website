// Alexis Jennings
// aej190000
/*
 * Description:
 * This class serves to represent a seat in a movie theater. It has variables to store the row and
 * column (seat letter) of the seat, as well as the ticket type (adult, child, or senior) purchased
 * by the customer occupying the seat. 
 * 
 * The overloaded constructor sets the row, column (seat letter), and the ticket type of the seat.
 */

public class Seat
{
    // member variables
    private int row;
    private char seatLetter;
    private char ticketType;
    
    // default constructor
    public Seat()
    {}
    
    // overloaded constructor
    public Seat(int row, char seatLetter, char ticketType)
    {
	setRow(row);
	setSeatLetter(seatLetter);
	setTicketType(ticketType);
    }
    
    // accessors
    public int getRow()
    {
	return this.row;
    }
    
    public char getSeatLetter()
    {
	return this.seatLetter;
    }
    
    public char getTicketType()
    {
	return this.ticketType;
    }
    
    // mutators
    public void setRow(int newRow)
    {
	this.row = newRow;
    }
    
    public void setSeatLetter(char newSeat)
    {
	this.seatLetter = newSeat;
    }
    
    public void setTicketType(char newTicket)
    {
	this.ticketType = newTicket;
    }
}
