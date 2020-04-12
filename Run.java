package ir.ac.aut;

public class Run {


    public static void main(String[] args) {
        Pentago pentagoBoard = new Pentago();
        AiPlayer blackPlayer= new AiPlayer(TYPE.BLACK);
        Player redPlayer= new Player(TYPE.RED);

        while(true){
            //turn of red/white player
            //this prototype is made for the red player(the human player to start)
            pentagoBoard.print();
            if(pentagoBoard.gameOver(true)){
                break;
            }
            redPlayer.play(pentagoBoard);

            //now turn of black player
            pentagoBoard.print();
            if(pentagoBoard.gameOver(true)){
                break;
            }

            System.out.printf("ai going to play\n");
            blackPlayer.decideNextMove(pentagoBoard);
            System.out.printf("ai played\n\n");
        }

    }


}