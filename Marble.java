package ir.ac.aut;



public class Marble {
    private TYPE typeOfMarble;

    public Marble(TYPE typeOfMarble){
        this.typeOfMarble=typeOfMarble;
    }

    public TYPE getTypeOfMarble() {
        return typeOfMarble;
    }

    public void setTypeOfMarble(TYPE typeOfMarble) {
        this.typeOfMarble = typeOfMarble;
    }
}
