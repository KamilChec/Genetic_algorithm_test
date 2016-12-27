package genetic_algorithm;
/**
 * Created by User on 19.12.2016.
 */
public class Individual {

    int chromosomeSize = 8;
    //Each place in the array is the building - In other worlds each gene is the building
    private char[][] genes = new char[chromosomeSize][chromosomeSize];

    //Creating a random individual
    public void generateIndividual(char[] array) {
        for(int i = 0 ; i < size() ; i++){
            for( int y = 0 ; y < size() ; y++){
            genes[i][y] = array[randomWithRange(0,array.length - 1)];
            }
        }
    }


    /* Getters and setters */
    public char getGene (int index, int index2) {
        return genes[index][index2];
    }
    public void setGene (int index, int index2, char insert) { genes[index][index2] = insert; }

    /* public methods */
    public int size() {
        return chromosomeSize;
    }
    static int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }


}
