package genetic_algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by dorota on 27.12.16.
 */
public class Algorithm {

    // Declaring Variables
    private static final double uniformRate = 0.5;
    private static final double mutationRate = 0.001;
    private static final int tournamentSize = 5;
    private static final boolean elitism = true;



    //Evolve population
    public static Population evolvePopulation(Population pop, Gene[] template) {
        Population newPopulation = new Population(pop.size(), false, template);

        // Keep our best individual
        if (elitism) {
            newPopulation.saveIndividual(0, pop.getFittest());
        }

        // Crossover population
        int elitismOffset;
        if (elitism) {
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }
        // Loop over the population size and create new individuals with
        // Crossover
        for (int i = elitismOffset; i < pop.size(); i++) {
            Individual indiv1 = tournamentSelection(pop,template);
            Individual indiv2 = tournamentSelection(pop,template);
            Individual newIndiv = cxCrossover(indiv1, indiv2);
            newPopulation.saveIndividual(i, newIndiv);
        }

        // Mutate population
        for (int i = elitismOffset; i < newPopulation.size(); i++) {
            mutate(newPopulation.getIndividual(i));
        }

        return newPopulation;
    }

    // Crossover individuals
    private static Individual crossover(Individual indiv1, Individual indiv2) {

        // Individuals sizes
        int size1 = (indiv1.getGeneRowLength(0));
        int size2 = (indiv2.getGeneRowLength(0));

        if ( size1 == size2 ) {
            int column = randomWithRange(1,size1);
            int row = randomWithRange(1,size1);


            //Creating temporary individual
            Individual temporary1 = new Individual(indiv1);
            Individual temporary2 = new Individual(indiv2);

            //Clear crossover area - Not good !
            for (int i = 0; i < row; i++) {
                for (int j=0; j < column; j++) {
                    indiv1.setGeneNull(i, j);
                    indiv2.setGeneNull(i, j);
                }
            }

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
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
        // Functions returns one from two given individuals - Crossover method need to be refined
        Individual newSol;
        if (Math.random() <= uniformRate) {
            newSol = new Individual(indiv1);
        } else {
            newSol = new Individual(indiv1);
        }

        return newSol;
    }
    private static Individual cxCrossover(Individual indiv1, Individual indiv2){

        // Individuals sizes
        int size1 = (indiv1.getGeneRowLength(0));
        int size2 = (indiv2.getGeneRowLength(0));
        //
        Individual newSol = indiv1;
        if ( size1 == size2 ) {

            //Transform 2D arrays for 1D arrays
            Gene[] parent1 = new Gene[size1*size1];
            Gene[] parent2 = new Gene[size1*size1];
            //Child
            Gene[] buffer = new Gene[size1*size1];
            List<Gene> list1 = new ArrayList<>(); // <Gene> ?
            List<Gene> list2 = new ArrayList<>();
            for (int i = 0; i < size1; i++) {
                for (int j = 0; j < size1; j++) {
                    list1.add(indiv1.getGene(i,j));
                    list2.add(indiv2.getGene(i,j));
                }
            }
            for (int i = 0; i < parent1.length; i++) {
                parent1[i] = list1.get(i);
                parent2[i] = list2.get(i);
                //Filling child with temp values
                buffer[i] = new Gene(Building.TEMP,-1);

            }

            // Already have transformed arrays
            buffer[0] = new Gene(parent1[0]); //Przypisanie 1
            int temp = parent2[0].fieldNumber;
            for(int i = 1 ; i < parent1.length; i++){ //Buffer length
                int j = 0;
                while(parent1[j].fieldNumber != temp){
                    //buffer[j] = indiv1[j];
                    j++;
                }
                buffer[j] = new Gene(parent1[j]);
                temp = parent2[j].fieldNumber;
            }

            for( int i = 0 ; i < buffer.length; i++){
                if(buffer[i].fieldNumber == -1){
                    buffer[i] = new Gene(parent2[i]);
                }
            }

            //Create 2D Individual from 1D Genes array
            List<Gene> templist = new ArrayList<>();
            for (int i = 0 ; i < buffer.length; i++){
                templist.add(buffer[i]);
            }

            Gene[][] newSolGenes = new Gene[size1][size1];
            int z = 0;
            for(int i = 0; i < size1; i++){
                for(int j = 0; j< size1;j++){
                    newSolGenes[i][j] = new Gene(templist.get(z));
                    z++;
                }
            }
            newSol = new Individual(newSolGenes);

        }

        return newSol;
    }

    //Mutation
    private static void mutate(Individual indiv) { // Mutation ok !

        int size = (indiv.getGeneRowLength(0));
        // Loop through genes
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (Math.random() <= mutationRate) { // Need to be checked for proper values
                    // Select random gene
                    int index1 = randomWithRange(0, (size-1));
                    int index2 = randomWithRange(0, (size-1));
                    while ((index1 == i) && (index2 == j)) {
                        index1 = randomWithRange(0, (size-1));
                        index2 = randomWithRange(0, (size-1));
                    }
                    Gene temp = new Gene(indiv.getGene(index1,index2)); //Creating new temporary Gene
                    //Save genes
                    indiv.setGene(index1, index2, indiv.getGene(i,j).buildingType, indiv.getGene(i,j).fieldNumber);
                    indiv.setGene(i, j, temp.buildingType,temp.fieldNumber);
                }
            }

        }
    }

    // Select individuals for crossover
    private static Individual tournamentSelection(Population pop, Gene[] template) {
        // Create a tournament population
        Population tournament = new Population(50,true, template);
        // For each place in the tournament get a random individual
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.getIndividual(randomId));
        }
        // Get the fittest
        Individual fittest = tournament.getFittest();
        return fittest;
    }

    static int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }
    static double calcFitness(double dist, double p) {

        double fit = -dist*dist + 2*p*dist;

        return fit;
    }

    static double getFitness(Individual indiv) {

        int size = (indiv.getGeneRowLength(0));



        double distance;
        double fitness = 0;
        double maxDistance = Math.sqrt((size-1)*(size-1)+(size-1)*(size-1));

        double pHS = 0.4*maxDistance;
        double pHT = 0.3*maxDistance;
        double pHP = 0.1*maxDistance;
        double pPT = 0.4*maxDistance;

        Building build1;
        Building build2;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                build1 = indiv.getGene(i, j).buildingType;
                if (build1==Building.HOUSE) {
                    for (int z = 0; z < size; z++) {
                        for (int v = 0; v < size; v++) {
                            build2 = indiv.getGene(z, v).buildingType;
                            switch (build2) {

                                case HOUSE:
                                    break;
                                case STORE:
                                    distance = Math.sqrt((i-z)*(i-z)+(j-v)*(j-v));
                                    fitness+=calcFitness(distance,pHS);
                                    break;
                                case TRASHCAN:
                                    distance = Math.sqrt((i-z)*(i-z)+(j-v)*(j-v));
                                    fitness+=calcFitness(distance,pHT);
                                    break;
                                case EMPTY:
                                    break;
                                case PLAYGROUND:
                                    distance = Math.sqrt((i-z)*(i-z)+(j-v)*(j-v));
                                    fitness+=calcFitness(distance,pHP);
                                    break;
                                case TEMP:
                                    break;
                            }

                        }

                    }
                }

                else if (build1==Building.PLAYGROUND) {

                    for (int z = 0; z < size; z++) {
                        for (int v = 0; v < size; v++) {
                            build2 = indiv.getGene(z, v).buildingType;
                            switch (build2) {

                                case HOUSE:
                                    break;
                                case STORE:
                                    break;
                                case TRASHCAN:
                                    distance = Math.sqrt((i-z)*(i-z)+(j-v)*(j-v));
                                    fitness+=calcFitness(distance,pPT);
                                    break;
                                case EMPTY:
                                    break;
                                case PLAYGROUND:
                                    break;
                                case TEMP:
                                    break;
                            }

                        }

                    }

                }

            }
        }

        return fitness;
    }
}
