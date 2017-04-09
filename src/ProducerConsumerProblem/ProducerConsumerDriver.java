package ProducerConsumerProblem;

public class ProducerConsumerDriver {

	public static void main(String[] args) { 
		// Object on which producer and consumer thread will operate 
		ProducerConsumerImpl sharedObject = new ProducerConsumerImpl(); 
		// creating producer and consumer threads 
		Producer p = new Producer(sharedObject); 
		Consumer c = new Consumer(sharedObject); 
		// starting producer and consumer threads 
		p.start(); 
		c.start(); 
	}
}

		


