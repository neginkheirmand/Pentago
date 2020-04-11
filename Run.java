package ir.ac.aut;

public class Run {


    public static void main(String[] args) {
        Pentago pentagoBoard = new Pentago();
        AiPlayer blackPlayer= new AiPlayer(TYPE.BLACK);
        Player redPlayer= new Player(TYPE.RED);

        int cont[];
        int row;
        int column;
        int block;
        boolean clockWise;

        int repetition=0;
        while(true){
            //turn of red/white player
            //this prototype is made for the red player(the human player to start)
            pentagoBoard.print();
            if(pentagoBoard.gameOver(true)){
                break;
            }

            do {
                if(repetition>0){
                    System.out.printf("Please enter the address of an empty house of the board\n");
                }
                cont = redPlayer.getInputFromHuman();
                row = cont[0];
                column = cont[1];
                repetition++;
            }while(!pentagoBoard.addMarbleToBoard(TYPE.RED, row, column));
            pentagoBoard.print();
            if(pentagoBoard.gameOver(true)){
                break;
            }
            block = redPlayer.getBlockNum();
            clockWise= redPlayer.getTwistType();
            pentagoBoard.twist(block, clockWise);
            //now turn of black player
            pentagoBoard.print();
            if(pentagoBoard.gameOver(true)){
                break;
            }

            System.out.printf("ai going to play\n");
            blackPlayer.decideNextMove(pentagoBoard);
            System.out.printf("ai played\n\n");
//            do {
//                cont = blackPlayer.getInputFromHuman();
//                row = cont[0];
//                column = cont[1];
//            }while(!pentagoBoard.addMarbleToBoard(TYPE.BLACK, row, column));
//            pentagoBoard.print();
//            if(pentagoBoard.gameOver(true)){
//                break;
//            }
//            block = blackPlayer.getBlockNum();
//            clockWise= blackPlayer.getTwistType();
//            pentagoBoard.twist(block, clockWise);

        }

    }


}