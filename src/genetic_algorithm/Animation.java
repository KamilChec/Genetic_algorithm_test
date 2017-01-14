package genetic_algorithm;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Created by dorota on 12.01.17.
 */
public class Animation extends JPanel {

    private BufferedImage house;
    private BufferedImage store;
    private BufferedImage trash;
    private BufferedImage playground;
    private List<Population> currentPop;
    private Individual currentBest;
    private Population pop;
    private int size;
    int k=1;
    int dist;
    int scale;
    Toolkit toolkit;
    Timer timer;
    Boolean run = true;


    public Animation(List<Population> population) {

        currentPop = population;
        System.out.println(currentPop.size());
        pop = currentPop.get(0);
        currentBest = pop.getFittest();
        size = pop.getIndividual(0).getGeneRowLength(0);
        scale=(int)600/size;
        dist=(int)scale/2;

        try {
            house = ImageIO.read(Animation.class.getResource("/resources/images/H.png"));
        } catch (IOException e) {
            System.err.println("Blad odczytu obrazka");
            e.printStackTrace();
        }
        try {
            store = ImageIO.read(Animation.class.getResource("/resources/images/S.png"));
        } catch (IOException e) {
            System.err.println("Blad odczytu obrazka");
            e.printStackTrace();
        }
        try {
            trash = ImageIO.read(Animation.class.getResource("/resources/images/T.png"));
        } catch (IOException e) {
            System.err.println("Blad odczytu obrazka");
            e.printStackTrace();
        }
        try {
            playground = ImageIO.read(Animation.class.getResource("/resources/images/P.png"));
        } catch (IOException e) {
            System.err.println("Blad odczytu obrazka");
            e.printStackTrace();
        }

        toolkit = Toolkit.getDefaultToolkit();
        timer = new Timer();
        timer.schedule(new RemindTask(), 0, 50);
        this.setBackground(Color.white);

    }

    public void runAnimation(Boolean run){
        this.run = run;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D area = (Graphics2D)g;
        area.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        area.setBackground(Color.white);
        area.setColor(Color.black);
        area.drawRect(dist,dist,size*scale,size*scale);
        Building build;
        pop = currentPop.get(k-1);
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                int X=dist+j*scale;
                int Y=dist+i*scale;
                build = pop.getFittest().getGene(i, j).buildingType;
                switch (build) {
                    case HOUSE:
                        area.drawImage(house,X,Y,dist,dist,this);
                        break;
                    case STORE:
                        area.drawImage(store,X,Y,dist,dist,this);
                        break;
                    case TRASHCAN:
                        area.drawImage(trash,X,Y,dist,dist,this);
                        break;
                    case EMPTY:
                        break;
                    case PLAYGROUND:
                        area.drawImage(playground,X,Y,dist,dist,this);
                        break;
                    case TEMP:
                        break;
                }
            }
        }
    }

    class RemindTask extends TimerTask {
        public void run() {
            if (k<currentPop.size()) {
                k++;
                repaint();
            }
            else {}
        }
    }

}


