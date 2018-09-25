package ia.ambiente;

import ia.problema.Pontos;
import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

import java.util.List;
import java.util.Map;

public class MinimizingFitnessFunction extends FitnessFunction{

    public static List<Pontos> distances;
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
        Pontos p1 = distances.get(c1.intValue());
        Pontos p2 = distances.get(c2.intValue());
        double dx = Math.pow(p2.getX() - p1.getX(),2);
        double dy = Math.pow(p2.getY() - p1.getY(),2);
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
