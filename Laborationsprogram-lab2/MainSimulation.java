
import java.util.*;
import java.io.*;

//Denna klass �rver Global s� att man kan anv�nda time och signalnamnen utan punktnotation


public class MainSimulation extends Global{

    public static void main(String[] args) throws IOException {

		Random slump = new Random(5);
		


    	//Signallistan startas och actSignal deklareras. actSignal �r den senast utplockade signalen i huvudloopen nedan.

    	Signal actSignal;
    	new SignalList();

    	//H�r nedan skapas de processinstanser som beh�vs och parametrar i dem ges v�rden.

		//Uppgift e
		
		/*QS Q1 = new QS();
		QS Q2 = new QS();
		QS Q3 = new QS();*/

    	QS Q1 = new QS();
		QS Q2 = new QS();
		QS Q3 = new QS();
		QS Q4 = new QS();
		QS Q5 = new QS();

    	//Q1.sendTo = null;

    	Gen Generator = new Gen();
    	Generator.lambda = 45; //Generator ska generera nio kunder per sekund
		
		//De genererade kunderna ska skickas till k�systemet QS

		//Uppgift e
    	/*Generator.sendTo = Q1;
		Q1.sendTo = Q2;
		Q2.sendTo = Q3;*/


    	//H�r nedan skickas de f�rsta signalerna f�r att simuleringen ska komma ig�ng.

    	SignalList.SendSignal(READY, Generator, time);
    	SignalList.SendSignal(MEASURE, Q1, time);
		SignalList.SendSignal(MEASURE, Q2, time);
		SignalList.SendSignal(MEASURE, Q3, time);
		SignalList.SendSignal(MEASURE, Q4, time);
		SignalList.SendSignal(MEASURE, Q5, time);



    	// Detta �r simuleringsloopen:
		//int Queue = 1;
    	while (time < 100000){
    		actSignal = SignalList.FetchSignal();
    		time = actSignal.arrivalTime;
    		actSignal.destination.TreatSignal(actSignal);


		//i

			/*int Queue = slump.nextInt(5)+1;

			if(Queue == 1){
				Generator.sendTo = Q1;
			}
			if(Queue == 2){
				Generator.sendTo = Q2;
			}
			if(Queue == 3){
				Generator.sendTo = Q3;
			}
			if(Queue == 4){
				Generator.sendTo = Q4;
			}
			if(Queue == 5){
				Generator.sendTo = Q5;
			}*/


		//ii

			/*if(Queue == 1){
				Generator.sendTo = Q1;
			}
			if(Queue == 2){
				Generator.sendTo = Q2;
			}
			if(Queue == 3){
				Generator.sendTo = Q3;
			}
			if(Queue == 4){
				Generator.sendTo = Q4;
			}
			if(Queue == 5){
				Generator.sendTo = Q5;
			}
			Queue++;

			if(Queue == 6){
				Queue = 1;
			}*/

		//iii
			int max  = Integer.MAX_VALUE;

			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("kö1", Q1.numberInQueue);
			map.put("kö2", Q2.numberInQueue);
			map.put("kö3", Q3.numberInQueue);
			map.put("kö4", Q4.numberInQueue);
			map.put("kö5", Q5.numberInQueue);

			String QueueKey = null;

			for(String key : map.keySet()){
				if(map.get(key) < max){
					max = map.get(key);
					QueueKey = key;
				}
			}

			if(QueueKey.equals("kö1")){
				Generator.sendTo = Q1;
			}
			if(QueueKey.equals("kö2")){
				Generator.sendTo = Q2;
			}
			if(QueueKey.equals("kö3")){
				Generator.sendTo = Q3;
			}
			if(QueueKey.equals("kö4")){
				Generator.sendTo = Q4;
			}
			if(QueueKey.equals("kö5")){
				Generator.sendTo = Q5;
			}


    	}

    	//Slutligen skrivs resultatet av simuleringen ut nedan:

    	System.out.println("Medelantal kunder i kösystem 1: " + 1.0*Q1.accumulated/Q1.noMeasurements);
		System.out.println("Medelantal kunder i kösystem 2: " + 1.0*Q2.accumulated/Q2.noMeasurements);
		System.out.println("Medelantal kunder i kösystem 3: " + 1.0*Q3.accumulated/Q3.noMeasurements);
		System.out.println("Medelantal kunder i kösystem 4: " + 1.0*Q4.accumulated/Q4.noMeasurements);
		System.out.println("Medelantal kunder i kösystem 5: " + 1.0*Q5.accumulated/Q5.noMeasurements);

    }
}