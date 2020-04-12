package ir.ac.aut;

import java.lang.reflect.Type;

public class Block {
    private int blockNum;
    private MarbleHouse[][] blockHouses;

    public Block(int blockNum) {
        this.blockNum=blockNum;
        blockHouses = new MarbleHouse[3][3];
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                blockHouses[y][x] = new MarbleHouse(x, y, blockNum);
            }
        }
    }

    public void notch(boolean isClockWise) {
        if (isClockWise) {
            clockWise();
        } else {
            counterClockWise();
        }
        return;
    }

    private void clockWise() {
        //        clock-wise
        MarbleHouse clock_wise[][] = new MarbleHouse[3][3];

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                clock_wise[y][x] = blockHouses[2 - x][y];
            }
        }
        blockHouses = clock_wise;
        return;
    }

    private void counterClockWise() {

        //counter clock-wise
        MarbleHouse counter_clock_wise[][] = new MarbleHouse[3][3];
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                counter_clock_wise[y][x] = blockHouses[x][2 - y];
            }
        }
        blockHouses = counter_clock_wise;
        return;

    }

    /**
     * this method should return a reference to a MarbleHouse of this block that has an xOfHouse equal to x and an yOfHouse equal to y
     * @param y passed parameter
     * @param x passed parameter
     * @return
     */
    public MarbleHouse getMarbleHouse(int y, int x) {
//        return blockHouses[y][x];
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(y==blockHouses[i][j].getYOfHouse() && x==blockHouses[i][j].getXOfHouse()){
                    return blockHouses[i][j];
                }
            }
        }
        return null;
    }


    public TYPE getTypeOfMarble(int y, int x) {
        return blockHouses[y][x].getType();
    }

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

    private MarbleHouse[][] getBlockHouses() {
        return blockHouses;
    }

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
                System.out.println("i ="+i+" j="+j);
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
        for (int i = 0, j = 0; i < 3 && j<3; j++, i++) {
            numPlayer = 0;
            numOpponent=0;
            System.out.println("i ="+i+" j="+j);
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
            answer -= 4*numOpponent;

        }else if(numOpponent==2){
            if(numPlayer==0){
                answer-= 3 * numOpponent;
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
            System.out.println("i ="+i+" j="+j);
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

        }else if(numOpponent==2){
            if(numPlayer==0){
                answer-= 3 * numOpponent;
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