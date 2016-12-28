package genetic_algorithm;

import java.util.Random;

/**
 * Created by dorota on 27.12.16.
 */
public class Algorithm {

    Random generator = new Random();

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

}
