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
        //to make sure we wont use it (cause there is still no marble in this house of the board)
    }

    public boolean isFull(){
        return isFull;
    }

    public TYPE getType(){
        if(isFull==true) {
            return marble.getTypeOfMarble();
        }else{
            return null;
        }
    }
    public void putMarble(TYPE typeOfPlayer){
        isFull=true;
        marble= new Marble(typeOfPlayer);
    }

    public void hollowHouse(){
        isFull = false;
        marble = null;
    }

    public int getXOfHouse(){
        return xOfHouse;
    }

    public int getYOfHouse(){
        return yOfHouse;
    }

    public Marble getMarble(){
        return marble;
    }

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
