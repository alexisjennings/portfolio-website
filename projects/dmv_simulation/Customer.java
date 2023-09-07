// Alexis Jennings

public class Customer implements Runnable
{
    int id;		// customer's personal number
    int dmv_num;	// number the customer is given at the information desk
    
    // constructor
    Customer(int id) 
    {
       this.id = id;
    }
    
    // code for what the customer threads will do
    public void run()
    {
	try
	{
	    DMVSim.info_desk.acquire();		// wait for the info desk to be available
	    DMVSim.cust_ready_info_desk.release();	// signal to the info desk that a customer has arrived	
	    
	    // critical section
	    DMVSim.mutex1.acquire();
	    this.dmv_num = DMVSim.count;		// assign the customer a number
	    DMVSim.count++;
	    DMVSim.mutex1.release();
	    
	    DMVSim.gave_number.acquire();		// wait for the info desk to give the number to the customer
	    receive_number();				// print that customer has received a number and enters waiting area
	    DMVSim.info_desk.release();		// signal that the info desk is available
	    
	    // critical section
	    DMVSim.mutex2.acquire();
	    DMVSim.waiting_area_q.add(this.dmv_num);	// enqeue the customer into the waiting area
	    DMVSim.mutex2.release();
	    
	    DMVSim.cust_waiting.release();		// signal that there is a customer waiting in waiting area
	    DMVSim.number_call.acquire();		// wait for announcer to call number
	    enter_agent_line();				// print that customer has moved to agent line
	    
	    // critical section
	    DMVSim.mutex3.acquire();
	    DMVSim.agent_line_q.add(this.id);		// enqeue the customer into the agent line
	    DMVSim.mutex3.release();
	    
	    DMVSim.cust_ready_agent.release();	// signal that customer is ready for agent
	    DMVSim.agent.acquire();			// wait for agent to be ready
	    DMVSim.agent_line.release();		// agent is ready, so signal that a spot opened in agent line
	    being_served();				// print that customer is being served
	    DMVSim.being_served.release();		// signal that customer has printed they are being served
	    DMVSim.eye_exam_photo.acquire();		// wait for eye exam and photo to be given
	    complete_exam_photo();			// print that customer has completed eye exam and photo
	    DMVSim.gave_exam_photo.release();		// signal that customer has printed they have done exam and photo			
	    DMVSim.done[id].acquire();		// wait for the agent to give the license
	    leave_dmv();				// print that customer has received license and left dmv
	    DMVSim.cust_left.release();		// signal that customer has left
	}
	catch (Exception e)
	{
	    Thread.currentThread().getStackTrace();
	}
    }
    
    void receive_number()
    {
	System.out.println("Customer " + this.id + " gets number " + this.dmv_num + ", enters waiting room");
	return;
    }
    
    void enter_agent_line()
    {
	System.out.println("Customer " + this.id + " moves to agent line");
	return;
    }
    
    void being_served()
    {
	System.out.println("Customer " + this.id + " is being served by Agent " + DMVSim.cust_arr[this.id]);
	return;
    }
    
    void complete_exam_photo()
    {
	System.out.println("Customer " + this.id + " completes photo and eye exam for Agent " + DMVSim.cust_arr[this.id]);
	return;
    }
    
    void leave_dmv()
    {
	System.out.println("Customer " + this.id + " gets license and departs");
	return;
    }
}
