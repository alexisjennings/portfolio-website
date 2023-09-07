// Alexis Jennings

import java.util.concurrent.Semaphore;
import java.util.Queue;
import java.util.LinkedList;

public class DMVSim
{
    // number of customers
    static final int N = 20;
    
    // semaphores for the project
    static Semaphore info_desk = new Semaphore(1, true);
    static Semaphore agent_line = new Semaphore(4, true);
    static Semaphore cust_ready_info_desk = new Semaphore(0, true);
    static Semaphore cust_ready_agent = new Semaphore(0, true);
    static Semaphore cust_waiting = new Semaphore(0, true);
    static Semaphore gave_number = new Semaphore(0, true);
    static Semaphore number_call = new Semaphore(0, true);
    static Semaphore agent = new Semaphore(0, true);
    static Semaphore eye_exam_photo = new Semaphore(0, true);
    static Semaphore being_served = new Semaphore(0, true);
    static Semaphore gave_exam_photo = new Semaphore(0, true);
    static Semaphore cust_left = new Semaphore(0, true);
    static Semaphore mutex1 = new Semaphore(1, true);
    static Semaphore mutex2 = new Semaphore(1, true);
    static Semaphore mutex3 = new Semaphore(1, true);
    static Semaphore[] done = new Semaphore[N];
    static
    {
	for (int i = 0; i < N; i++)
	{
	    done[i] = new Semaphore(0, true);
	}
    }
    
    // number the information desk will assign to customer
    static int count = 1;
    
    // array for keeping track of which agent served which customer
    static int[] cust_arr = new int[N];
    
    // queues for the waiting area and the agent line
    static Queue<Integer> waiting_area_q = new LinkedList<>();
    static Queue<Integer> agent_line_q = new LinkedList<>();

    public static void main(String[] args)
    {
	// constants to declare the max number of customers and agents
	// in the case we want to add or remove customers or agents
	final int max_customers = N;
	final int max_agents = 2;
	
	// create arrays of each thread type and arrays the threads will exist in
	Customer customers[] = new Customer[max_customers];
	Thread customer_thr[] = new Thread[max_customers];
	InfoDesk info_desk = new InfoDesk();
	Thread info_desk_thr = new Thread();
	Announcer announcer = new Announcer();
	Thread announcer_thr = new Thread();
	Agent agents[] = new Agent[max_agents];
	Thread agent_thr[] = new Thread[max_agents];

	// create info desk thread
	info_desk = new InfoDesk();
	info_desk_thr = new Thread(info_desk);
	info_desk_thr.start();
	System.out.println("Information desk created");

	// create announcer thread
	announcer = new Announcer();
	announcer_thr = new Thread(announcer);
	announcer_thr.start();
	System.out.println("Announcer created");
	
	// create agent threads
	for (int i = 0; i < max_agents; i++)
	{
	    agents[i] = new Agent(i);
	    agent_thr[i] = new Thread(agents[i]);
	    agent_thr[i].start();
	    System.out.println("Agent " + i + " created");
	}

	// create customer threads
	for (int i = 0; i < max_customers; i++)
	{
	    customers[i] = new Customer(i);
	    customer_thr[i] = new Thread(customers[i]);
	    customer_thr[i].start();
	    System.out.println("Customer " + i + " created, enters DMV");
	}

	// join customer threads
	for (int i = 0; i < max_customers; i++)
	{
	    try
	    {
		customer_thr[i].join();
		System.out.println("Customer " + i + " was joined");
	    }
	    catch (Exception e)
	    {
		Thread.currentThread().getStackTrace();
	    }
	}
	System.exit(0);
    }
}
