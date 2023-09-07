// Alexis Jennings

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
		DMVSim.cust_waiting.acquire();		// wait for a customer to be waiting in waiting area
		DMVSim.agent_line.acquire();			// wait for an agent line spot to open
		
		// critical section
		DMVSim.mutex2.acquire();
		cust_dmv_num = DMVSim.waiting_area_q.remove();// spot is open, dequeue customer from waiting area
		DMVSim.mutex2.release();
		
		call_number();					// print that the announcer called a number
		DMVSim.number_call.release();			// signal that a number has been called
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
