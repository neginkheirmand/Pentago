package ir.ac.aut;

public class MarbleHouse {
    private final int xOfHouse;
    private final int yOfHouse;
    private boolean isFull;
    private Marble marble;

    public MarbleHouse(int x, int y){
        this.xOfHouse=x;
        this.yOfHouse=y;
        this.isFull=false;
        marble=null;
//        marble= new Marble(TYPE.RED);
        //to make sure we wont use it (cause there is still no marble in this house of the board)
    }

    public boolean isFull(){
        return isFull;
    }

    public void putMarble(TYPE typeOfPlayer){
        isFull=true;
        marble= new Marble(typeOfPlayer);
    }

    public int getXOfHouse(){
        return xOfHouse;
    }

    public int getyOfHouse(){
        return yOfHouse;
    }

    public Marble getMarble(){
        return marble;
    }

}
