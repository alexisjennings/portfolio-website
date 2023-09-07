// Alexis Jennings

public class Agent implements Runnable
{
    int id;		// agent's personal number
    int cust_id;	// customer that the agent is serving's personal number
    
    // constructor
    Agent(int id) 
    {
       this.id = id;
    }
    
    // code for what the agent threads will do
    public void run()
    {
	while (true)
	{
	    try
	    {
		DMVSim.cust_ready_agent.acquire();		// wait for customer to be ready for agent
		
		// critical section
		DMVSim.mutex3.acquire();
		cust_id = DMVSim.agent_line_q.remove();	// dequeue customer from agent line
		serve_cust();					// print that the agent is serving customer
		DMVSim.agent.release();			// signal that the agent is serving customer
		DMVSim.cust_arr[cust_id] = this.id;		// store agent's number for customer use
		DMVSim.mutex3.release();
		
		DMVSim.being_served.acquire();		// wait for customer to print that they are being served
		give_eye_exam_photo();				// print that the agent is giving customer eye exam and photo
		DMVSim.eye_exam_photo.release();		// signal that the agent is giving customer eye exam and photo
		DMVSim.gave_exam_photo.acquire();		// wait for customer to print that they completed eye exam and photo
		give_license();					// print that the agent gave customer their license
		DMVSim.done[cust_id].release();		// signal that the customer has received license and may leave
		DMVSim.cust_left.acquire();			// wait for customer to leave before serving new customer
	    }
	    catch (Exception e)
	    {
		Thread.currentThread().getStackTrace();
	    }
	}
    }
    
    void serve_cust()
    {
	System.out.println("Agent " + this.id + " is serving Customer " + cust_id);
	return;
    }
    
    void give_eye_exam_photo()
    {
	System.out.println("Agent " + this.id + " asks Customer " + cust_id + " to take photo and eye exam");
	return;
    }
    
    void give_license()
    {
	System.out.println("Agent " + this.id + " gives license to Customer " + cust_id);
	return;
    }
}
