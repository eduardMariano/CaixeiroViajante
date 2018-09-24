package ia.main;

import ia.problema.TravelingSalesman;

@SuppressWarnings("unused")
public class Main {

	public static int CITYS = 15;
	public static int POPULATIONS = 30;

	public static void main(String[] args) {

		TravelingSalesman t = new TravelingSalesman(CITYS, POPULATIONS);
		try {
            t.makeChange();
        }catch (Exception e){
		    e.printStackTrace();
        }

//		final Graph demo = new Graph("Caixeiro Viajante");
//		demo.pack();
//		RefineryUtilities.centerFrameOnScreen(demo);
//		demo.setVisible(true);

	}
}
