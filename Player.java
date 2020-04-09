package ir.ac.aut;

import java.util.Scanner;

enum TYPE{
    BLACK,
    RED
}
public class Player {

    //type of player
    private final TYPE typePlayer;

    /**
     * constructor of the class
     * @param typePlayer
     */
    public Player(TYPE typePlayer){
        this.typePlayer=typePlayer;
    }

    /**
     * this method has to take in input from human player in each round
     * @return answer is an array with the row and column
     */
    public int[] getInputFromHuman(){
        Scanner scan=new Scanner(System.in);
        int row, column;
        if(typePlayer.name().equals("BLACK")){
            System.out.println("\033[1;30m");
            //change the output color to black since the message is for the black player
        }
        else{
            System.out.println("\033[1;31m");
        }

        System.out.println("Please enter the row of the Marble:");
        row= scan.nextInt();
        while(row<0||row>6){
            System.out.println("Please enter a valid number");
            row= scan.nextInt();
        }

        System.out.println("Please enter the column of the Marble:"+"\033[0m");
        column=scan.nextInt();
        while(column<0|| column>6){
            System.out.println("Please enter a valid number");
        }
        //now we have to understand which block is the player refering to
        /*
         * Blocks enumerating:
         *     __________________
         *    |         |        |
         *    |    1    |    2   |
         *    | ________|________|
         *    |         |        |
         *    |    3    |    4   |
         *    | ________|________|
         */


        int blockNumber;
        if(row<4&&column<4){
            blockNumber=1;
        }
        else if(row<4&&column>3){
            blockNumber=2;
        }
        else if(row>3&&column<4){
            blockNumber=3;
        }
        else{
            blockNumber=4;
        }
        int answer[]={row-1, column-1, blockNumber-1};
        return answer;
    }


}
