package ia.main;

import ia.problema.TravelingSalesman;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@SuppressWarnings("unused")
public class Main {

	public static int CITYS = 8;
	public static int POPULATIONS = 30;

	public static void main(String[] args) throws IOException {

        FileWriter arq = new FileWriter("results1.txt");
        PrintWriter gravarArq = new PrintWriter(arq);
		TravelingSalesman t = new TravelingSalesman(CITYS, POPULATIONS);
		try {
            t.makeChange(gravarArq);
        }catch (Exception e){
		    e.printStackTrace();
        }
        arq.close();

	}
}
