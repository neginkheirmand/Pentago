package ir.ac.aut;

import java.util.ArrayList;
import java.util.Scanner;



/**
 * this class holds information about the board
 * @author      negin kheirmand <neginkheirmand@aut.ac.ir>
 * @version     1                 (current version number of program)
 */

public class Pentago
{
    //the color of the board for the print method
    private final String boardColor;
    //the Array of blocks
    private Block[] blocks;


    /**
     * Constructor for this class
     */
    public Pentago(){
        boardColor=chooseBoardColor();
        blocks= new Block[4];
        for(int i=0; i<4; i++){
            blocks[i]=new Block(i);
        }
    }

    /**
     * method to take in input about the color of the board;
     * @return String with the color unicode
     */
    private String chooseBoardColor(){
        System.out.println("choose one color for the board:");
        String[][] colors= {
//                {"\033[1;30m","BLACK"},
//                {"\033[1;31m", "RED"},
                {"\033[1;32m", "GREEN"},
                {"\033[1;33m", "YELLOW"},
                {"\033[1;34m", "BLUE"},
                {"\033[1;35m", "PURPLE"},
                {"\033[1;36m", "CYAN"},
//                {"\033[1;37m", "WHITE"}
                //because the color of the intellij interferes with the white color
        };
        for(int i=0; i<colors.length; i++){
            System.out.println("\033[1;91m"+(i+1)+colors[i][0]+colors[i][1]);
        }
        Scanner scan=new Scanner(System.in);
        int choosenColor= scan.nextInt();
        while(choosenColor<=0||choosenColor>colors.length){
            System.out.println("Please enter a valid number:");
            choosenColor= scan.nextInt();
        }
        return colors[choosenColor-1][0];
    }

    /**
     * a method to handle the twisting of the blocks in this board
     * @param numberOfBlock the block number is the index of that block in the array of Blocks (field of this class)
     * @param isClockWise the type of twisting
     */
    public void twist(int numberOfBlock, boolean isClockWise){
        //the block num varies from 0 to 3
        blocks[numberOfBlock].notch(isClockWise);
        return;
    }

    /**
     * a method to print the board
     */
    public void print(){



//        System.out.printf("THE STRATEGY OF THIS BOARD FOR THE BLACK PLAYER IS %d\n", strategyBoard(TYPE.BLACK));
//        System.out.printf("THE STRATEGY OF THIS BOARD FOR THE RED PLAYER IS %d\n", strategyBoard(TYPE.RED));
        System.out.printf("\033[1;94m"+"    ");

        for(int i=1; i<7; i++){
            System.out.printf("   %d  ", i);
            if(i==3){
                System.out.printf("    ", i);
            }
        }



        System.out.printf(boardColor+"\n    ");
        for(int i=0; i<6; i++){
            System.out.printf("+-----");
            if(i==2){
                System.out.printf("+   ");
            }
        }
        System.out.println("+");

        for(int i=0; i<6; i++){
            System.out.printf("\033[1;94m"+" "+(i+1)+"  "+boardColor);
            for(int j=0; j<6; j++){
                System.out.printf("|  ");
                if(getMarbleHouseInBoard(i, j).isFull()){
                    System.out.printf(getMarbleHouseInBoard(i, j).getMarble().getColorOfPlayer());
                    System.out.printf("O  "+boardColor);
                }else{
                    System.out.printf("   ");
                }
                if(j==2){
                    System.out.printf("|   ");
                }
            }
            System.out.println("|");

            System.out.printf("    ");
            for(int k=0; k<6; k++){
                System.out.printf("+-----");
                if(k==2){
                    System.out.printf("+   ");
                }
            }
            System.out.println("+");


            if(i==2){
            System.out.println();
        /*
                System.out.printf("    ");
                for(int k=0; k<6; k++){
                    System.out.printf("+-----");
                    if(k==2){
                        System.out.printf("+   ");
                    }
                }
                System.out.println("+");
        */
        //an abreviation of the code above is:
            System.out.println("    +-----+-----+-----+   +-----+-----+-----+");

            }
        }



    }

