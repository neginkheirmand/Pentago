package ir.ac.aut;
//all the public access modifiers of this project can be package-private but for the sake of clean coding will let them be as they are


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Pentago pentagoBoard = new Pentago();


        int i;
        System.out.println("Do you want to play against human or against AI?\n1)AI\n2)Human");
        i= (new Scanner(System.in)).nextInt();
        while(i!=1&&i!=2){
            System.out.println("Please enter a valid number");
            i= (new Scanner(System.in)).nextInt();
        }
        if(i==1) {
            //AI
            System.out.println("Do you want to play with the BLACK marbles or the RED marbles?\n1)"+"\033[1;90m"+"BLACK\n2)"+"\033[1;91m"+"RED");
            int j= (new Scanner(System.in)).nextInt();
            while(j!=1&&j!=2){
                System.out.println("Please enter a valid number");
                j= (new Scanner(System.in)).nextInt();
            }
            if(j==2) {
                AiPlayer blackPlayer = new AiPlayer(TYPE.BLACK);
                Player redPlayer = new Player(TYPE.RED);

                while (true) {
                    //turn of red/white player
                    //this prototype is made for the red player(the human player to start)
                    pentagoBoard.print();
                    if (pentagoBoard.gameOver(true)) {
                        break;
                    }
                    redPlayer.play(pentagoBoard);

                    //now turn of black player
                    pentagoBoard.print();
                    if (pentagoBoard.gameOver(true)) {
                        break;
                    }

                    blackPlayer.decideNextMove(pentagoBoard);
                }
            }else{
                AiPlayer redPlayer = new AiPlayer(TYPE.RED);
                Player blackPlayer = new Player(TYPE.BLACK);

                while (true) {
                    //turn of red/white player
                    //this prototype is made for the red player(the ai player to start)
                    pentagoBoard.print();
                    if (pentagoBoard.gameOver(true)) {
                        break;
                    }
                    redPlayer.decideNextMove(pentagoBoard);

                    //now turn of black player
                    pentagoBoard.print();
                    if (pentagoBoard.gameOver(true)) {
                        break;
                    }
                    blackPlayer.play(pentagoBoard);

                }
            }
        }else {
            //Human
            Player blackPlayer = new Player(TYPE.BLACK);
            Player redPlayer = new Player(TYPE.RED);

            while (true) {

                //turn of red/white player
                pentagoBoard.print();
                if (pentagoBoard.gameOver(true)) {
                    break;
                }
                redPlayer.play(pentagoBoard);

                //now turn of black player
                pentagoBoard.print();
                if (pentagoBoard.gameOver(true)) {
                    break;
                }
                blackPlayer.play(pentagoBoard);
            }

        }
    }
}