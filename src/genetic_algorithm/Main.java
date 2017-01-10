package genetic_algorithm;

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

        Gene[] template = createTemplate(64,4,4,4,5);
        Population myPop = new Population(50,true, template);

        int generationCount = 0;
        while(myPop.getFittest().getFitness() < 4000){ //Proper condition of ending algorithm
            generationCount++;
            System.out.println("Generation: "+ generationCount +" Fittest: "+ myPop.getFittest().getFitness());
            myPop = Algorithm.evolvePopulation(myPop,template);
        }
        System.out.println("Solution found!");
        System.out.println("Generation: "+ generationCount);
        System.out.println("Genes:");
        myPop.getFittest().drawIndividual(myPop.getFittest().getGeneRowLength(0));

//
//        myPop.individuals[0].drawIndividual(myPop.individuals[0].getGeneRowLength(0));
//        System.out.println("\n");
//        myPop.individuals[1].drawIndividual(myPop.individuals[0].getGeneRowLength(0));
//        System.out.println("\n");
//        crossover(myPop.individuals[0],myPop.individuals[1]);
//        myPop.individuals[0].drawIndividual(myPop.individuals[0].getGeneRowLength(0));
//        System.out.println("\n");
//        myPop.individuals[1].drawIndividual(myPop.individuals[0].getGeneRowLength(0));
//        System.out.println("\n");
//        System.out.println("\n");
//        System.out.println("\n");
//        myPop.individuals[0].drawIndividual(myPop.individuals[0].getGeneRowLength(0));
//        System.out.println(getFitness(64,myPop.individuals[0],0.4,0.5,0.1));
//        crossover(myPop.individuals[0],myPop.individuals[1]);
//        System.out.println(getFitness(64,myPop.individuals[0],0.4,0.5,0.1));
//        myPop.individuals[0].drawIndividual(myPop.individuals[0].getGeneRowLength(0));
    }
}