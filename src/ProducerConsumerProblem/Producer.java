package ProducerConsumerProblem;

public class Producer extends Thread{
	private ProducerConsumerImpl pc;
	
	public Producer(ProducerConsumerImpl sharedObject){
		super("PRODUCER");
		this.pc = sharedObject;
	}
	
	@Override
	public void run(){
		while(true){
			try{
				pc.put();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
}
