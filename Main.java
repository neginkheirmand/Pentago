package ir.ac.aut;



public class Main {
    public static void main(String[] args) {
        Pentago pentagoBoard = new Pentago();
        Player blackPlayer= new Player(TYPE.BLACK);
        Player redPlayer= new Player(TYPE.RED);

        pentagoBoard.PrintBlockxAndy();
        while(true){
//            if(pentagoBoard.rotationSymmetric(0)){
//                System.out.println("1the first block has rotation symmetry");
//            }
//            if(pentagoBoard.rotationSymmetric(1)){
//                System.out.println("2the second block has rotation symmetry");
//            }
//            if(pentagoBoard.rotationSymmetric(2)){
//                System.out.println("3the third block has rotation symmetry");
//            }
//            if(pentagoBoard.rotationSymmetric(3)){
//                System.out.println("4the fourth block has rotation symmetry");
//            }
            //turn of red/white player
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
            blackPlayer.play(pentagoBoard);
        }

    }
}