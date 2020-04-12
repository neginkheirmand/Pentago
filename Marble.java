package ir.ac.aut;



/**
 * this class holds information about the Marble
 * @author      negin kheirmand <neginkheirmand@aut.ac.ir>
 * @version     1                 (current version number of program)
 */
public class Marble {
    //TYPE of the marble
    private TYPE typeOfMarble;
    //an string of the color of the marble
    private String colorOfPlayer;

    /**
     * the constructor of the class
     * @param typeOfMarble TYPE of the marble
     */
    public Marble(TYPE typeOfMarble){
        this.typeOfMarble=typeOfMarble;
        if(typeOfMarble.name().equals(TYPE.BLACK.name())){
            colorOfPlayer="\033[1;90m";
        }else{
            colorOfPlayer="\033[1;91m";
        }
    }

    /**
     * getter method for the TYPE of the marble
     * @return type of marble
     */
    public TYPE getTypeOfMarble() {
        return typeOfMarble;
    }

    /**
     * getter method for the color string field
     * @return color
     */
    public String getColorOfPlayer(){
        return colorOfPlayer;
    }
}
