package genetic_algorithm;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by dorota on 12.01.17.
 */
public class Animation extends JPanel {

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
        toolkit = Toolkit.getDefaultToolkit();
        timer = new Timer();
        timer.schedule(new RemindTask(), 0, 50);
        this.setBackground(Color.black);

    }

    public void runAnimation(Boolean run){
        this.run = run;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D area = (Graphics2D)g;
        area.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        area.setBackground(Color.black);
        area.setColor(Color.white);
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
                        area.drawRect(X,Y,dist,dist);
                        area.drawString("H",X+5,Y+15);
                        break;
                    case STORE:
                        area.drawRect(X,Y,dist,dist);
                        area.drawString("S",X+5,Y+15);
                        break;
                    case TRASHCAN:
                        area.drawRect(X,Y,dist,dist);
                        area.drawString("T",X+5,Y+15);
                        break;
                    case EMPTY:
                        break;
                    case PLAYGROUND:
                        area.drawRect(X,Y,dist,dist);
                        area.drawString("P",X+5,Y+15);
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


