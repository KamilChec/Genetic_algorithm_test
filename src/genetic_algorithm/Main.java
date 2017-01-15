package genetic_algorithm;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.swing.*;
import java.awt.*;
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

    static void saveToFile(int generation, double fitness) throws IOException {

        FileWriter file = new FileWriter("ga_data_size81_params2.txt", true);
        BufferedWriter out = new BufferedWriter(file);
        String toWrite =  generation + "\t" + fitness + "\n";
        out.write(toWrite);
        out.close();

    }


    public static void main(String[] args)
    {

        System.out.println("Genetic Algorithm");

        //Gene[] template = createTemplate(128,8,10,8,8);
        Gene[] template = createTemplate(81,12,6,6,6);
        Population myPop = new Population(50,true, template);
        List<Population> bestFit = new ArrayList<>();
        bestFit.add(myPop);

        int counter = 0;
        double tempFittest = 0;
        int generationCount = 0;


        while(counter <= 200){ //Proper condition of ending algorithm
            generationCount++;
            System.out.println("Generation: "+ generationCount +" Fittest: "+ myPop.getFittest().getFitness());
            tempFittest = myPop.getFittest().getFitness();
            myPop = Algorithm.evolvePopulation(myPop,template);
            bestFit.add(myPop);
            if(tempFittest == myPop.getFittest().getFitness()){
                counter++;
            }
            else{
                counter = 0;
            }
            try {
                saveToFile(generationCount, myPop.getFittest().getFitness());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        showAnimation(bestFit);

        System.out.println("Solution found!");
        System.out.println("Generation: "+ generationCount);
        System.out.println("Genes:");
        myPop.getFittest().drawIndividual(myPop.getFittest().getGeneRowLength(0));

    }
    private static void showAnimation(List<Population> population){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Animation animation = new Animation(population);
                newJFrame(animation);
                animation.runAnimation(true);
            }
        });
    }

    private static void newJFrame(JPanel a){
        JFrame f = new JFrame("Area");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLayout(new GridLayout(1, 1));
        f.setSize(700, 700);
        f.setMinimumSize(new Dimension(200, 200));
        f.setLocation(300, 0);
        f.setBackground(Color.white);
        f.setLayout(new BorderLayout());
        f.add(a, BorderLayout.CENTER);
        f.setVisible(true);
    }
}
