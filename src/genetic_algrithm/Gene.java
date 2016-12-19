package genetic_algrithm;

/**
 * Created by User on 19.12.2016.
 */
public class Gene {

    //Location variables
    int first_x_coordinate;
    int second_x_coordinate;
    int first_y_coordinate;
    int second_y_coordinate;
    //Rest of the variables
    int size;
    boolean occurance;


    //Constructor
    public Gene(int f_x_coordinate, int s_x_coordinate, int f_y_coordinate, int s_y_coordinate, int s ,boolean occur){
        first_x_coordinate = f_x_coordinate;
        second_x_coordinate = s_x_coordinate;
        first_y_coordinate = f_y_coordinate;
        second_y_coordinate = s_y_coordinate;
        size = s;
        occurance = occur;
    }

}
