// Alexis Jennings
// aej190000
/*
 * Description:
 * This program's purpose is to simulate a movie theater seat reserving program. It has the following methods:
 * 
 * 1. Main: This method creates the Auditorium object from a file, then displays a menu to to the customer.
 * The customer can choose to either reserve seats in the auditorium, or exit the program. The program cycle
 * continues to loop until the customer chooses to exit the program. Using the Auditorium object, the seat
 * layout is printed to the screen, so that the customer may view which seats are available. The customer is
 * then asked to input their desired row, column, and number of tickets with their types. If the seats are
 * available, they are reserved. If the seats are not available, the seats closest to the middle of the
 * auditorium are selected instead. The customer can then choose whether or not to reserve the new seats.
 * When the program exits, a report is printed on the tickets sold.
 * 
 * 2. traverseAuditorium: This method traverses the given Auditorium object and finds the given row and seat
 * letter. It returns a pointer to the given seat. If the seat does not exist, it returns the last seat of the
 * last row, as it has traversed through the entire Auditorium.
 * 
 * 3. checkAvailability: This method checks whether or not the seats selected are available. It uses a for loop
 * to check if the seats selected are available. The method returns false if any seat selected is unavailable,
 * or true if they are all available.
 * 
 * 4. reserveSeats: This method modifies the Auditorium object to reserve the user's seats. It will run after
 * the selected seats have been confirmed to be available. Each seat is modified to contain the correct ticket type.
 * 
 * 5. writeToFile: This method prints the current Auditorium to a file, in the same format that Auditorium files
 * are read in.
 * 
 * 6. bestAvailable: This method will find the "best" available seats in the entire Auditorium, given that the
 * customer's original desired seats are unavailable. "Best" refers to the seats closest to the center of the
 * auditorium. These seats are determined by their distance to the center. The method returns null if no seats
 * were found, otherwise it will return a pointer to the first seat of the "best" available seats.
 * 
 * 7. displayReport: This method prints a report on the number of tickets sold after the program is exited.
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws IOException
    {
	// declare filename and scanner
	String filename;
	Scanner userInput = new Scanner(System.in);
	
	// prompt the user for the input file name
	System.out.println("Enter the name of the file:");
	filename = userInput.nextLine();
	
	// create auditorium object
	Auditorium theater = new Auditorium(filename);
	
	// make sure the list is not empty
	if (theater.getFirst() != null)
	{
	    	// create temporary node pointer
		Node<Seat> curr = new Node<Seat>();
		
		// get number of rows
		int countRows = 0;
		curr = theater.getFirst();
		while(curr != null)
		{
		    countRows++;
		    curr = curr.getDown();
		}
		
		// get number of columns
		int countCols = 0;
		curr = theater.getFirst();
		while(curr != null)
		{
		    countCols++;
		    curr = curr.getRight();
		}
		
		// set the number of rows and columns
		theater.setRows(countRows);
		theater.setCols(countCols);
		
		int userChoice = 0;
		String line;
		
		while (userChoice != 2)
		{
		    // display the menu to the user
		    System.out.println("1. Reserve Seats");
		    System.out.println("2. Exit");

		    // get the user's input
		    line = userInput.nextLine();

		    try
		    {
			userChoice = Integer.parseInt(line);

		    } 
		    catch (Exception e)
		    {
			System.out.println("Invalid input. Please choose a valid option");
			continue;
		    }

		    // user menu input has been validated
		    // user wants to reserve tickets
		    if (userChoice == 1)
		    {
			// print the column header
			System.out.print("  ");
			Node<Seat> temp = theater.getFirst();
			for (int i = 0; i < theater.getCols(); i++)
			{
			    System.out.print(temp.getPayload().getSeatLetter());
			    temp = temp.getRight();
			}
			System.out.println();

			// reset temporary node to traverse the theater
			temp = theater.getFirst();

			// node for keeping track of the first seat of the current row
			Node<Seat> tempTop = temp;

			// print the auditorium
			for (int i = 0; i < theater.getRows(); i++)
			{
			    System.out.print((i + 1) + " ");
			    for (int j = 0; j < theater.getCols(); j++)
			    {
				if (temp.getPayload().getTicketType() == 'A' || temp.getPayload().getTicketType() == 'C' || temp.getPayload().getTicketType() == 'S')
				{
				    // filled seats
				    System.out.print("#");
				    if ((j + 1) % theater.getCols() == 0)
				    {
					System.out.println();
				    }
				}
				else if (temp.getPayload().getTicketType() == '.')
				{
				    // empty seats
				    System.out.print(".");
				    if ((j + 1) % theater.getCols() == 0)
				    {
					System.out.println();
				    }
				}
				else
				{
				    continue;
				}
				// go to the next seat
				temp = temp.getRight();
			    }
			    // go to the next row
			    tempTop = tempTop.getDown();
			    temp = tempTop;
			}
			// prompt the user for the row and validate the input
			int rowChoice = 0;
			while (rowChoice < 1 || rowChoice > theater.getRows())
			{
			    System.out.println("Please enter the desired row: ");
			    line = userInput.nextLine();

			    try
			    {
				rowChoice = Integer.parseInt(line);
			    }
			    catch (Exception e)
			    {
				System.out.println("Invalid input. Please choose a valid row");
				continue;
			    }
			}

			// prompt the user for the seat letter and validate the input
			char seatChoice = '.';
			while (seatChoice > 'Z' || seatChoice < 'A')
			{
			    System.out.println("Please enter the desired seat: ");
			    line = userInput.nextLine();

			    try
			    {
				if (line.length() != 1)
				{
				    throw new InputMismatchException();
				}
				else
				{
				    seatChoice = line.charAt(0);
				    if (seatChoice < 'A' || seatChoice > 'Z')
				    {
					throw new InputMismatchException();
				    }
				}
			    }
			    catch (Exception e)
			    {
				System.out.println("Invalid input. Please choose a valid seat");
				continue;
			    }
			}

			// prompt the user for the number of adult tickets
			int numAdult = -1;
			while (numAdult < 0)
			{
			    System.out.println("Please enter the number of adult tickets: ");
			    line = userInput.nextLine();

			    try
			    {
				numAdult = Integer.parseInt(line);
			    }
			    catch (Exception e)
			    {
				System.out.println("Invalid input. Please enter a valid number");
				continue;
			    }
			}

			// prompt the user for the number of adult tickets
			int numChild = -1;
			while (numChild < 0)
			{
			    System.out.println("Please enter the number of child tickets: ");
			    line = userInput.nextLine();

			    try
			    {
				numChild = Integer.parseInt(line);
			    }
			    catch (Exception e)
			    {
				System.out.println("Invalid input. Please enter a valid number");
				continue;
			    }
			}

			// prompt the user for the number of senior tickets
			int numSenior = -1;
			while (numSenior < 0)
			{
			    System.out.println("Please enter the number of senior tickets: ");
			    line = userInput.nextLine();

			    try
			    {
				numSenior = Integer.parseInt(line);
			    }
			    catch (Exception e)
			    {
				System.out.println("Invalid input. Please enter a valid number");
				continue;
			    }
			}
			// at this point, all basic user input has been validated
			// add up the total tickets
			int totalTickets = numAdult + numChild + numSenior;

			// check availability
			boolean status = checkAvailability(theater, rowChoice, seatChoice, totalTickets);

			// reserve the seats if they are available
			if (status == true)
			{
			    reserveSeats(theater, rowChoice, seatChoice, numAdult, numChild, numSenior);

			    // print the reserved seats to the user
			    // format: "Seats 1A - 1B reserved"
			    Node<Seat> firstSeat = traverseAuditorium(theater, rowChoice, seatChoice);
			    Node<Seat> lastSeat = traverseAuditorium(theater, rowChoice, (char) ((seatChoice + totalTickets) - 1));
			    System.out.println("Seats " + rowChoice + firstSeat.getPayload().getSeatLetter() + " - " + rowChoice + lastSeat.getPayload().getSeatLetter() + " reserved");

			    // write the updated auditorium to a file
			    writeToFile(theater);
			}
			// the selected seats are not available
			else
			{
			    // find the best available seats within the selected row
			    Node<Seat> result = bestAvailable(theater, totalTickets);

			    // best available seats found
			    if (result != null)
			    {
				System.out.println("Your selected seats are not available. Reserve these seats instead? (Y/N)");

				// print the new seats
				// format: "Seats 1A - 1B reserved"
				Node<Seat> lastSeat = traverseAuditorium(theater, result.getPayload().getRow(), (char) ((result.getPayload().getSeatLetter() + (totalTickets) - 1)));
				System.out.println("Seats " + result.getPayload().getRow() + result.getPayload().getSeatLetter() + " - " + result.getPayload().getRow() + lastSeat.getPayload().getSeatLetter());

				// prompt the user for their answer to the question and validate the input
				char choice = '.';
				while (choice != 'Y' || choice != 'N')
				{
				    System.out.println("Please enter your choice: ");
				    line = userInput.nextLine();

				    try
				    {
					if (line.length() != 1)
					{
					    throw new InputMismatchException();
					}
					else
					{
					    choice = line.charAt(0);
					    if (choice != 'Y' && choice != 'N')
					    {
						throw new InputMismatchException();
					    }
					    else
					    {
						break;
					    }
					}
				    }
				    catch (Exception e)
				    {
					System.out.println("Invalid input. Please choose a valid answer");
					continue;
				    }
				}

				// if yes, reserve the new seats
				if (choice == 'Y')
				{
				    reserveSeats(theater, result.getPayload().getRow(), result.getPayload().getSeatLetter(), numAdult, numChild, numSenior);

				    // print the reserved seats to the user
				    // format: "Seats 1A - 1B reserved"
				    System.out.println("Seats " + rowChoice + result.getPayload().getSeatLetter() + " - " + rowChoice + lastSeat.getPayload().getSeatLetter() + " reserved");

				    // write the updated auditorium to a file
				    writeToFile(theater);
				}
				else
				{
				    continue;
				}
			    }
			    // no available seats with the correct criteria were found within the row
			    else
			    {
				System.out.println("no seats available");
			    }
			}
		    }
		}
		// close the scanner
		userInput.close();
		
		// write the report to the console
		displayReport(theater);
	}
	else
	{
	    System.out.println("The file was not read properly. Please restart the program");
	}
    }
    
    // traverses the auditorium to the selected seat
    // returns the node pointer to the specified seat
    public static Node<Seat> traverseAuditorium(Auditorium theater, int row, char seat)
    {
	// create temporary node to traverse the auditorium
	Node<Seat> temp = theater.getFirst();
	
	// find correct row
	for (int i = 0; i < theater.getRows(); i++)
	{
	    // if the row is the correct row, break out of the traversal loop
	    if (temp.getPayload().getRow() == row)
	    {
		break;
	    }
	    // row is not correct, so go down one row
	    else
	    {
		temp = temp.getDown();
	    }
	}
	
	// find correct starting seat
	for (int i = 0; i < theater.getCols(); i++)
	{
	    // if the col is the correct col, break out of the traversal loop
	    if (temp.getPayload().getSeatLetter() == seat)
	    {
		break;
	    }
	    // col is not correct, so go one seat to the right
	    else
	    {
		temp = temp.getRight();
	    }
	}
	return temp;
    }
    
    // checks the availability of the seats selected
    // returns false if the seats are not available, or true if they are available
    public static boolean checkAvailability(Auditorium theater, int row, char seat, int totalTickets)
    {
	// create temporary node to traverse the auditorium
	Node<Seat> temp = traverseAuditorium(theater, row, seat);
	
	// check the total seat availability
	for (int i = 0; i < totalTickets && temp != null; i++)
	{
	    if (temp.getPayload().getTicketType() == 'A' || temp.getPayload().getTicketType() == 'C' || temp.getPayload().getTicketType() == 'S')
	    {
		// return false if any seat is taken
		return false;
	    }
	    // go to next seat
	    else
	    {
		temp = temp.getRight();
	    }
	}
	// all seats have been checked to be available, so return true
	return true;
    }
    
    // modifies the auditorium to reserve the user's seats
    public static void reserveSeats(Auditorium theater, int row, char seat, int numAdult, int numChild, int numSenior)
    {
	// create temporary node to traverse the auditorium
	Node<Seat> temp = traverseAuditorium(theater, row, seat);
		
	// temp now should contain the correct starting seat, so reserve the seats
	for (int i = 0; i < numAdult; i++, seat++)
	{
	    temp.getPayload().setTicketType('A');
	    temp = temp.getRight();
	}
	
	for (int i = 0; i < numChild; i++, seat++)
	{
	    temp.getPayload().setTicketType('C');
	    temp = temp.getRight();
	}
	
	for (int i = 0; i < numSenior; i++, seat++)
	{
	    temp.getPayload().setTicketType('S');
	    temp = temp.getRight();
	}
    }
    
    // writes the current auditorium to a file
    public static void writeToFile(Auditorium theater) throws IOException
    {
	// file + printwriter objects for output to file
	File outFile = new File("A1.txt");
	PrintWriter output = new PrintWriter(outFile);
	
	// get the first seat for traversal
	Node<Seat> temp = theater.getFirst();
	
	// node for keeping track of the first seat of the current row
	Node<Seat> tempTop = temp;
	
	// print the auditorium
	for (int i = 0; i < theater.getRows(); i++)
	{
	    for (int j = 0; j < theater.getCols(); j++)
	    {
		output.print(temp.getPayload().getTicketType());
		if ((j + 1) % theater.getCols() == 0)
		{
		    output.println();
		}
		// go to the next seat
		temp = temp.getRight(); 
	    }
	    // go to the next row
	    tempTop = tempTop.getDown();
	    temp = tempTop;
	}   
	// close the printwriter
	output.close();
    }
    
    // finds the best available seats in the entire auditorium
    // based on the available seats' distance from the middle of the auditorium
    // returns null if no seats were found, otherwise returns the node pointer of the best first seat available
    public static Node<Seat> bestAvailable(Auditorium theater, int totalTickets)
    {
	double tempXDistance = 0;
	double tempYDistance = 0;
	char tempFirstSeat = 0;
	int tempRow = 0;
	double tempTotalDist = 0;
	double farthestDist = 0;
	char previousFirstSeat = 0;
	int previousRow = 0;
	Node<Seat> bestSeat = null;
	
	// get the number of seats in the row and the number of rows
	int numRows = theater.getRows();
	int numSeats = theater.getCols();
	
	// initialize the farthest distance possible in the entire auditorium
	farthestDist = Math.sqrt((((numSeats / 2) * (numSeats / 2)) + ((numRows / 2) * (numRows / 2))));
	
	// loop for row
	for (int i = 0; i < numRows; i++)
	{
	    
	    // loop for seat/column
	    for (int j = 0; j < numSeats; j++)
	    {
		String s = Character.toString((char) (j + 65));
		char letter = s.charAt(0);
		boolean result = checkAvailability(theater, i + 1, letter, totalTickets);
		// if the seats are available, calculate their distance from the center of the row
		if (result == true)
		{
		    // find x axis distance
		    tempXDistance = Math.abs((((double) numSeats + 1) / 2) - ((double) j + (((double) totalTickets + 1) / 2)));
		    
		    // set column letter
		    tempFirstSeat = letter;
			
		    // then calculate their distance from the center of the column
		    tempYDistance = Math.abs((((double) numRows + 1) / 2) - ((double) i + ((((double) totalTickets) + 1) / 2)));
		
		    // set row number
		    tempRow = i + 1;
			
		    // calculate total distance from center of theater
		    tempTotalDist = Math.sqrt((((tempXDistance) * (tempXDistance)) + ((tempYDistance) * (tempYDistance))));
			
		    // compare the previous distance from the current one
		    if (tempTotalDist < farthestDist)
		    {
			// set the new smallest distance
			farthestDist = tempTotalDist;
			previousFirstSeat = tempFirstSeat;
			previousRow = tempRow;
			bestSeat = traverseAuditorium(theater, previousRow, previousFirstSeat);
		    }
		    // if their distances are equal, find the closest row to the center row
		    else if (farthestDist == tempTotalDist)
		    {
			// find middle row
			double middle = (numRows + 1) / 2;
			double prevRowDist = Math.abs(previousRow - middle);
			double currRowDist = Math.abs(tempRow - middle);
			if (currRowDist < prevRowDist)
			{
			    // set the new smallest distance
			    farthestDist = tempTotalDist;
			    previousFirstSeat = tempFirstSeat;
			    previousRow = tempRow;
			    bestSeat = traverseAuditorium(theater, previousRow, previousFirstSeat);
			}
			// if they are equally distanced from the middle row, find the smallest row number
			else if (currRowDist == prevRowDist)
			{
			    if (tempRow < previousRow)
			    {
				// set the new smallest distance
				farthestDist = tempTotalDist;
				previousFirstSeat = tempFirstSeat;
				previousRow = tempRow;
				bestSeat = traverseAuditorium(theater, previousRow, previousFirstSeat);
			    }
			    else
			    {
				continue;
			    }
			}
			else
			{
			    continue;
			}
		    }
		    else
		    {
			continue;
		    }
		}
		else
		{
			continue;
		}
	    }
	}
	return bestSeat;
    }
    
    // calculates ticket totals and prints a report to the user
    public static void displayReport(Auditorium theater)
    {
	int numAdult = 0;
	int numChild = 0;
	int numSenior = 0;
	int totalSeats = 0;
	
	// temporary node to traverse the theater
	Node<Seat >temp = theater.getFirst();

	// node for keeping track of the first seat of the current row
	Node<Seat> tempTop = temp;

	// calculate the total tickets sold for each category + the total seats in the auditorium
	for (int i = 0; i < theater.getRows(); i++)
	{
	    for (int j = 0; j < theater.getCols(); j++)
	    {
		if (temp.getPayload().getTicketType() == 'A')
		{
		    numAdult++;
		    totalSeats++;
		}
		else if (temp.getPayload().getTicketType() == 'C')
		{
		    numChild++;
		    totalSeats++;
		}
		else if (temp.getPayload().getTicketType() == 'C')
		{
		    numChild++;
		    totalSeats++;
		}
		else if (temp.getPayload().getTicketType() == '.')
		{
		    totalSeats++;
		}
		// go to the next seat
		temp = temp.getRight();
	    }
	    // go to the next row
	    tempTop = tempTop.getDown();
	    temp = tempTop;
	}
	// print the report to the user
	System.out.print("Total Seats: ");
	System.out.println(totalSeats);
	System.out.print("Total Tickets: ");
	System.out.println(numAdult + numChild + numSenior);
	System.out.print("Adult Tickets: ");
	System.out.println(numAdult);
	System.out.print("Child Tickets: ");
	System.out.println(numChild);
	System.out.print("Senior Tickets: ");
	System.out.println(numSenior);
	System.out.print("Total Sales: $");
	System.out.println(String.format("%.2f", ((numAdult * 10.00) + (numChild * 5.00) + (numSenior * 7.50))));
    }
}
