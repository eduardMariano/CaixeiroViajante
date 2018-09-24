package ia.ambiente;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

import java.util.ArrayList;
import java.util.Map;

public class MinimizingFitnessFunction extends FitnessFunction{

    public static ArrayList<Map> distances;
    private Integer MINWAY = null;

    protected double evaluate(IChromosome chromosome) {

        int totalDistance = getTotalOfDistance(chromosome);
        if(this.MINWAY != null){
            if(this.MINWAY > totalDistance){
                this.MINWAY = totalDistance;
            }
        }else {
            this.MINWAY = totalDistance;
        }

        return (double) this.MINWAY;

    }

    public static int getDistanceAtGene(IChromosome a_potentialSolution, int a_position, int a_position_next ) {
        Integer city1 = (Integer) a_potentialSolution.getGene(a_position).getAllele();
        Integer city2 = (Integer) a_potentialSolution.getGene(a_position_next).getAllele();
        return Math.abs(getValueAtGene(city1, city2));
    }

    public static int getValueAtGene(Integer c1, Integer c2){
        Map<Integer, Integer> p1 = distances.get(c1.intValue());
        Map<Integer, Integer> p2 = distances.get(c2.intValue());
        double dx = Math.pow(p2.get(0) - p1.get(0),2);
        double dy = Math.pow(p2.get(1) - p1.get(1),2);
        return (int) Math.sqrt(dx+dy);
    }

    public static int getTotalOfDistance(IChromosome a_potentialsolution ) {
        int totalDistance = 0;

        int numberOfGenes = a_potentialsolution.size();
        for( int i = 0; i < numberOfGenes; i++ ) {
            if(i != (numberOfGenes - 1)) {
                totalDistance += getDistanceAtGene(a_potentialsolution, i, (i + 1));
            }else{
                totalDistance += getDistanceAtGene(a_potentialsolution, 0, i);
            }
        }

        return totalDistance;
    }
}
