package ia.problema;

import org.jgap.*;
import org.jgap.impl.*;

import ia.ambiente.MinimizingFitnessFunction;

import java.io.PrintWriter;
import java.util.*;

import java.awt.Point;

public class TravelingSalesman {

    private int totalCity;
    private int populations;
    private int numEvolutions = 10000;
    private List<Point> distances = new ArrayList<>();
    private List<Point> visits = new ArrayList<>();

    public TravelingSalesman(int totalCity, int populations) {
        this.totalCity = totalCity;
        this.populations = populations;
    }

    public void makeChange(PrintWriter gravarArq) throws Exception {

        Configuration conf = new DefaultConfiguration();
        conf.setPreservFittestIndividual(true);
        conf.addNaturalSelector(new WeightedRouletteSelector(conf), true);
        conf.addNaturalSelector(new WeightedRouletteSelector(conf), false);
        conf.setAlwaysCaculateFitness(true);
        FitnessFunction myFunc = new MinimizingFitnessFunction();
        conf.setFitnessFunction(myFunc);

        Gene[] sampleGenes = new Gene[this.totalCity];
        IChromosome[] sampleChromossomes = new IChromosome[this.populations];

        for(int i = 0; i < this.totalCity; i++){
            IntegerGene gene = new IntegerGene(conf, i, i);
            gene.setAllele(i);
            sampleGenes[i] = gene;
            distances.add(getDistance());
        }

        printDistances(distances, gravarArq);
        gravarArq.println("");
        gravarArq.println("");

        MinimizingFitnessFunction.distances = distances;

        Chromosome sampleChromosome = new Chromosome(conf, sampleGenes);

        conf.setSampleChromosome(sampleChromosome);

        conf.setPopulationSize(this.populations);

        conf.getGeneticOperators().clear();
        conf.addGeneticOperator(new CrossoverOperator(conf, 75));
        conf.addGeneticOperator(new SwappingMutationOperator(conf,15));

        Genotype population = Genotype.randomInitialGenotype(conf);

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < this.numEvolutions; i++) {
            if (!uniqueChromosomes(population.getPopulation())) {
                throw new RuntimeException("Inválido o estado da geração "+ i);
            }
            population.evolve();
            population.applyGeneticOperators();
        }
        long endTime = System.currentTimeMillis();

        IChromosome bestSolutionSoFar = MinimizingFitnessFunction.chromo;
        System.out.println("A melhor solução pra fitness foi " + bestSolutionSoFar.getFitnessValue());
        List<Point> bestPontos = MinimizingFitnessFunction.getBestSolution(bestSolutionSoFar);
        List<Integer> positions = MinimizingFitnessFunction.getBestSolutionPosition(bestSolutionSoFar);
        List<Integer> bestDistance = MinimizingFitnessFunction.getBestSolutionDistance(bestSolutionSoFar);
        printBestDistances(bestPontos, positions, bestDistance, gravarArq);
        System.out.println("O tempo total das evoluções foi : " + ( endTime - startTime) + " ms");
        System.out.println("");
        System.out.println("");

    }

    private static boolean uniqueChromosomes(Population a_pop) {

        for(int i=0;i<a_pop.size()-1;i++) {
            IChromosome c = a_pop.getChromosome(i);
            for(int j=i+1;j<a_pop.size();j++) {
                IChromosome c2 =a_pop.getChromosome(j);
                if (c == c2) {
                    return false;
                }
            }
        }
        return true;
    }


    private Point getDistance() {
        Point pontos = null;

        if(this.visits == null){
            pontos = new Point (aleatoriar(), aleatoriar());
            this.visits.add(pontos);
        }else{
            if(isRepeat(pontos)){
                return getDistance();
            }else{
                pontos = new Point (aleatoriar(), aleatoriar());
                this.visits.add(pontos);
            }
        }

        return pontos;
    }

    public boolean isRepeat(Point pontos){
        return this.visits.contains(pontos);
    }

    public int aleatoriar() {
        Random random = new Random();
        return random.nextInt((30 - 0) + 1) + 0;
    }

    public void printDistances(List<Point> pontos, PrintWriter gravarArq){
        System.out.println("Pontos Inicial : ");
        for (int i = 0; i < pontos.size(); i++) {
            gravarArq.println("array(\"x\" => "+pontos.get(i).x+", \"y\" => "+pontos.get(i).y+", \"label\" => \"Cidade "+i+"\"),");
            System.out.println("Cidade "+i+" -> Posicão X : "+pontos.get(i).x+" Posição Y : "+pontos.get(i).y);
        }
        System.out.println("######################################################");
    }

    public void printBestDistances(List<Point> pontos, List<Integer> position, List<Integer> distance, PrintWriter gravarArq){
        System.out.println("Pontos Inicial : ");
        int totalDistance = 0;
        for (int i = 0; i < pontos.size(); i++) {
            gravarArq.println("array(\"x\" => "+pontos.get(i).x+", \"y\" => "+pontos.get(i).y+", \"indexLabel\" => \"Cidade "+position.get(i)+"\", \"color\" => \"blue\"),");
            if (i != (pontos.size() - 1)){
                System.out.println("Cidade " + position.get(i) + " -> Posicão X : " + pontos.get(i).x + " Posição Y : " + pontos.get(i).y);
                System.out.println(" A distância da cidade " + position.get(i) + " para a cidade : " + position.get((i+1)) + " é de : " + distance.get(i));
            }else{
                System.out.println("Cidade " + position.get(i) + " -> Posicão X : " + pontos.get(i).x + " Posição Y : " + pontos.get(i).y);
                System.out.println(" A distância da cidade " + position.get(i) + " para a cidade : " + position.get(0) + " é de : " + distance.get(i));
            }
            totalDistance += distance.get(i);
        }
        System.out.println("A distancia total é "+ totalDistance);
        System.out.println("######################################################");
    }
}
