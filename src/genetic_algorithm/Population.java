package genetic_algorithm;

/**
 * Created by User on 19.12.2016.
 */
public class Population {

    Individual[] individuals;

    /*Constructor*/
    //Create population
    public Population(int populationSize, boolean initialize, Gene[] template){
        individuals = new Individual[populationSize];
        // Initializing population
        if (initialize){
            //Creating individuals loop
            for (int i = 0 ; i < populationSize; i++){
                Individual newIndividual = new Individual(template);
                saveIndividual(i, newIndividual);
            }
        }
    }

    /* Getters */
    public Individual getIndividual(int index) {
        return individuals[index];
    }

    public Individual getFittest() { // Getting the best individual from the whole population
        Individual fittest = individuals[0];
        // Loop through individuals to find the best individual
        for (int i = 0; i < size(); i++) {
            if (fittest.getFitness() <= getIndividual(i).getFitness()) {
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }

    /* Public methods*/
    public int size() {
        return individuals.length;
    }
    //Saving Individual
    public void saveIndividual(int index, Individual indiv) {
        individuals[index] = indiv;
    }

}
