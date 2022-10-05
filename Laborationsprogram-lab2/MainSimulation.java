import java.util.*;
import java.io.*;

//Denna klass �rver Global s� att man kan anv�nda time och signalnamnen utan punktnotation


public class MainSimulation extends Global{

    public static void main(String[] args) throws IOException {

    	//Signallistan startas och actSignal deklareras. actSignal �r den senast utplockade signalen i huvudloopen nedan.

    	Signal actSignal;
    	new SignalList();

    	//H�r nedan skapas de processinstanser som beh�vs och parametrar i dem ges v�rden.

    	QS Q1 = new QS();
		QS Q2 = new QS();
		QS Q3 = new QS();

    	Q1.sendTo = null;

    	Gen Generator = new Gen();
    	Generator.lambda = 8; //Generator ska generera nio kunder per sekund
		
		//De genererade kunderna ska skickas till k�systemet QS
    	Generator.sendTo = Q1;

		

    	//H�r nedan skickas de f�rsta signalerna f�r att simuleringen ska komma ig�ng.

    	SignalList.SendSignal(READY, Generator, time);
    	SignalList.SendSignal(MEASURE, Q1, time);
		SignalList.SendSignal(MEASURE, Q2, time);
		SignalList.SendSignal(MEASURE, Q3, time);


    	// Detta �r simuleringsloopen:

    	while (time < 100000){
    		actSignal = SignalList.FetchSignal();
    		time = actSignal.arrivalTime;
    		actSignal.destination.TreatSignal(actSignal);
    	}

    	//Slutligen skrivs resultatet av simuleringen ut nedan:

    	System.out.println("Medelantal kunder i kösystem 1: " + 1.0*Q1.accumulated/Q1.noMeasurements);
		System.out.println("Medelantal kunder i kösystem 2: " + 1.0*Q2.accumulated/Q2.noMeasurements);
		System.out.println("Medelantal kunder i kösystem 3: " + 1.0*Q3.accumulated/Q3.noMeasurements);

    }
}