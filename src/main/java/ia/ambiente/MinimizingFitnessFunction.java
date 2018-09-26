package ia.ambiente;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MinimizingFitnessFunction extends FitnessFunction {

    public static List<Point> distances;
    private Integer MINWAY = null;
    public static IChromosome chromo = null;

    protected double evaluate(IChromosome chromosome) {

        int totalDistance = getTotalOfDistance(chromosome);
        if (this.MINWAY != null) {
            if (this.MINWAY > totalDistance) {
                this.MINWAY = totalDistance;
                this.chromo = chromosome;
            }
        } else {
            this.MINWAY = totalDistance;
            this.chromo = chromosome;
        }

        return (double) this.MINWAY;

    }

    public static int getDistanceAtGene(IChromosome a_potentialSolution, int a_position, int a_position_next) {
        Integer city1 = (Integer) a_potentialSolution.getGene(a_position).getAllele();
        Integer city2 = (Integer) a_potentialSolution.getGene(a_position_next).getAllele();
        return Math.abs(getValueAtGene(city1, city2));
    }

    public static int getValueAtGene(Integer c1, Integer c2) {
        Point p1 = distances.get(c1.intValue());
        Point p2 = distances.get(c2.intValue());
        double dx = Math.pow(p2.getX() - p1.getX(), 2);
        double dy = Math.pow(p2.getY() - p1.getY(), 2);
        return (int) Math.floor(Math.sqrt(dx + dy));
    }

    public static int getTotalOfDistance(IChromosome a_potentialsolution) {
        int totalDistance = 0;

        int numberOfGenes = a_potentialsolution.size();
        for (int i = 0; i < numberOfGenes; i++) {
            if (i != (numberOfGenes - 1)) {
                totalDistance += getDistanceAtGene(a_potentialsolution, i, (i + 1));
            } else {
                totalDistance += getDistanceAtGene(a_potentialsolution, 0, i);
            }
        }

        return totalDistance;
    }

    public static List<Point> getBestSolution(IChromosome a_solution){
        List<Point> pontos = new ArrayList<>();
        int numberOfGenes = a_solution.size();
        for (int i = 0; i < numberOfGenes; i++) {
            Integer p = (Integer) a_solution.getGene(i).getAllele();
            pontos.add(distances.get(p.intValue()));
        }
        return pontos;
    }

    public static List<Integer> getBestSolutionPosition(IChromosome a_solution){
        List<Integer> pontos = new ArrayList<>();
        int numberOfGenes = a_solution.size();
        int distance = 0;
        for (int i = 0; i < numberOfGenes; i++) {
            Integer p = (Integer) a_solution.getGene(i).getAllele();
            pontos.add(p.intValue());
        }

        return pontos;
    }

    public static List<Integer> getBestSolutionDistance(IChromosome a_solution){
        List<Integer> pontos = new ArrayList<>();
        int numberOfGenes = a_solution.size();
        int distance = 0;
        for (int i = 0; i < numberOfGenes; i++) {
            if (i != (numberOfGenes - 1)) {
                Integer city1 = (Integer) a_solution.getGene(i).getAllele();
                Integer city2 = (Integer) a_solution.getGene((i+1)).getAllele();
                distance = Math.abs(getValueAtGene(city1, city2));
            } else {
                Integer city1 = (Integer) a_solution.getGene(i).getAllele();
                Integer city2 = (Integer) a_solution.getGene(0).getAllele();
                distance = Math.abs(getValueAtGene(city1, city2));
            }
            pontos.add(distance);
        }

        return pontos;
    }

}
