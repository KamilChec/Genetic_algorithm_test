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
    private static void crossover(Individual indiv1, Individual indiv2) { // Sometimes error line 54

        // Individuals sizes
        int size1 = (indiv1.getGeneRowLength(0));
        int size2 = (indiv2.getGeneRowLength(0));

        if ( size1 == size2 ) {
            int column = randomWithRange(1,size1);
            int row = randomWithRange(1,size1);

            //int part = generator.nextInt(4); // Don't need to use now

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
    }

    static double calcFitness(double dist, double p) {

        double fit = -dist*dist + 2*p*dist;

        return fit;
    }

    static double getFitness(int templateSize, Individual indiv, double hsDistance, double htDistance, double hpDistance) {

        int size = (int) Math.sqrt(templateSize);

        double distance;
        double fitness = 0;
        double maxDistance = Math.sqrt((size-1)*(size-1)+(size-1)*(size-1));

        double pHS = hsDistance*maxDistance;
        double pHT = htDistance*maxDistance;
        double pHP = hpDistance*maxDistance;

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

            }
        }

        return fitness;
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
        System.out.println("\n");
        System.out.println("\n");
        myPop.individuals[0].drawIndividual(myPop.individuals[0].getGeneRowLength(0));
        System.out.println(getFitness(64,myPop.individuals[0],0.4,0.5,0.1));
        crossover(myPop.individuals[0],myPop.individuals[1]);
        System.out.println(getFitness(64,myPop.individuals[0],0.4,0.5,0.1));
        myPop.individuals[0].drawIndividual(myPop.individuals[0].getGeneRowLength(0));
    }
}