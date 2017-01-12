package genetic_algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 19.12.2016.
 */
public class Main
{
    public static Gene[] createTemplate(int templateSize, int numberOfHouses,int numberOfStores, int numberOfTrashcans, int numberOfPlaygrounds) {

        // If Condition - if modulo /64 - System Exit
        Gene[] genes = new Gene[templateSize];
        for(int i = 0; i < numberOfHouses; i++){
            genes[i] = new Gene(Building.HOUSE,i);
        }
        for(int i = numberOfHouses; i < (numberOfHouses + numberOfStores) ; i++){
            genes[i] = new Gene(Building.STORE,i);
        }
        for(int i = (numberOfHouses + numberOfStores); i < (numberOfHouses + numberOfStores + numberOfTrashcans ) ; i++){
            genes[i] = new Gene(Building.TRASHCAN,i);
        }
        for(int i = (numberOfHouses + numberOfStores + numberOfTrashcans ); i < (numberOfHouses + numberOfStores + numberOfTrashcans + numberOfPlaygrounds ) ; i++){
            genes[i] = new Gene(Building.PLAYGROUND,i);
        }
        for(int i = (numberOfHouses + numberOfStores + numberOfTrashcans + numberOfPlaygrounds ); i < templateSize ; i++) {
            genes[i] = new Gene(Building.EMPTY, i);
        }
        return genes;
    }

    public static void main(String[] args)
    {

        System.out.println("Genetic Algorithm");

        //Gene[] template = createTemplate(128,8,10,8,8);
        Gene[] template = createTemplate(81,8,4,7,5);
        Population myPop = new Population(50,true, template);

        int generationCount = 0;
        while(myPop.getFittest().getFitness() < 2368){ //Proper condition of ending algorithm
            generationCount++;
            System.out.println("Generation: "+ generationCount +" Fittest: "+ myPop.getFittest().getFitness());
            myPop = Algorithm.evolvePopulation(myPop,template);
        }
        System.out.println("Solution found!");
        System.out.println("Generation: "+ generationCount);
        System.out.println("Genes:");
        myPop.getFittest().drawIndividual(myPop.getFittest().getGeneRowLength(0));

    }
}