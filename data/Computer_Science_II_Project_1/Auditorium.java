// Alexis Jennings
// aej190000
/*
 * Description:
 * This class serves to represent an individual auditorium in a movie theater. The auditorium is composed of
 * a 2D grid of Node<Seat> objects, representing the seats, using a 2D doubly linked list. A variable of type
 * Node<Seat> is used to store the first seat in the auditorium, being the first seat of the first row. This
 * variable also serves as the head of the doubly linked list of seats. There are also variables to store the
 * number of rows and number of columns in the auditorium, so that no customer may reserve a seat in a row or
 * column greater than the number of rows or columns.
 * 
 * The overloaded constructor has 2 phases:
 * 	1. The first phase opens and a file that was passed in from the constructor call. This file contains
 * information on the seats in the auditorium, including the number of rows and columns, and the ticket
 * type occupied by each seat.
 * 	2. The file is read line by line, parsing for seat information. It is assumed that all columns have the
 * same number of seats, so the number of rows can be obtained by finding the length of the first line.
 * After setting the first seat, a for loop stores the rest of the seats and creates a new Node<Seat> object
 * for each one. Because the first row is processed differently than the rest of the rows, a separate for loop
 * is used for processing the remaining rows. This is due to needing to set the first seat pointer.
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Auditorium
{
    // member variables
    private Node<Seat> first;
    private int numRows;
    private int numCols;
    
    // default constructor
    public Auditorium()
    {
	this.first = null;
    }
    
    // overloaded constructor
    public Auditorium(String filename) throws IOException
    {
	// variables for creating the nodes
	File inFile = new File(filename);
	int row = 1;
	char seat = 'A';
	String line;
	
	try
	{
		    // check if the file can be opened
		    if (inFile.canRead() == true)
		    {
			// create scanner for file input
			Scanner input = new Scanner(inFile);
			// read first line of file
			line = input.nextLine();
			// get the length of the row
			int rowLength = line.length();
			// set the auditorium's first node
			setFirst(new Node<Seat>(new Seat(row, seat, line.charAt(0))));
			// set prev, top, and newTop to the first node
			Node<Seat> prev = getFirst();   // for keeping track of the previous node to the current node
			Node<Seat> top = getFirst();    // for keeping track of the node above the current node
			Node<Seat> newTop = getFirst(); // for keeping track of the first seat of the previous row
			// go to the next seat letter
			seat++;
			// loop for creating the first row of seats
			for (int i = 1; i < rowLength; i++)
			{
			    // create the next node
			    Node<Seat> newNode = new Node<Seat>(new Seat(row, seat, line.charAt(i)));
			    // the new node's left pointer will point to the previous node
			    newNode.setLeft(prev);
			    // the previous node's right pointer will point to the new node
			    prev.setRight(newNode);
			    // set the previous node to the new node for the next iteration
			    prev = newNode;
			    // go to the next seat letter
			    seat++;
			}
			// at this point, the first row has its left and right pointers
			// go to the next row
			row++;
			// loop for creating the remaining rows
			while (input.hasNextLine() == true)
			{
			    // reset first seat to A for beginning of the row
			    seat = 'A';
			    // read the next line
			    line = input.nextLine();
			    // loop for traversing the row
			    for (int i = 0; i < rowLength; i++)
			    {
				// create the next node
				Node<Seat> newNode = new Node<Seat>(new Seat(row, seat, line.charAt(i)));
				// for setting up the first seat of the row
				if (i == 0)
				{
				    newNode.setUp(newTop);
				    newTop.setDown(newNode);
				    top = newTop.getRight();
				    newTop = newNode;
				    prev = newNode;
				}
				// for the rest of the seats
				else
				{
				    newNode.setLeft(prev);
				    newNode.setUp(top);
				    top.setDown(newNode);
				    prev.setRight(newNode);
				    prev = newNode;
				    if (i != rowLength - 1)
				    {
					top = top.getRight();
				    }
				}
				// go to the next seat
				seat++;
			    }
			    // go to the next row
			    row++;
			}
			// close the scanner
			input.close();
		    }
		    else
		    {
			throw new IOException();
		    }
	}
	catch (IOException e)
	{
	    System.out.println("File cannot be opened");
	}
    }
    
    // accessors
    public Node<Seat> getFirst()
    {
	return this.first;
    }
    
    public int getRows()
    {
	return this.numRows;
    }
    
    public int getCols()
    {
	return this.numCols;
    }
    
    // mutators
    public void setFirst(Node<Seat> newFirst)
    {
	this.first = newFirst;
    }
    
    public void setRows(int newRows)
    {
	this.numRows = newRows;
    }
    
    public void setCols(int newCols)
    {
	this.numCols = newCols;
    }
}
