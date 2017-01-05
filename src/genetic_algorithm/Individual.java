package genetic_algorithm;
/**
 * Created by User on 19.12.2016.
 */
public class Individual {

    int chromosomeSize = 8;
    //Each place in the array is the building - In other worlds each gene is the building
    private Gene[][] genes;
    //Creating a random individual
    public void generateIndividual(Gene[] template) {

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


    /* Getters and setters */
    public Gene getGene (int index, int index2) {
        return genes[index][index2];
    }
    public void setGene (int index, int index2, Building insert, int number, Gene[][] Genes){
        Genes[index][index2].buildingType = insert;
        Genes[index][index2].fieldNumber = number;
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

            default:
                sign = '_';
                break;

        }
        return sign;
    }

    /* public methods */
    public int size() {
        return chromosomeSize;
    }

    static int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    public void drawIndividual() {
        for (int i = 0; i < size(); i++) {
            String temp = "";
            Gene tempGene; // Initializing creates a problem
            for (int j = 0; j < size(); j++) {
            tempGene = getGene(i,j);
            temp += getGeneSign(tempGene.buildingType);
            }
            System.out.println(temp);
        }
    }

}
