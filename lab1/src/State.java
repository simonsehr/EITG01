import java.util.*;

class State extends GlobalSimulation{
	
	public int queueA = 0, queueB = 0, accumulated = 0, noMeasurements = 0, accumulatedB = 0;
	
	private EventList myEventList;
	
	Random slump = new Random();
	
	State(EventList x){
		myEventList = x;
	}
	
	private void InsertEvent(int event, double timeOfEvent){
		myEventList.InsertEvent(event, timeOfEvent);
	}
	
	
	public void TreatEvent(Event x){
		switch (x.eventType){
			case ARRIVAL_A:
				arrivalA();
				break;
			case ARRIVAL_B:
				arrivalB();
				break;
			case READY_A:
				readyA();
				break;
			case READY_B:
				readyB();
				break;
			case MEASURE:
				measure();
				break;
		}
	}
	
	private double generateMean(double mean){
		return 2*mean*slump.nextDouble();
	}
	
	private void arrivalA(){
		if ((queueA + queueB) == 0){
			InsertEvent(READY_A, time + generateMean(0.002));
		}
		
		queueA++;
		InsertEvent(ARRIVAL_A, time + generateMean(1.0/150));
			
	}
	
	private void arrivalB(){
		if ((queueA + queueB) == 0){
			InsertEvent(READY_B, time + generateMean(0.004));
		}
		queueB++;
	}

	
	private void readyA(){
		queueA--;
		if(queueB > 0){
			InsertEvent(READY_B, time + generateMean(0.004));
		}
		else if(queueA > 0){
			InsertEvent(READY_A, time + generateMean(0.002));
		}
		
		InsertEvent(ARRIVAL_B, time + 1);
		
	}
	
	private void readyB(){
		queueB--;
		
		if(queueB > 0){
			InsertEvent(READY_B, time + generateMean(0.004));
		}
		else if(queueA > 0){
			InsertEvent(READY_A, time + generateMean(0.002));
		}
	}
	
	
	private void measure(){
		accumulated = accumulated + queueA + queueB;
		accumulatedB += queueB;
		noMeasurements++;
		InsertEvent(MEASURE, time + 0.1);
	}
}
