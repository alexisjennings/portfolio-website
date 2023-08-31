// Alexis Jennings
// aej190000
/*
 * Description:
 * This program's purpose is to simulate a DVD rental system. It takes two files, an inventory log and a 
 * transaction log, as input. The files are processed into a Binary Search Tree in the Main method.
 * Each item in the transaction log has an action keyword, the title of the DVD, and if applicable, the
 * number of copies of the DVD to add or remove. Updating the information of the DVDs relies on searching
 * for the given title in the BST. If a line in the transaction log has an error, it is printed to an error log.
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws IOException
    {
	// scanner for filename input
	Scanner userInput = new Scanner(System.in);
	
	// prompt the user for the inventory filename
	System.out.println("Enter the name of the inventory file:");
	
	// get inventory filename
	String inventoryName = userInput.next();
	
	// prompt the user for the transaction log filename
	System.out.println("Enter the name of transaction log:");
	
	// get transaction log filename
	String transactionLogName = userInput.next();
	
	// close scanner
	userInput.close();
	
	// open the files
	File inventoryFile = new File(inventoryName);
	File transactionLogFile = new File(transactionLogName);
	
	// check if the files can be opened
	if (inventoryFile.canRead() == true && transactionLogFile.canRead() == true)
	{
	    // create the scanner and the tree
	    Scanner scnr1 = new Scanner(inventoryFile);
	    BSTree<DVD> inventory = new BSTree<DVD>();
		
	    while (scnr1.hasNextLine() == true)
	    {
		// process the line in the inventory file
		String line = scnr1.nextLine();
		String arr[] = line.split(",");
		String title = arr[0];
		title = title.replace("\"", "");
		int numAvailable = Integer.parseInt(arr[1]);
		int numRented = Integer.parseInt(arr[2]);
		    
		// if the tree is empty, set the root
		if (inventory.getRoot() == null)
		{
		    inventory.setRoot(new Node<DVD>(new DVD(title, numAvailable, numRented)));
		}
		else
		{
		    inventory.BSTInsert(new Node<DVD>(new DVD(title, numAvailable, numRented)));
		}
	    }
	
	    // create the error log and the new scanner
	    scnr1.close();
	    Scanner scnr2 = new Scanner(transactionLogFile);
	    File errorLog = new File("error.log");
	    PrintWriter toFile = new PrintWriter(errorLog);

	    while (scnr2.hasNextLine() == true)
	    {
		// get the operation keyword
		String op = scnr2.next();

		// Find the title in the tree and increase the number of available copies by the amount listed
		if (op.compareTo("add") == 0)
		{
		    String line = scnr2.nextLine();
		    try
		    {
			// check if the line is formatted properly
			if (line.charAt(1) != '"')
			{
			    throw new Exception();
			}
			else if (line.charAt(line.length() - 3) != '"')
			{
			    throw new Exception();
			}
			// the line is properly formatted for add
			else
			{
			    String arr[] = line.split(",");
			    String title = arr[0];
			    title = title.replace("\"", "");
			    title = title.substring(1);
			    int numToAdd = Integer.parseInt(arr[1]);
			    
			    // search for the title in the tree
			    Node<DVD> result = inventory.BSTSearch(new DVD(title, 0, 0));
			    
			    // if found, update the amount available for the title
			    if (result != null)
			    {
				result.getPayload().setAvailable(result.getPayload().getAvailable() + numToAdd);
			    }
			    // if not found, add the title to the tree
			    else
			    {
				inventory.BSTInsert(new Node<DVD>(new DVD(title, numToAdd, 0)));
			    }
			}
		    }
		    // invalid line, print to the error log
		    catch (Exception e)
		    {
			toFile.println(op + line);
			continue;
		    }
		}
		// Find the title in the tree and reduce the number of available copies by the amount listed
		else if (op.compareTo("remove") == 0)
		{
		    String line = scnr2.nextLine();
		    try
		    {
			// check if the line is formatted properly
			if (line.charAt(1) != '"')
			{
			    throw new Exception();
			}
			else if (line.charAt(line.length() - 3) != '"')
			{
			    throw new Exception();
			}
			// the line is properly formatted for remove
			else
			{
			    String arr[] = line.split(",");
			    String title = arr[0];
			    title = title.replace("\"", "");
			    title = title.substring(1);
			    int numToRemove = Integer.parseInt(arr[1]);
			    
			    // search for the title in the tree
			    Node<DVD> result = inventory.BSTSearch(new DVD(title, 0, 0));
			    
			    // if found, begin the removal process
			    if (result != null)
			    {
				// check if the number to remove is less than the number available
				if (numToRemove > result.getPayload().getAvailable())
				{
				    throw new Exception();
				}
				else
				{
				    // If number available is zero and no copies are rented out, delete the node from the tree
				    if (result.getPayload().getAvailable() == 0 && result.getPayload().getRented() == 0)
				    {
					boolean isRemoved = inventory.BSTRemove(result.getPayload());
					if (isRemoved == true)
					{
					    continue;
					}
					else
					{
					    System.out.println("Title could not be removed");
					}
				    }
				    // remove the copies from the title
				    else
				    {
					result.getPayload().setAvailable(result.getPayload().getAvailable() - numToRemove);
					// If number available is zero and no copies are rented out, delete the node from the tree
					if (result.getPayload().getAvailable() == 0 && result.getPayload().getRented() == 0)
					{
					    boolean isRemoved = inventory.BSTRemove(result.getPayload());
					    if (isRemoved == true)
					    {
						continue;
					    }
					    else
					    {
						System.out.println("Title could not be removed");
					    }
					}
				    }
				}
			    }
			}
		    }
		    // invalid line, print to the error log
		    catch (Exception e)
		    {
			toFile.println(op + line);
			continue;
		    }
		}
		// Reduce available amount by one and increase rented amount by one
		else if (op.compareTo("rent") == 0)
		{
		    String line = scnr2.nextLine();
		    try
		    {
			// check if the line is formatted properly
			if (line.charAt(1) != '"')
			{
			    throw new Exception();
			}
			else if (line.charAt(line.length() - 1) != '"')
			{
			    throw new Exception();
			}
			// the line is properly formatted for rent
			else
			{
			    String title = line;
			    title = title.replace("\"", "");
			    title = title.substring(1);
			    
			    // search for the title in the tree
			    Node<DVD> result = inventory.BSTSearch(new DVD(title, 0, 0));
			    
			    // title found
			    if (result != null)
			    {
				result.getPayload().setAvailable(result.getPayload().getAvailable() - 1);
				result.getPayload().setRented(result.getPayload().getRented() + 1);
			    }
			    else
			    {
				continue;
			    }
			}
		    }
		    // invalid line, print to the error log
		    catch (Exception e)
		    {
			toFile.println(op + line);
			continue;
		    }
		}
		// Increase available amount by one and reduce rented amount by one
		else if (op.compareTo("return") == 0)
		{
		    String line = scnr2.nextLine();
		    try
		    {
			// check if the line is formatted properly
			if (line.charAt(1) != '"')
			{
			    throw new Exception();
			}
			else if (line.charAt(line.length() - 1) != '"')
			{
			    throw new Exception();
			}
			// the line is properly formatted for return
			else
			{
			    String title = line;
			    title = title.replace("\"", "");
			    title = title.substring(1);
			    
			    // search for the title in the tree
			    Node<DVD> result = inventory.BSTSearch(new DVD(title, 0, 0));
			    
			    // title found
			    if (result != null)
			    {
				result.getPayload().setAvailable(result.getPayload().getAvailable() + 1);
				result.getPayload().setRented(result.getPayload().getRented() - 1);
			    }
			    else
			    {
				continue;
			    }
			}
		    }
		    // invalid line, print to the error log
		    catch (Exception e)
		    {
			toFile.println(op + line);
			continue;
		    }
		}
		// the operation keyword is invalid, so write the line to the error log
		else
		{
		    String line = scnr2.nextLine();
		    toFile.println(op + line);
		    continue;
		}
	    }
	    
	    // close the scanner and the transaction file
	    scnr2.close();
	    toFile.close();
	    
	    // print the inventory report
	    inventory.BSTPrintInorder(inventory.getRoot());
	}
	else
	{
	    throw new IOException();
	}
    }
}
