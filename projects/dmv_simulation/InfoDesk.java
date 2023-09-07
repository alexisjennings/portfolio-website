// Alexis Jennings

public class InfoDesk implements Runnable
{
    // code for what the information desk thread will do
    public void run()
    {
	while (true)
	{
	    try
	    {
		DMVSim.cust_ready_info_desk.acquire();// wait for a customer to be ready at info desk
		DMVSim.gave_number.release();		// signal that a number has been assigned to customer
	    }
	    catch (Exception e)
	    {
		Thread.currentThread().getStackTrace();
	    }
	}
    }
}
