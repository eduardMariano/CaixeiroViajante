package ia.problema;

import org.jgap.*;
import org.jgap.impl.*;

import ia.ambiente.MinimizingFitnessFunction;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import ia.grafico.Graph;
import  org.jfree.ui.RefineryUtilities;

public class TravelingSalesman {

    private int totalCity;
    private int populations;
    private int numEvolutions = 10000;

    public TravelingSalesman(int totalCity, int populations) {
        this.totalCity = totalCity;
        this.populations = populations;
    }

    public void makeChange() throws Exception {

        Configuration conf = new DefaultConfiguration();
        conf.setPreservFittestIndividual(true);
        conf.setAlwaysCaculateFitness(true);
        FitnessFunction myFunc = new MinimizingFitnessFunction();
        conf.setFitnessFunction(myFunc);

        Gene[] sampleGenes = new Gene[this.totalCity];
        IChromosome[] sampleChromossomes = new IChromosome[this.populations];

        for(int i = 0; i < this.totalCity; i++){
            IntegerGene gene = new IntegerGene(conf, i, i);
            gene.setAllele(i);
            sampleGenes[i] = gene;
        }

        Population population_alan = new Population(conf, sampleChromossomes);

        Chromosome sampleChromosome = new Chromosome(conf, sampleGenes);

        conf.setSampleChromosome(sampleChromosome);

        conf.setPopulationSize(this.populations);

        conf.addGeneticOperator(new SwappingMutationOperator(conf, 12));
        conf.addGeneticOperator(new CrossoverOperator(conf,35));

        Genotype population = Genotype.randomInitialGenotype(conf);


//        Genotype.randomInitialGenotype().mergeResults();

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < this.numEvolutions; i++) {
            if (!uniqueChromosomes(population.getPopulation())) {
                throw new RuntimeException("Inválido o estado da geração "+ i);
            }
            population.evolve();
            population.applyGeneticOperators();
        }
        long endTime = System.currentTimeMillis();

        IChromosome bestSolutionSoFar = population.getFittestChromosome();
        System.out.println("A melhor solução é " + bestSolutionSoFar);
        System.out.println("O tempo total das evoluções foi : " + ( endTime - startTime) + " ms");
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

    static void shuffleArray(Gene[] ar) {

        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            Gene a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
}
