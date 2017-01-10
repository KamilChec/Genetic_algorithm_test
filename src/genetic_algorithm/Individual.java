package genetic_algorithm;
/**
 * Created by User on 19.12.2016.
 */
public class Individual {

    //Each place in the array is the building - In other worlds each gene is the building
    private Gene[][] genes;
    //Cache
    private double fitness = 0;
    //Creating a random individual
    public Individual(Gene[] template) {

        int size = (int) Math.sqrt(template.length);
        genes = new Gene[size][size];
        // Loop through the template []
        for (int i = 0; i < template.length; i++) {
            int index1 = randomWithRange(0, (size-1));
            int index2 = randomWithRange(0, (size-1));
            while (genes[index1][index2] != null) {
                index1 = randomWithRange(0, (size-1));
                index2 = randomWithRange(0, (size-1));
            }
            genes[index1][index2] = new Gene(template[i].buildingType, template[i].fieldNumber);
        }
    }

    //Copying constructor
    public Individual(Individual indiv) {

        int size = (indiv.getGeneRowLength(0));
        genes = new Gene[size][size];
        // Loop through the template []
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size;j++){
                genes[i][j] = new Gene(indiv.getGene(i,j).buildingType, indiv.getGene(i,j).fieldNumber);
            }
        }
    }

    /* Getters and setters */
    public Gene getGene (int index, int index2) {
        return genes[index][index2];
    }
    public int getGeneRowLength (int index){
        return genes[index].length;
    }
    public void setGene (int index, int index2, Building insert, int number){
        genes[index][index2].buildingType = insert;
        genes[index][index2].fieldNumber = number;
    }
    public void setGeneNull (int index, int index2){
        genes[index][index2].buildingType = Building.TEMP;
        genes[index][index2].fieldNumber = -1;
    }
    public char getGeneSign(Building type){
        char sign;
        switch (type) {
            case HOUSE:
                sign = '#';
                break;

            case STORE:
                sign = 'o';
                break;

            case TRASHCAN:
                sign = 'Y';
                break;

            case PLAYGROUND:
                sign = 'G';
                break;

            case EMPTY:
                sign = '_';
                break;

            case TEMP:
                sign = 'T';
                break;

            default:
                sign = '_';
                break;

        }
        return sign;
    }

    /* public methods */
//    public int size() {
//        return genes[0].length;
//    }

    public double getFitness() {
        if( fitness == 0 ){
            fitness = Algorithm.getFitness(this);
        }
        return fitness;

    }

    static int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    public void drawIndividual(int size) { // Need to change this
        for (int i = 0; i < size; i++) {
            String temp = "";
            Gene tempGene; // Initializing creates a problem
            for (int j = 0; j < size; j++) {
            tempGene = getGene(i,j);
            temp += getGeneSign(tempGene.buildingType);
            }
            System.out.println(temp);
        }
    }

}
