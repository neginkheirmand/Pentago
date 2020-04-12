package ir.ac.aut;

import java.util.Scanner;


/**
 * enum containing the differenet TYPEs of marble and respectivly the different TYPEs of player
 */
enum TYPE{
    BLACK,
    RED;
    public static TYPE getOtherType(TYPE thisType){
        if(thisType.name().equals(BLACK.name())){
            return RED;
        }else{
            return BLACK;
        }
    }
}


/**
 * this class holds information about the player
 * @author      negin kheirmand <neginkheirmand@aut.ac.ir>
 * @version     1                 (current version number of program)
 */
public class Player {

    //type of player
    protected final TYPE typePlayer;

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
            System.out.println("\033[1;90m");
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
            column=scan.nextInt();
        }

        int answer[]={row-1, column-1};
        return answer;
    }

    /**
     * get from the user the number of the block which the user wants to rotate and see if that block has Symmetry in the specified way
     * if it has then ignore that block
     * @return the number of the block
     */
    public int getBlockNum(){
        if(this.typePlayer.equals(TYPE.RED)){
            System.out.printf("\033[1;31m");
        }else{
            System.out.printf("\033[1;90m");
        }
        Scanner scan=new Scanner(System.in);
        System.out.println("Please enter the number of block you want to twist:");
        System.out.println("     __________________");
        System.out.println("    |         |        |");
        System.out.println("    |    1    |    2   |");
        System.out.println("    | ________|________|");
        System.out.println("    |         |        |");
        System.out.println("    |    3    |    4   |");
        System.out.println("    | ________|________|");
        int blockNum=scan.nextInt();
        while(blockNum<1||blockNum>4){
            System.out.printf("Please enter a valid number:");
            blockNum=scan.nextInt();
        }
        return blockNum-1;
    }

    /**
     * get twist TYPE from the consule entered by the user
     * @return boolean defining if its clock-wise or not
     */
    public boolean getTwistType(){
        Scanner scan=new Scanner(System.in);
        System.out.println("How do you want to twist the block?\n1) Clock-Wise\n2) Counter Clock-Wise");
        int temp = scan.nextInt();
        while(temp!=1&&temp!=2){
            System.out.printf("Please enter a valid number:\n");
            temp= scan.nextInt();
        }

        if(temp==1){
            return true;
        }else{
            return false;
        }
    }

    /**
     * the play method of the player
     * @param gameBoard the Pentago game board in which the game is being played
     */
    public void play(Pentago gameBoard){
        int repetition=0;


        int cont[];
        int row;
        int column;
        int block;
        boolean clockWise;


        do {
            if(repetition>0){
                System.out.printf("Please enter the address of an empty house of the board\n");
            }
            cont = this.getInputFromHuman();
            row = cont[0];
            column = cont[1];
            repetition++;
        }while(!gameBoard.addMarbleToBoard(this.typePlayer, row, column));
        gameBoard.print();
        if(gameBoard.gameOver(true)){
            System.exit(0);
        }
        block = this.getBlockNum();
        if(!gameBoard.rotationSymmetric(block)) {
            clockWise = this.getTwistType();
            gameBoard.twist(block, clockWise);
        }
        System.out.println("done getting input from "+this.typePlayer);

    }
}
