package ir.ac.aut;

import java.util.Scanner;

enum TYPE{
    BLACK,
    RED;
    public static TYPE getOtherType(TYPE thisType){
        if(thisType.equals(BLACK)){
            return RED;
        }else{
            return BLACK;
        }
    }
}
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
        }

        int answer[]={row-1, column-1};
        return answer;
    }

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

}
