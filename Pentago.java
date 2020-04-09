package ir.ac.aut;


import java.lang.reflect.Type;
import java.util.Scanner;

public class Pentago
{
    private final String boardColor;
    private Block[] blocks;


    /**
     * Constructor for this class
     */
    public Pentago(){
        boardColor=chooseBoardColor();
        blocks= new Block[4];
        for(int i=0; i<4; i++){
            blocks[i]=new Block();
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
        blocks[numberOfBlock].notch(isClockWise);
        return;
    }

    /**
     * a method to print the board
     */
    public void print(){

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
                if(getMarbleHouse(i, j).isFull()){
                    System.out.printf(getMarbleHouse(i, j).getMarble().getColorOfPlayer());
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
        //a abreviation of the code above is:
            System.out.println("    +-----+-----+-----+   +-----+-----+-----+");

            }
        }



    }

    private  MarbleHouse getMarbleHouse(int row, int column){
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


    public boolean gameOver(){
        //horizontally
        int numContinousBlack=0;
        int numContinousRed=0;
        for(int i=0; i<6; i++){
            for(int j=1; j<5; j++){
                if(getMarbleHouse(i, j).isFull()){
                    if(getMarbleHouse(i, j).getMarble().getTypeOfMarble().equals(TYPE.BLACK)){
                        numContinousBlack++;
                    }else{
                        numContinousRed++;
                    }

                }else{
                    break;
                }
            }
            if(numContinousBlack==4){
                    if(getMarbleHouse(i, 0).isFull() && getMarbleHouse(i, 0).getMarble().getTypeOfMarble().equals(TYPE.BLACK)){
                        return true;
                    }else if(getMarbleHouse(i, 5).isFull() && getMarbleHouse(i, 5).getMarble().getTypeOfMarble().equals(TYPE.BLACK)){
                        return true;
                    }
            } else if(numContinousRed==4){
                if(getMarbleHouse(i, 0).isFull() && getMarbleHouse(i, 0).getMarble().getTypeOfMarble().equals(TYPE.RED)){
                    return true;
                }else if(getMarbleHouse(i, 5).isFull() && getMarbleHouse(i, 5).getMarble().getTypeOfMarble().equals(TYPE.RED)){
                    return true;
                }
            }
            numContinousBlack=0;
            numContinousRed=0;
        }


        //vertically
        for(int j=0; j<6; j++){
            for(int i=1; i<5; i++){
                if(getMarbleHouse(i, j).isFull()){
                    if(getMarbleHouse(i, j).getMarble().getTypeOfMarble().equals(TYPE.BLACK)){
                        numContinousBlack++;
                    }else{
                        numContinousRed++;
                    }

                }else{
                    break;
                }
            }
            if(numContinousBlack==4){
                if(getMarbleHouse(0,j).isFull() && getMarbleHouse(0, j).getMarble().getTypeOfMarble().equals(TYPE.BLACK)){
                    return true;
                }else if(getMarbleHouse(0, j).isFull() && getMarbleHouse(5, j).getMarble().getTypeOfMarble().equals(TYPE.BLACK)){
                    return true;
                }
            } else if(numContinousRed==4){
                if(getMarbleHouse(0, j).isFull() && getMarbleHouse(0, j).getMarble().getTypeOfMarble().equals(TYPE.RED)){
                    return true;
                }else if(getMarbleHouse(5, j).isFull() && getMarbleHouse(5, j).getMarble().getTypeOfMarble().equals(TYPE.RED)){
                    return true;
                }
            }
            numContinousBlack=0;
            numContinousRed=0;
        }


        //diagonal(/)
        for(int i=5, j=0; j<6 && i>=0; i--, j++){
            if(getMarbleHouse(i, j).isFull()){
                if(getMarbleHouse(i, j).getMarble().getTypeOfMarble().equals(TYPE.RED)){
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
            return true;
        }else if(numContinousRed==5){
            return true;
        }
        numContinousBlack=0;
        numContinousRed=0;

        for(int i=4, j=0; i>=0; i--, j++){
            if(getMarbleHouse(i, j).isFull()){
                if(getMarbleHouse(i, j).getMarble().getTypeOfMarble().equals(TYPE.RED)){
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
            return true;
        }else if(numContinousRed==5){
            return true;
        }
        numContinousBlack=0;
        numContinousRed=0;

        for(int i=5, j=1; j<6; j++, i--){
            if(getMarbleHouse(i, j).isFull()){
                if(getMarbleHouse(i, j).getMarble().getTypeOfMarble().equals(TYPE.BLACK)){
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
            return true;
        }else if(numContinousRed==5){
            return true;
        }
        numContinousBlack=0;
        numContinousRed=0;


        //diagona(\)
        for(int i=0,j=0; i<6; i++, j++){
            if(getMarbleHouse(i, j).isFull()) {
                if (getMarbleHouse(i, j).getMarble().getTypeOfMarble().equals(TYPE.BLACK)) {
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
            return true;
        }else if(numContinousRed==5){
            return true;
        }
        numContinousBlack=0;
        numContinousRed=0;

        for(int i=0, j=1; j<6; j++, i++){
            if(getMarbleHouse(i, j).isFull()){
                if(getMarbleHouse(i,j).getMarble().getTypeOfMarble().equals(TYPE.BLACK) ){
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
            return true;
        }else if(numContinousRed==5){
            return true;
        }
        numContinousBlack=0;
        numContinousRed=0;

        for(int i=1, j=0; i<6; i++, j++){
            if(getMarbleHouse(i, j).isFull()) {
                if (getMarbleHouse(i, j).getMarble().getTypeOfMarble().equals(TYPE.RED)) {
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
            return true;
        }else if(numContinousRed==5){
            return true;
        }


        return false;
    }
}
