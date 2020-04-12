package ir.ac.aut;

import java.util.ArrayList;

public class AiPlayer extends Player{

    public AiPlayer(TYPE typeOfPlayer){
        super(typeOfPlayer);
    }

    public void decideNextMove(Pentago gameBoard){//we make sure that the game hasent ended yet
        ArrayList<MarbleHouse> emptyHouse=gameBoard.getEmptyHouses(this.typePlayer);
        int [] blockNum={0,1,2,3};
        boolean clockWise[]={true, false};
        int maximum=-9999;
        int indexHouses=0;
        int indexClockWise=0;
        int indexBlock=0;

        for (int i = 0; i < emptyHouse.size(); i++) {
            int arr[]= gameBoard.getRowAndColumn(emptyHouse.get(i).getBlockNum(), emptyHouse.get(i).getYOfHouse(), emptyHouse.get(i).getXOfHouse());
            //row=arr[0]
            //column=arr[1]
            gameBoard.addMarbleToBoard(typePlayer, arr[0], arr[1]);
            for(int j=0; j<blockNum.length; j++){
                int temp;
                if(gameBoard.rotationSymmetric(j)){
                    gameBoard.twist(j, clockWise[0]);
    if(i==0&&j==0) {
        System.out.printf("goes in\n");
        gameBoard.print();
    }
                    temp=getBestMove(gameBoard, 0, TYPE.getOtherType(typePlayer) );
    if(i==0&&j==0) {
        System.out.printf("comes out\n\n\n");
        gameBoard.print();
    }
                    if(temp > maximum){
                        maximum=temp;
                        indexHouses=i;
                        indexBlock=j;
                        indexClockWise=0;
                    }
                    gameBoard.turnBackRotation(j, clockWise[0]);
                }else{
                    for(int k=0; k<clockWise.length; k++){
                        gameBoard.twist(j, clockWise[k]);
    if(i==0&&j==0) {
        System.out.printf("goes in\n");
        gameBoard.print();
    }
                        temp=getBestMove(gameBoard, 0, TYPE.getOtherType(typePlayer) );
    if(i==0&&j==0) {
        System.out.printf("comes out\n\n\n");
        gameBoard.print();
    }
                        if(temp > maximum){
                            maximum=temp;
                            indexHouses=i;
                            indexBlock=j;
                            indexClockWise=k;
                        }
                        gameBoard.turnBackRotation(j, clockWise[k]);
                    }
                }
            }
            gameBoard.turnBackMarble(arr[0], arr[1]);
        }

        int arr[]= gameBoard.getRowAndColumn( emptyHouse.get(indexHouses).getBlockNum(), emptyHouse.get(indexHouses).getYOfHouse(), emptyHouse.get(indexHouses).getXOfHouse());
        gameBoard.addMarbleToBoard(typePlayer, arr[0], arr[1]);
        if(gameBoard.gameOver(true)){
            System.exit(0);
        }
        gameBoard.print();
        System.out.printf("going to twist the block number= %d clockWise"+clockWise[indexClockWise]+"\n" ,indexBlock);
        gameBoard.twist(indexBlock,clockWise[indexClockWise]);
        if(gameBoard.gameOver(true)){
            System.exit(0);
        }

        return;
    }

    private int getBestMove(Pentago gameBoard, int repetition, TYPE playerOfTurn ) {
        if (gameBoard.gameOver(false)) {
            //the last rotation has ended the game
            int answer = gameBoard.winner();
            if (answer == 0 || answer == 3) {
                return 0;
            } else if (answer == 2) {
                //red wins
                if (this.typePlayer.name().equals(TYPE.RED.name())) {
                    return 999;
                } else {
                    return -999;
                }
            } else {
                //black wins
                if (this.typePlayer.name().equals(TYPE.BLACK.name())) {
                    return 999;
                } else {
                    return -999;
                }
            }
        } else if (repetition == 1) {
            return gameBoard.strategyBoard(this.typePlayer);
        }
        ArrayList<MarbleHouse> emptyHouse = gameBoard.getEmptyHouses(playerOfTurn);
        int[] blockNum = {0, 1, 2, 3};
        boolean clockWise[] = {true, false};
        int maximum = -999;
        int minimum=999;

        if(typePlayer.name().equals(playerOfTurn.name())) {
            System.out.println("4");
            for (int i = 0; i < emptyHouse.size(); i++) {
                System.out.println("5");
                int arr[]=gameBoard.getRowAndColumn( emptyHouse.get(i).getBlockNum(), emptyHouse.get(i).getYOfHouse(), emptyHouse.get(i).getXOfHouse());
                System.out.println("6");
                gameBoard.addMarbleToBoard(playerOfTurn, arr[0], arr[1]);
                System.out.println("7");
                for (int j = 0; j < blockNum.length; j++) {
                    int temp;
                    if (gameBoard.rotationSymmetric(j)) {
                        gameBoard.twist(j, clockWise[0]);
                        temp = getBestMove(gameBoard, repetition + 1, TYPE.getOtherType(TYPE.getOtherType(playerOfTurn)));
                        if (temp > maximum) {
                            maximum = temp;
                        }
                        gameBoard.turnBackRotation(j, clockWise[0]);
                    } else {
                        for (int k = 0; k < clockWise.length; k++) {
                            gameBoard.twist(j, clockWise[k]);
                            temp = getBestMove(gameBoard, repetition + 1, TYPE.getOtherType(TYPE.getOtherType(playerOfTurn)));
                            if (temp > maximum) {
                                maximum = temp;
                            }
                            gameBoard.turnBackRotation(j, clockWise[k]);
                        }
                    }
                }
                System.out.println("8");
                gameBoard.turnBackMarble(arr[0], arr[1]);
            }
            System.out.println("9");
            return maximum;
        }else{
            for (int i = 0; i < emptyHouse.size(); i++) {
                int arr[]= gameBoard.getRowAndColumn( emptyHouse.get(i).getBlockNum(), emptyHouse.get(i).getYOfHouse(), emptyHouse.get(i).getXOfHouse());
                gameBoard.addMarbleToBoard(playerOfTurn, arr[0], arr[1]);
                for (int j = 0; j < blockNum.length; j++) {
                    int temp;
                    if (gameBoard.rotationSymmetric(j)) {
                        gameBoard.twist(j, clockWise[0]);
                        temp = getBestMove(gameBoard, repetition + 1, TYPE.getOtherType(TYPE.getOtherType(playerOfTurn)));
                        if (temp < minimum) {
                            minimum = temp;
                        }
                        gameBoard.turnBackRotation(j, clockWise[0]);
                    } else {
                        for (int k = 0; k < clockWise.length; k++) {
                            gameBoard.twist(j, clockWise[k]);
                            temp = getBestMove(gameBoard, repetition + 1, TYPE.getOtherType(TYPE.getOtherType(playerOfTurn)));
                            if (temp < minimum) {
                                minimum = temp;
                            }
                            gameBoard.turnBackRotation(j, clockWise[k]);
                        }
                    }
                }
                gameBoard.turnBackMarble( arr[0], arr[1]);
            }
            return minimum;
        }


    }
}
