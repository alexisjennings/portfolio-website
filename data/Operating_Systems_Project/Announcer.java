// Alexis Jennings
// aej190000
// CS 4348.001
// Project 2

public class Announcer implements Runnable
{
    // variable that keeps track of the customer's assigned number in order to call it
    int cust_dmv_num;
    
    // code for what the announcer thread will do
    public void run()
    {
	while (true)
	{
	    try
	    {
		Project2.cust_waiting.acquire();		// wait for a customer to be waiting in waiting area
		Project2.agent_line.acquire();			// wait for an agent line spot to open
		
		// critical section
		Project2.mutex2.acquire();
		cust_dmv_num = Project2.waiting_area_q.remove();// spot is open, dequeue customer from waiting area
		Project2.mutex2.release();
		
		call_number();					// print that the announcer called a number
		Project2.number_call.release();			// signal that a number has been called
	    }
	    catch (Exception e)
	    {
		Thread.currentThread().getStackTrace();
	    }
	}
    }
    
    void call_number()
    {
	System.out.println("Announcer calls number " + cust_dmv_num);
	return;
    }
}