    /**
     * method to find a house in the block only by looking at its row and column
     * @param row this number is in [0,5]
     * @param column this number is in [0,5]
     * @return
     */
    private MarbleHouse getMarbleHouseInBoard(int row, int column){
        //the rows and columns are counted from 0 to 5
        if(row<3&&column<3){
            return  blocks[0].getMarbleHouse(row, column);
        }
        else if(row<3&&column>2){
            return  blocks[1].getMarbleHouse(row, (column%3));
        }
        else if(row>2&&column<3){
            return  blocks[2].getMarbleHouse((row%3), column);
        }
        else{
            return  blocks[3].getMarbleHouse((row%3), (column%3));
        }
    }

    /**
     * this method return an array list of MarbleHouse s that are empty
     * @return
     */
    public ArrayList<MarbleHouse> getEmptyHouses(){
        ArrayList<MarbleHouse> emptyHouses = new ArrayList<MarbleHouse>();

        for(int b=0; b<4; b++){
            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){
                    if(getMarbleHouseOfBlock(b, i, j).isFull()==false){
                        //is empty
                        emptyHouses.add(getMarbleHouseOfBlock(b, i, j));
                    }
                }
            }
        }
        return emptyHouses;
    }

    /**
     * this method is meant to help the consule output in the ai class
     * @param blockNum the block number of the marble being added
     * @param row the row in which the marble is being added
     * @param column the column in which the marble is being added
     * @return a 2 house array, the first one is the row and the second one is the column
     */
    public int[] getRowAndColumn(int blockNum, int row, int column){
        if(blockNum==0){
            int arr[]= {row, column};
            return arr;
        }else if(blockNum==1){
            int arr[]={row, column+3};
            return arr;
        }else if(blockNum==2){
            int arr[]={row+3, column};
            return arr;

        }else{
            int arr[]={row+3, column+3};
            return arr;
        }
    }

    /**
     * a method which adds a marble to the board in the specified location if possible and return true if was able to and if if was not able to then returns false
     * @param playerType the Type of player
     * @param row the row in ehich the marble is added
     * @param column the column in which the marble is added
     * @return true if was able to and false if it wasnt
     */
    public boolean addMarbleToBoard(TYPE playerType, int row, int column){
        if(getMarbleHouseInBoard(row, column).isFull()){
            return false;
        }else{
           getMarbleHouseInBoard(row,column).putMarble(playerType);
            return true;
        }

//        if(getMarbleHouseInBlock(blockNum, row, column).isFull()){
//            return false;
//        }else{
//            getMarbleHouseInBlock(blockNum, row, column).putMarble(playerType);
//            return true;
//        }
    }

    /**
     *
     * method to find a house in the block only by looking at its row and column
     * @param row this number is in [0,2]
     * @param column this number is in [0,2]
     * @param blockNum the block in which the marble is being added to
     * @return
     */
    public MarbleHouse getMarbleHouseOfBlock(int blockNum, int row, int column){
        return blocks[blockNum].getMarbleHouse(row, column);
    }

    /**
     * method to add marble to a block with the given number of block
     * @param playerType the TYPE of player adding the marble
     * @param blockNum the number of the block [0,3]
     * @param row the row [0,2]
     * @param column the column [0,2]
     */
    public void addMarbleToBlock(TYPE playerType, int blockNum, int row, int column){
        //we are sure that the choosen place is not already full since the method of checking that point has been called before this one
        getMarbleHouseOfBlock(blockNum, row, column).putMarble(playerType);
    }

    /**
     * the method to take back the marble which was added
     * @param blockNum the block number in which was added
     * @param row the row in which was added [0,2]
     * @param column the column in which was added [0,2]
     */
    public void turnBackMarble(int blockNum, int row, int column){
        getMarbleHouseOfBlock(blockNum, row, column).hollowHouse();
        return;
    }

    /**
     * a method which defines the state of the game
     * States:
     * if the return is -1 : the game continues
     * if the return is 0 : there is no space left in the board but nobody wins cause there is no pair of 5
     * if the return is 1 : the black player wins
     * if the return is 2 : the red player wins
     * if the return is 3 : there is a pair of 5 of both players in the board (draw)
     *
     * @return the number of state
     */
    public int winner(){

        boolean redWin=false;
        boolean blackWin=false;
        boolean emptyHouse =false;


        for(int i=0; i<6; i++){
            for(int j=0; j<6; j++){
                if(!getMarbleHouseInBoard(i, j).isFull()){
                    emptyHouse=true;
                }
            }
        }
        //if empty house is false the game is over

        //horizontally
        int numContinousBlack=0;
        int numContinousRed=0;
        for(int i=0; i<6; i++){
            for(int j=1; j<5; j++){
                if(getMarbleHouseInBoard(i, j).isFull()){
                    if(getMarbleHouseInBoard(i, j).getMarble().getTypeOfMarble().equals(TYPE.BLACK)){
                        numContinousBlack++;
                    }else{
                        numContinousRed++;
                    }

                }else{
                    break;
                }
            }
            if(numContinousBlack==4){
                if(getMarbleHouseInBoard(i, 0).isFull() && getMarbleHouseInBoard(i, 0).getMarble().getTypeOfMarble().equals(TYPE.BLACK)){
                    blackWin = true;
                }else if(getMarbleHouseInBoard(i, 5).isFull() && getMarbleHouseInBoard(i, 5).getMarble().getTypeOfMarble().equals(TYPE.BLACK)){
                    blackWin = true;
                }
            } else if(numContinousRed==4){
                if(getMarbleHouseInBoard(i, 0).isFull() && getMarbleHouseInBoard(i, 0).getMarble().getTypeOfMarble().equals(TYPE.RED)){
                    redWin = true;
                }else if(getMarbleHouseInBoard(i, 5).isFull() && getMarbleHouseInBoard(i, 5).getMarble().getTypeOfMarble().equals(TYPE.RED)){
                    redWin = true;
                }
            }
            numContinousBlack=0;
            numContinousRed=0;
        }


        //vertically
        for(int j=0; j<6; j++){
            for(int i=1; i<5; i++){
                if(getMarbleHouseInBoard(i, j).isFull()){
                    if(getMarbleHouseInBoard(i, j).getMarble().getTypeOfMarble().equals(TYPE.BLACK)){
                        numContinousBlack++;
                    }else{
                        numContinousRed++;
                    }

                }else{
                    break;
                }
            }
            if(numContinousBlack==4){
                if(getMarbleHouseInBoard(0,j).isFull() && getMarbleHouseInBoard(0, j).getMarble().getTypeOfMarble().equals(TYPE.BLACK)){
                    blackWin = true;
                }else if(getMarbleHouseInBoard(5, j).isFull() && getMarbleHouseInBoard(5, j).getMarble().getTypeOfMarble().equals(TYPE.BLACK)){
                    blackWin = true;
                }
            } else if(numContinousRed==4){
                if(getMarbleHouseInBoard(0, j).isFull() && getMarbleHouseInBoard(0, j).getMarble().getTypeOfMarble().equals(TYPE.RED)){
                    redWin = true;
                }else if(getMarbleHouseInBoard(5, j).isFull() && getMarbleHouseInBoard(5, j).getMarble().getTypeOfMarble().equals(TYPE.RED)){
                    redWin = true;
                }
            }
            numContinousBlack=0;
            numContinousRed=0;
        }


        //diagonal(/)
        for(int i=5, j=0; j<6 && i>=0; i--, j++){
            if(getMarbleHouseInBoard(i, j).isFull()){
                if(getMarbleHouseInBoard(i, j).getMarble().getTypeOfMarble().equals(TYPE.RED)){
                    numContinousRed++;
                }else{
                    numContinousBlack++;
                }
            }else{
                if(i!=5&&i!=0){
                    break;
                }
            }
        }
        if(numContinousBlack==5){
            blackWin = true;
        }else if(numContinousRed==5){
            redWin = true;
        }
        numContinousBlack=0;
        numContinousRed=0;

        for(int i=4, j=0; i>=0; i--, j++){
            if(getMarbleHouseInBoard(i, j).isFull()){
                if(getMarbleHouseInBoard(i, j).getMarble().getTypeOfMarble().equals(TYPE.RED)){
                    numContinousRed++;
                }else if(numContinousRed==0){
                    numContinousBlack++;
                }else{
                    break;
                }
            }else{
                break;
            }
        }
        if(numContinousBlack==5){
            blackWin = true;
        }else if(numContinousRed==5){
            redWin = true;
        }
        numContinousBlack=0;
        numContinousRed=0;

        for(int i=5, j=1; j<6; j++, i--){
            if(getMarbleHouseInBoard(i, j).isFull()){
                if(getMarbleHouseInBoard(i, j).getMarble().getTypeOfMarble().equals(TYPE.BLACK)){
                    numContinousBlack++;
                }else if(numContinousBlack==0){
                    numContinousRed++;
                }else{
                    break;
                }
            }else{
                break;
            }
        }
        if(numContinousBlack==5){
            blackWin = true;
        }else if(numContinousRed==5){
            redWin = true;
        }
        numContinousBlack=0;
        numContinousRed=0;


        //diagona(\)
        for(int i=0,j=0; i<6; i++, j++){
            if(getMarbleHouseInBoard(i, j).isFull()) {
                if (getMarbleHouseInBoard(i, j).getMarble().getTypeOfMarble().equals(TYPE.BLACK)) {
                    numContinousBlack++;
                }else {
                    numContinousRed++;
                }
            } else{
                if(i!=0&&i!=5){
                    break;
                }
            }
        }
        if(numContinousBlack==5){
            blackWin = true;
        }else if(numContinousRed==5){
            redWin = true;
        }
        numContinousBlack=0;
        numContinousRed=0;

        for(int i=0, j=1; j<6; j++, i++){
            if(getMarbleHouseInBoard(i, j).isFull()){
                if(getMarbleHouseInBoard(i,j).getMarble().getTypeOfMarble().equals(TYPE.BLACK) ){
                    if(numContinousRed!=0){
                        break;
                    }else{
                        numContinousBlack++;
                    }
                }else if( numContinousBlack==0){
                    numContinousRed++;
                }else{
                    break;
                }
            }else{
                break;
            }
        }
        if(numContinousBlack==5){
            blackWin = true;
        }else if(numContinousRed==5){
            redWin = true;
        }
        numContinousBlack=0;
        numContinousRed=0;

        for(int i=1, j=0; i<6; i++, j++){
            if(getMarbleHouseInBoard(i, j).isFull()) {
                if (getMarbleHouseInBoard(i, j).getMarble().getTypeOfMarble().equals(TYPE.RED)) {
                    if (numContinousBlack == 0) {
                        numContinousRed++;
                    } else {
                        break;
                    }
                }else if(numContinousRed==0){
                    numContinousBlack++;
                }else{
                    break;
                }
            }else{
                break;
            }
        }
        if(numContinousBlack==5){
            blackWin = true;
        }else if(numContinousRed==5){
            redWin = true;
        }


        if(redWin && blackWin){
            //draw
            return 3;
        }
        else if(redWin){
            //red wins
            return 2;
        }else if(blackWin){
            //black wins
            return 1;
        }
        else if(emptyHouse==false){
            //no winner
            return 0;
        }
        //the game is not over
        return -1;
    }

    /**
     * this method returns a boolean defining if the game is over yet or not
     * @param print if this boolean is true then this method will print out the state of the game
     * @return a boolean refering to the state of the game
     */
    public boolean gameOver(boolean print){           //CHECK IT ONE LAST TIME
        int game = winner();
        if(print && game>=0) {
            print();
            if (game == 0) {
                System.out.printf(" game ended with no winner ");
            } else if (game == 3) {
                System.out.println("draw");
            } else {
                System.out.println("\033[1;32m" + "WINNER IS :");
                if (game==2) {
                    System.out.printf("\033[1;31m" + "Red Player");
                } else {
                    System.out.printf("\033[1;30m" + "Black Player");
                }
            }
        }
        if (game >= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * this method should define the number of symmetric blocks in the board game
     * @return the number of symmetric blocks
     */
    public int numSymmetricBlocks(){
        int num=0;
        for(int i=0; i<4; i++){
            if(rotationSymmetric(i)){
                num++;
            }
        }
        return num;
    }

    /**
     * a method to return the pont of the aiplayer in the game while counting the points of the strategy called the triple power
     * @param playerOfTrun the TYPE of the aiplayer
     * @return the points
     */
    private int triplePower(TYPE playerOfTrun){
        int powerPlayer=0;
        int powerOpponent=0;
        int tempPlayer=0;
        int tempOpponent=0;


        //(\)(start point : i=0; j=1)
        for(int i=0, j=1; i<5; i++, j++){
            if(getMarbleHouseInBoard(i, j).isFull()){
                if(getMarbleHouseInBoard(i,j).getType().name().equals(playerOfTrun.name())){
                    tempPlayer++;
                }else{
                    tempOpponent++;
                }
            }
        }
        if(tempOpponent!=0&&tempPlayer!=0){
            tempOpponent=0;
            tempPlayer=0;
        }
        if(tempOpponent==1&&tempPlayer==0){
            tempOpponent=0;
        }else if(tempPlayer==1 && tempOpponent==0){
            tempPlayer=0;
        }
        powerOpponent+=tempOpponent;
        powerPlayer+=tempPlayer;

        //(\)(start point : i=1; j=0)
        tempPlayer=0;
        tempOpponent=0;
        for(int i=1, j=0; i<6; i++, j++){
            if(getMarbleHouseInBoard(i, j).isFull()){
                if(getMarbleHouseInBoard(i,j).getType().name().equals(playerOfTrun.name())){
                    tempPlayer++;
                }else{
                    tempOpponent++;
                }
            }
        }
        if(tempOpponent!=0 && tempPlayer!=0){
            tempOpponent=0;
            tempPlayer=0;
        }
        if(tempOpponent==1&&tempPlayer==0){
            tempOpponent=0;
        }else if(tempPlayer==1 && tempOpponent==0){
            tempPlayer=0;
        }
        powerOpponent+=tempOpponent;
        powerPlayer+=tempPlayer;


        //(/)(start point : i=4; j=0)
        tempPlayer=0;
        tempOpponent=0;
        for(int i=4, j=0; i>=0; i--, j++){
            if(getMarbleHouseInBoard(i, j).isFull()){
                if(getMarbleHouseInBoard(i,j).getType().name().equals(playerOfTrun.name())){
                    tempPlayer++;
                }else{
                    tempOpponent++;
                }
            }

        }
        if(tempOpponent!=0 && tempPlayer!=0){
            tempOpponent=0;
            tempPlayer=0;
        }
        if(tempOpponent==1&&tempPlayer==0){
            tempOpponent=0;
        }else if(tempPlayer==1 && tempOpponent==0){
            tempPlayer=0;
        }
        powerOpponent+=tempOpponent;
        powerPlayer+=tempPlayer;


        //(/)(start point : i=5; j=1)
        tempPlayer=0;
        tempOpponent=0;
        for(int i=5, j=1; j<5; i--, j++){
            if(getMarbleHouseInBoard(i, j).isFull()){
                if(getMarbleHouseInBoard(i,j).getType().name().equals(playerOfTrun.name())){
                    tempPlayer++;
                }else{
                    tempOpponent++;
                }
            }

        }
        if(tempOpponent!=0&&tempPlayer!=0){
            tempOpponent=0;
            tempPlayer=0;
        }
        if(tempOpponent==1&&tempPlayer==0){
            tempOpponent=0;
        }else if(tempPlayer==1 && tempOpponent==0){
            tempPlayer=0;
        }
        powerOpponent+=tempOpponent;
        powerPlayer+=tempPlayer;


        if(powerPlayer>18){
            powerPlayer-=9;
        }
        if(powerOpponent>18){
            powerOpponent-=9;
        }
        return (powerPlayer*9)-(powerOpponent*9);
    }

    /**
     * this method sees if the block with the given number passed to  the method is symmetric in rotation
     * @param numBlock the block number
     * @return true if is symmetric and false if not
     */
    public boolean rotationSymmetric(int numBlock){
        if(blocks[numBlock].hasClockWiseSymmetry()&&blocks[numBlock].hasCounterClockWiseSymmetry()){
            return true;
        }
        return false;
    }

    /**
     * this method should get the point of the ai player
     * @param playerOfTurn the TYPE of the player
     * @return the points
     */
    public int strategyBoard(TYPE playerOfTurn){
        int marbles =0;
        for(int i=0; i<4; i++){
            marbles+=blocks[i].marblesStrategy(playerOfTurn);
        }
        int triplePowerPlay;
        //diagonal of five (the triple power play)
        triplePowerPlay=triplePower(playerOfTurn);
        return marbles + triplePowerPlay;
    }

    /**
     * a method to turn back the rotation maid in the step before, the ai class need this from the board
     * @param numBlock the block number
     * @param clockWise the kind of rotation , if true is clock-wise if false is counter clock-wise
     */
    public void turnBackRotation( int numBlock, boolean clockWise){
        blocks[numBlock].notch(!clockWise);
        return;
    }
}
