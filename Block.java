package ir.ac.aut;

import java.lang.reflect.Type;

public class Block {

    private MarbleHouse[][] blockHouses;

    public Block() {
        blockHouses = new MarbleHouse[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                blockHouses[i][j] = new MarbleHouse(j, i);
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

    public MarbleHouse getMarbleHouse(int i, int j) {
        return blockHouses[i][j];
    }

    public TYPE getTypeOfMarble(int i, int j) {
        return blockHouses[i][j].getType();
    }

    public boolean hasClockWiseSymmetry() {
        this.clockWise();
        MarbleHouse[][] cloneBlockHouses = (new Block()).getBlockHouses();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (blockHouses[i][j].isFull()) {
                    cloneBlockHouses[i][j].putMarble(blockHouses[i][j].getType());
                }
            }
        }
        this.counterClockWise();
        int number = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cloneBlockHouses[i][j].equals(blockHouses[i][j])) {
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

        this.counterClockWise();
        MarbleHouse[][] cloneBlockHouses = (new Block()).getBlockHouses();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (blockHouses[i][j].isFull()) {
                    cloneBlockHouses[i][j].putMarble(blockHouses[i][j].getType());
                }
            }
        }
        this.clockWise();
        int number = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (cloneBlockHouses[i][j].equals(blockHouses[i][j])) {
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

    private void setBlockHouses(MarbleHouse[][] newBlockHouses) {
        this.blockHouses = newBlockHouses;
    }

    public int marblesStrategy(TYPE typeOfPlayer) {
        //horizontal
        int num = 0;
        int answer = 0;
        for (int i = 0; i < 3; i++) {
            num = 0;
            for (int j = 0; j < 3; j++) {
                if (blockHouses[i][j].isFull()) {
                    if (getTypeOfMarble(i, j).name().equals(typeOfPlayer.name())) {
                        num++;
                    } else {
                        break;
                    }
                }
                //even if there is a gap in the line we will counted as much as there are at least 2 marbles in a row
            }
            if(num==3) {
                if (i == 1) {
                    answer += 7*num;
                } else {
                    answer += 5*num;
                }
            }else if(num==2){
                if (i == 1) {
                    answer += 5*num;
                } else {
                    answer += 3*num;
                }
            }
        }

        //vertical
        num = 0;
        for (int j = 0; j < 3; j++) {
            num = 0;
            for (int i = 0; i < 3; i++) {
                if (blockHouses[i][j].isFull()) {
                    if (blockHouses[i][j].getType().name().equals(typeOfPlayer.name())) {
                        num++;
                    } else {
                        break;
                    }
                }
            }
            if (num == 3) {
                if(j==1){
                    answer+=5*num;
                }else{
                    answer+=7*num;
                }
            }else if(num==2){
                if(j==1){
                    answer+=3*num;
                }else{
                    answer+=5*num;
                }
            }
        }

        //diagonal(\)
        num = 0;
        for (int i = 0, j = 0; i < 3; j++, i++) {
            if (getMarbleHouse(i, j).isFull() == true) {
                if (getTypeOfMarble(i, j).name().equals(typeOfPlayer.name())) {
                    num++;
                } else {
                    break;
                }
            }
        }
        if (num == 3) {
            //monicas five strategy has 3 points
            answer+=3*num;
        }else if(num==2){
            answer+=1*num;
        }

        //diagonal(/)
        num = 0;
        for (int i = 2, j = 0; i >= 0; i--, j++) {
            if (getMarbleHouse(i, j).isFull()) {
                if (getTypeOfMarble(i, j).name().equals(typeOfPlayer.name())) {
                    num++;
                } else {
                    break;
                }
            }
        }
        if (num == 3) {
            answer+=3*num;
            //monicas five has 3 points of strategy
        }else if(num==2){
            answer+=1*num;
        }
        return answer;
    }

}