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
    private static void crossover(Individual indiv1, Individual indiv2) {

        // Individuals sizes
        int size1 = (int) Math.sqrt(indiv1.getGeneRowLength(0));
        int size2 = (int) Math.sqrt(indiv2.getGeneRowLength(0));

        if ( size1 == size2 ) {
            int column = randomWithRange(0,size1);
            int row = randomWithRange(0,size1);
            //int part = generator.nextInt(4); // Don't need to use now

            //Creating temporary individual
            Individual temporary1 = indiv1;
            Individual temporary2 = indiv2;

            //Clear crossover area
            for (int i = 0; i < column; i++) {
                for (int j=0; j < row; j++) {
                    indiv1.setGeneNull(i, j);
                    indiv2.setGeneNull(i, j);
                }
            }

            for (int i = 0; i < column; i++) {
                for (int j = 0; j < row; j++) {
                    indiv1.setGene(i, j, temporary2.getGene(i,j).buildingType, temporary2.getGene(i,j).fieldNumber);
                    indiv2.setGene(i, j, temporary1.getGene(i,j).buildingType, temporary1.getGene(i,j).fieldNumber);
                        // Loop for first individual
                        for (int z = 0; z < size1; z++) {
                            for (int v = 0; v < size1; v++) {
                                if((z != i) && (v != j)){
                                    if(indiv1.getGene(z,v).fieldNumber == indiv1.getGene(i,j).fieldNumber){
                                        indiv1.setGene(z,v,temporary1.getGene(i,j).buildingType, temporary1.getGene(i,j).fieldNumber);
                                    }

                                }
                            }
                        }
                        // Loop for second individual
                        for (int z=0; z < size1; z++) {
                            for (int v=0; v < size1; v++) {
                                if((z != i) && (v != j)){
                                    if(indiv2.getGene(z,v).fieldNumber == indiv2.getGene(i,j).fieldNumber) {
                                        indiv2.setGene(z,v,temporary2.getGene(i,j).buildingType, temporary2.getGene(i,j).fieldNumber);
                                    }
                                }
                            }
                        }
                }
            }
        }
    }

    //Mutation
    private static void mutate(Individual indiv) { // Giving population as a parameter is reasonable ?

        // Loop through genes
        for (int i = 0; i < indiv.size(); i++) {
            for (int j = 0; j < indiv.size(); j++) {
                if (Math.random() <= mutationRate) { // Need to be checked for proper values
                    // Select random gene
                    int index1 = randomWithRange(0, (indiv.size()-1));
                    int index2 = randomWithRange(0, (indiv.size()-1));
                    while ((index1 == i) && (index2 == j)) {
                        index1 = randomWithRange(0, (indiv.size()-1));
                        index2 = randomWithRange(0, (indiv.size()-1));
                    }
                    Gene temp = indiv.getGene(index1,index2);
                    //Save genes
                    indiv.setGene(index1, index2, indiv.getGene(i,j).buildingType, indiv.getGene(i,j).fieldNumber);
                    indiv.setGene(i, j, temp.buildingType,temp.fieldNumber);
                }
            }

        }
    }
    static int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }
}
