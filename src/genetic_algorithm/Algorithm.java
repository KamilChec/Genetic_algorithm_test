package genetic_algorithm;

import java.util.Random;

/**
 * Created by dorota on 27.12.16.
 */
public class Algorithm {

    Random generator = new Random();

    void crossover(Individual mom, Individual dad, int populationSize) {

        int column = generator.nextInt(populationSize);
        int row = generator.nextInt(populationSize);
        int part = generator.nextInt(4);

        Individual temporary=mom;

        switch(part) {
            case(0):
                for (int i=0; i<column; i++) {
                    for (int j=0; j<row; j++) {
                        mom.setGene(i, j, dad.getGene(i,j));
                        dad.setGene(i, j, temporary.getGene(i,j));
                    }
                }
            case(1):
                for (int i=column; i<populationSize; i++) {
                    for (int j=0; j<row; j++) {
                        mom.setGene(i, j, dad.getGene(i,j));
                        dad.setGene(i, j, temporary.getGene(i,j));
                    }
                }
            case(2):
                for (int i=0; i<column; i++) {
                    for (int j=row; j<populationSize; j++) {
                        mom.setGene(i, j, dad.getGene(i,j));
                        dad.setGene(i, j, temporary.getGene(i,j));
                    }
                }
            case(3):
                for (int i=column; i<populationSize; i++) {
                    for (int j=row; j<populationSize; j++) {
                        mom.setGene(i, j, dad.getGene(i,j));
                        dad.setGene(i, j, temporary.getGene(i,j));
                    }
                }
        }

    }

}
