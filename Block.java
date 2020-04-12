package ir.ac.aut;



/**
 * this class holds information about each block of the board
 * @author      negin kheirmand <neginkheirmand@aut.ac.ir>
 * @version     1                 (current version number of program)
 */

public class Block {
    //the block number, varies from 0 to 3
    private int blockNum;
    //the bidimensional array of the MarbleHouses
    private MarbleHouse[][] blockHouses;

    /**
     * the constructor
     * @param blockNum variable for setting the blockNum
     */
    public Block(int blockNum) {
        this.blockNum=blockNum;
        blockHouses = new MarbleHouse[3][3];
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                blockHouses[y][x] = new MarbleHouse(y, x, blockNum);
            }
        }
    }

    /**
     * method for doing the rotations of this block
     * @param isClockWise if true then the rotation will be clock-wise and if its false the roattion will be counter clock-wise
     */
    public void notch(boolean isClockWise) {
        if (isClockWise) {
            clockWise();
        } else {
            counterClockWise();
        }
        return;
    }

    /**
     * this method is meant to do specifically the  clock-wise rotation
     */
    private void clockWise() {
        //clock-wise
        Block containerBlock= new Block(0);
        MarbleHouse clock_wise[][] = containerBlock.getBlockHouses();
        for(int x=0; x<3; x++){
            for(int y=0; y<3; y++){
                if(blockHouses[2 - x][y].isFull()) {
                    clock_wise[y][x].putMarble(blockHouses[2 - x][y].getMarble().getTypeOfMarble());
                }
                //else they will stay empty
            }
        }
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(clock_wise[i][j].isFull()) {
                    blockHouses[i][j].putMarble(clock_wise[i][j].getMarble().getTypeOfMarble());
                }else{
                    blockHouses[i][j].hollowHouse();
                }
            }
        }
    }


    /**
     * this method is meant to do specifically the counter clock-wise rotation
     */
    private void counterClockWise() {

        //counter clock-wise
        Block containerBlock= new Block(0);
        MarbleHouse clock_wise[][] = containerBlock.getBlockHouses();
        for(int x=0; x<3; x++){
            for(int y=0; y<3; y++){
                if(blockHouses[y][x].isFull()) {
                    clock_wise[2 - x][y].putMarble(blockHouses[y][x].getMarble().getTypeOfMarble());
                }
                //else they will stay empty
            }
        }
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(clock_wise[i][j].isFull()) {
                    blockHouses[i][j].putMarble(clock_wise[i][j].getMarble().getTypeOfMarble());
                }else{
                    blockHouses[i][j].hollowHouse();
                }
            }
        }
    }

    /**
     * this method should return a reference to a MarbleHouse of this block that has an xOfHouse equal to x and an yOfHouse equal to y
     * @param y passed parameter
     * @param x passed parameter
     * @return
     */
    public MarbleHouse getMarbleHouse(int y, int x) {
        //the parameters passed are between 0 and 3
        if(blockHouses[y][x].getXOfHouse()==x && blockHouses[y][x].getYOfHouse()==y) {
            return blockHouses[y][x];
        }else{
            System.out.println("WHAT THE FUCK");
            System.out.println("y="+y+" x="+x);
            System.out.println("a[y][x].gety="+blockHouses[y][x].getYOfHouse()+"   a[y][x].getx= "+blockHouses[y][x].getXOfHouse());
            return null;
        }

    }


    /**
     * this method is meant to give the Tyoe of the marble in an specific location of the block with the y and x 's specified as the parameters of the method
     * bhefore calling this method we have to make sure that there is actually a marble in this house of the block
     * @param y the y of tyhe specified Marblehouse
     * @param x the y of tyhe specified Marblehouse
     * @return
     */
    private TYPE getTypeOfMarble(int y, int x) {
        return blockHouses[y][x].getType();
    }

    /**
     * this method should evaluate if this block has hasClockWiseSymmetry
     * @return if has hasClockWiseSymmetry return will be true if not the return will be false
     */
    public boolean hasClockWiseSymmetry() {
        int number = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (blockHouses[2-j][i].equals(blockHouses[i][j])) {
                    number++;
                } else {
                    return false;
                }
            }
        }
        if (number == 9) {
            return true;
        }
        return false;
    }

    /**
     * this method should evaluate if this block has hasCounterClockWiseSymmetry
     * @return if has hasCounterClockWiseSymmetry return will be true if not the return will be false
     */
    public boolean hasCounterClockWiseSymmetry() {

        int number = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (blockHouses[j][2-i].equals(blockHouses[i][j])) {
                    number++;
                } else {
                    return false;
                }
            }
        }
        if (number == 9) {
            return true;
        }
        return false;
    }

    /**
     * ths method should return the bidimensional array in the field of the class
     * @return the bidimensional array of MarbleHouses
     */
    private MarbleHouse[][] getBlockHouses() {
        return blockHouses;
    }

    /**
     * this method should search the block and give points to each of the players, the ai and the opponent
     * @param typeOfPlayer the TYPE of the aiPlayer
     * @return the points given to aiPlayer having in counter the points of the opponent
     */
    public int marblesStrategy(TYPE typeOfPlayer) {
        //horizontal
        int numPlayer = 0;
        int numOpponent = 0;
        int answer = 0;
        for (int i = 0; i < 3; i++) {
            numPlayer = 0;
            numOpponent=0;
            for (int j = 0; j < 3; j++) {
                if (blockHouses[i][j].isFull()) {
                    if (getTypeOfMarble(i, j).name().equals(typeOfPlayer.name())) {
                        numPlayer++;
                    } else {
                        numOpponent++;
                    }
                }
                //even if there is a gap in the line we will counted as much as there are at least 2 marbles in a row
            }
            if(numPlayer==3) {
                if (i == 1) {
                    answer += 5*numPlayer;
                } else {
                    answer += 7*numPlayer;
                }
            }else if(numPlayer==2){
                if(numOpponent==0) {
                    if (i == 1) {
                        answer += 3 * numPlayer;
                    } else {
                        answer += 5 * numPlayer;
                    }
                }else{
                    numPlayer=0;
                    numOpponent=0;
                    answer-=2;
                }
            }else if(numOpponent==3){
                if (i == 1) {
                    answer -= 5*numOpponent;
                } else {
                    answer -= 7*numOpponent;
                }
            }else if(numOpponent==2){
                if(numPlayer==0){
                    if(i==1){
                        answer-= 5 * numOpponent;
                    }else{
                        answer-= 7* numOpponent;
                    }
                }
                else{
                    numOpponent=0;
                    numPlayer=0;
                    answer+=4;
                }
            }

        }

        //vertical
        for (int j = 0; j < 3; j++) {
            numPlayer = 0;
            numOpponent=0;
            for (int i = 0; i < 3; i++) {
                if (blockHouses[i][j].isFull()) {
                    if (blockHouses[i][j].getType().name().equals(typeOfPlayer.name())) {
                        numPlayer++;
                    } else {
                        numOpponent++;
                    }
                }
            }
            if(numPlayer==3) {
                if (j == 1) {
                    answer += 5*numPlayer;
                } else {
                    answer += 7*numPlayer;
                }
            }else if(numPlayer==2){
                if(numOpponent==0) {
                    if (j == 1) {
                        answer += 3 * numPlayer;
                    } else {
                        answer += 5 * numPlayer;
                    }
                }else{
                    numPlayer=0;
                    numOpponent=0;
                    answer-=2;
                }
            }else if(numOpponent==3){
                if (j == 1) {
                    answer -= 5*numOpponent;
                } else {
                    answer -= 7*numOpponent;
                }
            }else if(numOpponent==2){
                if(numPlayer==0){
                    if(j==1){
                        answer-= 6 * numOpponent;
                    }else{
                        answer-= 8* numOpponent;
                    }
                }
                else{
                    numOpponent=0;
                    numPlayer=0;
                    answer+=6;
                    //cause was able to stop the marbles of becoming 3
                }
            }

        }

        //diagonal(\)
        for (int i = 0, j = 0; i < 3; j++, i++) {
            numPlayer = 0;
            numOpponent=0;
            if (getMarbleHouse(i, j).isFull() == true) {
                if (getTypeOfMarble(i, j).name().equals(typeOfPlayer.name())) {
                    numPlayer++;
                } else {
                    numOpponent++;
                }
            }

        }
        if(numPlayer==3) {
            answer += 3*numPlayer;

        }else if(numPlayer==2){
            if(numOpponent==0) {
                answer += numPlayer;
            }else{
                numPlayer=0;
                numOpponent=0;
                answer-=2;
            }
        }else if(numOpponent==3){
            answer -= 4 * numOpponent;
            if(blockNum==0||blockNum==3){
                answer--;
            }
        }else if(numOpponent==2){
            if(numPlayer==0){
                if(blockNum==0||blockNum==3){
                    answer--;
                }
                answer -= 3 * numOpponent;
            }
            else{
                numOpponent=0;
                numPlayer=0;
                answer+=4;
            }
        }


        //diagonal(/)
        for (int i = 2, j = 0; i >= 0; i--, j++) {
            numPlayer = 0;
            numOpponent =0;
            if (getMarbleHouse(i, j).isFull()) {
                if (getTypeOfMarble(i, j).name().equals(typeOfPlayer.name())) {
                    numPlayer++;
                } else {
                    numOpponent++;
                }
            }
        }
        if(numPlayer==3) {
            answer += 3*numPlayer;
        }else if(numPlayer==2){
            if(numOpponent==0) {
                answer += numPlayer;
            }else{
                numPlayer=0;
                numOpponent=0;
                answer-=2;
            }
        }else if(numOpponent==3){
            answer -= 4*numOpponent;
            if(blockNum==1||blockNum==2){
                answer--;
            }
        }else if(numOpponent==2){
            if(numPlayer==0){
                answer-= 3 * numOpponent;
                if(blockNum==1||blockNum==2){
                    answer--;
                }
            }
            else{
                numOpponent=0;
                numPlayer=0;
                answer+=4;
            }
        }

        return answer;
    }

}