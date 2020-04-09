package ir.ac.aut;



public class Marble {
    private TYPE typeOfMarble;
    private String colorOfPlayer;
    public Marble(TYPE typeOfMarble){
        this.typeOfMarble=typeOfMarble;
        if(typeOfMarble.name().equals(TYPE.BLACK.name())){
            colorOfPlayer="\033[1;90m";
        }else{
            colorOfPlayer="\033[1;91m";
        }
    }

    public TYPE getTypeOfMarble() {
        return typeOfMarble;
    }

    public void setTypeOfMarble(TYPE typeOfMarble) {
        this.typeOfMarble = typeOfMarble;
    }

    public String getColorOfPlayer(){
        return colorOfPlayer;
    }
}
