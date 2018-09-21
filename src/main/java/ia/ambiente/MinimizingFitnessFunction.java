package ia.ambiente;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

public class MinimizingFitnessFunction extends FitnessFunction{

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
        return Math.abs(city1.intValue() - city2.intValue());
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
