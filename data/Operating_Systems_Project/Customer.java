// Alexis Jennings
// aej190000
// CS 4348.001
// Project 2

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
	    Project2.info_desk.acquire();		// wait for the info desk to be available
	    Project2.cust_ready_info_desk.release();	// signal to the info desk that a customer has arrived	
	    
	    // critical section
	    Project2.mutex1.acquire();
	    this.dmv_num = Project2.count;		// assign the customer a number
	    Project2.count++;
	    Project2.mutex1.release();
	    
	    Project2.gave_number.acquire();		// wait for the info desk to give the number to the customer
	    receive_number();				// print that customer has received a number and enters waiting area
	    Project2.info_desk.release();		// signal that the info desk is available
	    
	    // critical section
	    Project2.mutex2.acquire();
	    Project2.waiting_area_q.add(this.dmv_num);	// enqeue the customer into the waiting area
	    Project2.mutex2.release();
	    
	    Project2.cust_waiting.release();		// signal that there is a customer waiting in waiting area
	    Project2.number_call.acquire();		// wait for announcer to call number
	    enter_agent_line();				// print that customer has moved to agent line
	    
	    // critical section
	    Project2.mutex3.acquire();
	    Project2.agent_line_q.add(this.id);		// enqeue the customer into the agent line
	    Project2.mutex3.release();
	    
	    Project2.cust_ready_agent.release();	// signal that customer is ready for agent
	    Project2.agent.acquire();			// wait for agent to be ready
	    Project2.agent_line.release();		// agent is ready, so signal that a spot opened in agent line
	    being_served();				// print that customer is being served
	    Project2.being_served.release();		// signal that customer has printed they are being served
	    Project2.eye_exam_photo.acquire();		// wait for eye exam and photo to be given
	    complete_exam_photo();			// print that customer has completed eye exam and photo
	    Project2.gave_exam_photo.release();		// signal that customer has printed they have done exam and photo			
	    Project2.done[id].acquire();		// wait for the agent to give the license
	    leave_dmv();				// print that customer has received license and left dmv
	    Project2.cust_left.release();		// signal that customer has left
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
	System.out.println("Customer " + this.id + " is being served by Agent " + Project2.cust_arr[this.id]);
	return;
    }
    
    void complete_exam_photo()
    {
	System.out.println("Customer " + this.id + " completes photo and eye exam for Agent " + Project2.cust_arr[this.id]);
	return;
    }
    
    void leave_dmv()
    {
	System.out.println("Customer " + this.id + " gets license and departs");
	return;
    }
}
