package ProducerConsumerProblem;

public class Consumer extends Thread {
	private ProducerConsumerImpl pc;
	
	public Consumer(ProducerConsumerImpl sharedObject){
		super("CONSUMER");
		this.pc = sharedObject;
	}

	@Override
	public void run(){
		while(true){
			try{
				pc.get();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
