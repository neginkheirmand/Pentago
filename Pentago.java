package ir.ac.aut;


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
     * a method to print the board, if the player wants to, the board can be printed with unicodes too :)
     * @param useUniCode
     */
    public void print(boolean useUniCode){
        if(useUniCode==true){
            print();
            return;
        }

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



    public void print(){
//
//        System.out.printf("\033[0;37m"+"    ");
//
//        for(int i=0; i<6; i++){
//            System.out.printf("   %d  ", i);
//            if(i==2){
//                System.out.printf("    ", i);
//            }
//        }
//
//
//
//        System.out.printf(boardColor+"\n    ");
//        for(int i=0; i<6; i++){
//            System.out.printf("+-----");
//            if(i==2){
//                System.out.printf("+   ");
//            }
//        }
//        System.out.println("+");
//
//        for(int i=0; i<6; i++){
//            System.out.printf("\033[0;37m"+" "+(i+1)+"  "+boardColor);
//            for(int j=0; j<6; j++){
//                System.out.printf("|  ");
//                if(getMarbleHouse(i, j).isFull()){
//                    System.out.printf(getMarbleHouse(i, j).getMarble().getColorOfPlayer());
//                    System.out.printf("\u26AB"+"  "+boardColor);
//                }else{
//                    System.out.printf("   ");
//                }
//                if(j==2){
//                    System.out.printf("|   ");
//                }
//            }
//            System.out.println("|");
//
//            System.out.printf("    ");
//            for(int k=0; k<6; k++){
//                System.out.printf("+-----");
//                if(k==2){
//                    System.out.printf("+   ");
//                }
//            }
//            System.out.println("+");
//
//
//            if(i==2){
//                System.out.println();
//        /*
//                System.out.printf("    ");
//                for(int k=0; k<6; k++){
//                    System.out.printf("+-----");
//                    if(k==2){
//                        System.out.printf("+   ");
//                    }
//                }
//                System.out.println("+");
//        */
//                //a abreviation of the code above is:
//                System.out.println("    +-----+-----+-----+   +-----+-----+-----+");
//
//            }
//        }
//

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

}
