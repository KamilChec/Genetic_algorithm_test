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
        Gene[] template = createTemplate(64,4,4,4,5);
        System.out.println("Genetic Algorithm");
        Population myPop = new Population(50,true, template);
        myPop.individuals[0].drawIndividual();
    }
}
