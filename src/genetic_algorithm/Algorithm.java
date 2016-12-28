package genetic_algorithm;

import java.util.Random;

/**
 * Created by dorota on 27.12.16.
 */
public class Algorithm {

    // Declaring Variables
    private static final double mutationRate = 0.001;

    Random generator = new Random();

    // Crossover individuals
    void crossover(Individual indiv1, Individual indiv2, int chromosomeSize) {

        int column = generator.nextInt(chromosomeSize);
        int row = generator.nextInt(chromosomeSize);
        int part = generator.nextInt(4);

        Individual temporary=indiv1;

        switch(part) {
            case(0):
                for (int i=0; i<column; i++) {
                    for (int j=0; j<row; j++) {
                        indiv1.setGene(i, j, indiv2.getGene(i,j));
                        indiv2.setGene(i, j, temporary.getGene(i,j));
                    }
                }
            case(1):
                for (int i=column; i<chromosomeSize; i++) {
                    for (int j=0; j<row; j++) {
                        indiv1.setGene(i, j, indiv2.getGene(i,j));
                        indiv2.setGene(i, j, temporary.getGene(i,j));
                    }
                }
            case(2):
                for (int i=0; i<column; i++) {
                    for (int j=row; j<chromosomeSize; j++) {
                        indiv1.setGene(i, j, indiv2.getGene(i,j));
                        indiv2.setGene(i, j, temporary.getGene(i,j));
                    }
                }
            case(3):
                for (int i=column; i<chromosomeSize; i++) {
                    for (int j=row; j<chromosomeSize; j++) {
                        indiv1.setGene(i, j, indiv2.getGene(i,j));
                        indiv2.setGene(i, j, temporary.getGene(i,j));
                    }
                }
        }

    }

    //Mutation
    private static void mutate(Population pop ,Individual indiv) { // Giving population as a parameter is reasonable ?

        // Loop through genes
        for (int i = 0; i < indiv.chromosomeSize; i++) {
            for (int j = 0; j < indiv.chromosomeSize; j++) {
                if (Math.random() <= mutationRate) { // Need to be checked for proper values
                    // Create random gene
                    char temp = pop.possibleStates[indiv.randomWithRange(0, pop.possibleStates.length - 1)]; // Need to be tested !
                    //Save random gene
                    indiv.setGene(i, j, temp);
                }
            }

        }
    }
}
