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
    static int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    public static void main(String[] args)
    {
        Gene[] template = createTemplate(64,4,4,4,5);
        System.out.println("Genetic Algorithm");
        Population myPop = new Population(50,true, template);
        myPop.individuals[0].drawIndividual(myPop.individuals[0].getGeneRowLength(0));
        System.out.println("\n");
        myPop.individuals[1].drawIndividual(myPop.individuals[0].getGeneRowLength(0));
        System.out.println("\n");
        crossover(myPop.individuals[0],myPop.individuals[1]);
        myPop.individuals[0].drawIndividual(myPop.individuals[0].getGeneRowLength(0));
        System.out.println("\n");
        myPop.individuals[1].drawIndividual(myPop.individuals[0].getGeneRowLength(0));
        System.out.println("\n");
    }
}
