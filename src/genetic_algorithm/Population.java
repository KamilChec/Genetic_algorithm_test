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
            for (int i = 0 ; i < size(); i++){
                Individual newIndividual = new Individual();
                newIndividual.generateIndividual(template);
                saveIndividual(i, newIndividual);
            }
        }
    }

    /* Public methods*/
    public int size() {
        return individuals.length;
    }
    //Saving Individual
    public void saveIndividual(int index, Individual indiv) {
        individuals[index] = indiv;
    }
    public Population() {
        // TODO Auto-generated constructor stub
    }
}
