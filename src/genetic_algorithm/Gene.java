package genetic_algorithm;

/**
 * Created by User on 19.12.2016.
 */
public class Gene {

    Building buildingType;
    int fieldNumber;

    //Constructor
    public Gene(Building type, int number){
        buildingType = type;
        fieldNumber = number;
    }

}
