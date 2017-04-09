package ProducerConsumerProblem;

import java.util.*;
import java.util.concurrent.locks.*;


public class ProducerConsumerImpl {
	//Data members of the production line 
	private static final int CAPACITY = 10; 
	private final Queue<Integer> queue = new LinkedList<Integer>(); //Buffer for the products
	private final Random productID = new Random();

	//Lock and Condition variables
    private final Lock aLock = new ReentrantLock(); 
    private final Condition bufferNotFull = aLock.newCondition(); 
    private final Condition bufferNotEmpty = aLock.newCondition();

    
    // Put method to add items from producer thread to queue
    public void put() throws InterruptedException { 
    	aLock.lock(); // gets the lock to queue buffer
    	try { 	
    		String currentThreadName =  Thread .currentThread().getName();
    		while (queue.size() == CAPACITY) { 
		    		System.out.println(currentThreadName + " : Buffer is full, waiting");
		    		bufferNotEmpty.await();  /// Wait till the queue slot is empty
    		} 
    		
	    	int number = productID.nextInt();//Randomly generated product ID
	    	
	    	boolean isAdded = queue.offer(number); //Note to self: Better than add as it only adds to queue when possible without violation of queue limit
	    	if (isAdded) { 
		    		System.out.printf(currentThreadName+ " added "+number +" into queue \n"); 
		    		// signal consumer thread that, buffer has element now 
		    		System.out.println(currentThreadName + " : Signalling all consumer that buffer is no more empty now"); 
		    		bufferNotFull.signalAll(); // Signals all consumer threads
	    	} 
    	} finally { 
    		aLock.unlock(); //Release the lock to the queue buffer
    	} 
    } 

    // Get method for the consumer thread to remove item from queue
    
	public void get() throws InterruptedException { 
		aLock.lock(); // gets the lock to queue buffer
		try { 
			String currentThreadName =  Thread .currentThread().getName();
			while (queue.size() == 0) { 
				System.out.println(currentThreadName + " : Buffer is empty, waiting"); 
				bufferNotFull.await(); 
			} 
			Integer value = queue.poll(); 
			if (value != null) { 
				System.out.printf(currentThreadName + " consumed "+value+" from queue \n"); 
				// signal producer thread that, buffer may be empty now 
				System.out.println(currentThreadName + " : Signalling that buffer may be empty  now"); 
				bufferNotEmpty.signalAll(); 
			 } 
		} finally { 
			aLock.unlock(); //Release the lock to the queue buffer
		} 
	}

}
   