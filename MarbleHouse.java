package ir.ac.aut;


/**
 * this class holds information about each MarblePlace and House of each Block in the Pentago Board
 * @author      negin kheirmand <neginkheirmand@aut.ac.ir>
 * @version     1                 (current version number of program)
 */

public class MarbleHouse {
    //this is the x of the MarbleHouse
    private final int xOfHouse;
    //this is the y of the MarbleHouse
    private final int yOfHouse;
    //a boolean which specifies if the House is full or is empty
    private boolean isFull;
    //marble in the house(if the house is empty the value of this object will be null)
    private Marble marble;
    //a integer to hold the information about the number of the block which this MarbleHouse belongs to
    private final int blockNum;

    /**
     * the constructor of this class
     * @param y y field of the MarbleHouse
     * @param x x field of the MarbleHouse
     * @param blockNum blockNum field of the MarbleHouse
     */
    public MarbleHouse(int y, int x, int blockNum){
        this.blockNum=blockNum;
        this.xOfHouse=x;
        this.yOfHouse=y;
        this.isFull=false;
        marble=null;
        //to make sure we wont use it (cause there is still no marble in this house of the board)
    }

    /**
     * a getter method for the marble in the MarbleHouse
     * @return reference to the marble object or to null in case the MarbleHouse is empty
     */
    public Marble getMarble(){
        if(isFull==true){
            return marble;
        }
        return null;
    }

    /**
     * a method to specify if the MarbleHouse is full by looking at its isFull field
     * @return true if its full and false if it isnt
     */
    public boolean isFull(){
        return isFull;
    }

    /**
     * getter method for the TYPE of the marble in this MarbleHouse
     * @return the TYPE of the Marble or null in case there is no marble in this MarbleHouse
     */
    public TYPE getType(){
        if(isFull==true) {
            return marble.getTypeOfMarble();
        }else{
            return null;
        }
    }

    /**
     * a method for filling the marble field of this MarbleHouse
     * @param typeOfPlayer the TYPE of the player putting a marble in this MarbleHouse(the TYPE of the marble)
     */
    public void putMarble(TYPE typeOfPlayer){
        isFull=true;
        marble= new Marble(typeOfPlayer);
    }

    /**
     * a method to hollow this MarbleHouse
     */
    public void hollowHouse(){
        isFull = false;
        marble = null;
    }

    /**
     * a method for getting the x field
     * @return
     */
    public int getXOfHouse(){
        return xOfHouse;
    }


    /**
     * a method for getting the y field
     * @return
     */
    public int getYOfHouse(){
        return yOfHouse;
    }

    /**
     * a method for getting the blockNum field
     * @return
     */
    public int getBlockNum(){return blockNum;}


    @Override
    public boolean equals(Object obj) {
        if(obj instanceof MarbleHouse)
        {
            MarbleHouse object = (MarbleHouse) obj;
            if(object.isFull()==isFull){
                if(isFull==true) {
                    if (object.getType().name().equals(marble.getTypeOfMarble().name())) {
                        return true;
                    } else {
                        return false;
                    }
                }else{
                    return true;
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    //for only checking the type of marble
}
