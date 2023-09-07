// Alexis Jennings
// aej190000
// CS 4348.001
// Project 2

public class InfoDesk implements Runnable
{
    // code for what the information desk thread will do
    public void run()
    {
	while (true)
	{
	    try
	    {
		Project2.cust_ready_info_desk.acquire();// wait for a customer to be ready at info desk
		Project2.gave_number.release();		// signal that a number has been assigned to customer
	    }
	    catch (Exception e)
	    {
		Thread.currentThread().getStackTrace();
	    }
	}
    }
}
