package ir.ac.aut;

import java.util.ArrayList;




/**
 * this class holds information about the player and finds and excutes the moves of the ai
 * @author      negin kheirmand <neginkheirmand@aut.ac.ir>
 * @version     1                 (current version number of program)
 */
public class AiPlayer extends Player{

    /**
     * constructor of the class
     * @param typeOfPlayer TYPE of the ai player
     */
    public AiPlayer(TYPE typeOfPlayer){
        super(typeOfPlayer);
    }

    /**
     * this method iterates in the fisrt handed edges of the graph and calles getBestMove method in the necesary ones
     * @param gameBoard
     */
    public void decideNextMove(Pentago gameBoard){//we make sure that the game hasent ended yet
        ArrayList<MarbleHouse> emptyHouse=gameBoard.getEmptyHouses();
        int [] blockNum={0,1,2,3};
        boolean clockWise[]={true, false};
        int maximum=-9999;
        int indexHouses=0;
        int indexClockWise=0;
        int indexBlock=0;

        for (int i = 0; i < emptyHouse.size(); i++) {
            gameBoard.addMarbleToBlock(typePlayer, emptyHouse.get(i).getBlockNum(), emptyHouse.get(i).getYOfHouse(), emptyHouse.get(i).getXOfHouse());
            int SymmetricBlocksAfterAdding=gameBoard.numSymmetricBlocks();
            int countedSymmetrics=0;
            for(int j=0; j<blockNum.length; j++){

                int temp;
                if(gameBoard.rotationSymmetric(j)){
                        if(countedSymmetrics==0) {
                            gameBoard.twist(j, clockWise[0]);

                            temp = getBestMove(gameBoard, 0, -999, 999, TYPE.getOtherType(typePlayer));

                            if (temp > maximum) {
                                maximum = temp;
                                indexHouses = i;
                                indexBlock = j;
                                indexClockWise = 0;
                            }
                            gameBoard.turnBackRotation(j, clockWise[0]);
                            countedSymmetrics++;
                        }else{
                            continue;
                        }
                }else{
                    for(int k=0; k<clockWise.length; k++){
                        gameBoard.twist(j, clockWise[k]);
                        temp=getBestMove(gameBoard, 0, -999, 999, TYPE.getOtherType(typePlayer) );
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
            gameBoard.turnBackMarble(emptyHouse.get(i).getBlockNum(), emptyHouse.get(i).getYOfHouse(), emptyHouse.get(i).getXOfHouse());
        }
        int arr[]=gameBoard.getRowAndColumn(emptyHouse.get(indexHouses).getBlockNum(),emptyHouse.get(indexHouses).getYOfHouse(), emptyHouse.get(indexHouses).getXOfHouse());
        System.out.println("ai : adding a marble to house y= "+ (arr[0]+1)+"  x="+(arr[1]+1));
        gameBoard.addMarbleToBlock(typePlayer, emptyHouse.get(indexHouses).getBlockNum(), emptyHouse.get(indexHouses).getYOfHouse(), emptyHouse.get(indexHouses).getXOfHouse());
        if(gameBoard.gameOver(true)){
            System.exit(0);
        }
        gameBoard.print();
        System.out.printf("going to twist the block number= %d ", indexBlock + 1);
        if(clockWise[indexClockWise]) {
            System.out.println("Clock-wise");
        }else{
            System.out.println("Counter Clock-wise");
        }
        gameBoard.twist(indexBlock,clockWise[indexClockWise]);
        if(gameBoard.gameOver(true)){
            System.exit(0);
        }

        return;
    }

    /**
     * the ai which is the code based in the minimax algorithm and alpha-beta pruning
     * @param gameBoard the game board in which the game is being played
     * @param repetition the depth of the graph
     * @param alpha
     * @param beta
     * @param playerOfTurn the TYPE of the player
     * @return the best points each point of the graph can give
     */
    private int getBestMove(Pentago gameBoard, int repetition,int alpha, int beta, TYPE playerOfTurn ) {
        //beta is the maximum value that a node of minimum cant get
        //alpha is the minimum (known) value between the edges present for a maximumizer node, the minimum that will accept other than alpha should be absolutly bigger than alpha
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
        } else if (repetition == 2) {
            return gameBoard.strategyBoard(this.typePlayer);
        }
        ArrayList<MarbleHouse> emptyHouse = gameBoard.getEmptyHouses();
        int[] blockNum = {0, 1, 2, 3};
        boolean clockWise[] = {true, false};
        int maximum = -999;
        int minimum=999;

        if(typePlayer.name().equals(playerOfTurn.name())) {
            //maximizer node
            //inja beta 999 hast
            //vali ehtemalan alpha ye adadi dare
            for (int i = 0; i < emptyHouse.size(); i++) {

                gameBoard.addMarbleToBlock(playerOfTurn,emptyHouse.get(i).getBlockNum(), emptyHouse.get(i).getYOfHouse(), emptyHouse.get(i).getXOfHouse());
                int SymmetricBlocksAfterAdding=gameBoard.numSymmetricBlocks();
                int countedSymmetrics=0;
                for (int j = 0; j < blockNum.length; j++) {
                    int temp;
                    if (gameBoard.rotationSymmetric(j)) {
                        if(countedSymmetrics==0) {
                            gameBoard.twist(j, clockWise[0]);
                            temp = getBestMove(gameBoard, repetition + 1, alpha, beta, TYPE.getOtherType(TYPE.getOtherType(playerOfTurn)));
                            if (temp > maximum) {
                                maximum = temp;
                            }
                            if (alpha < temp) {
                                alpha = temp;
                            }
                            if (beta <= alpha) {
                                gameBoard.turnBackRotation(j, clockWise[0]);
                                gameBoard.turnBackMarble(emptyHouse.get(i).getBlockNum(), emptyHouse.get(i).getYOfHouse(), emptyHouse.get(i).getXOfHouse());
                                return maximum;
                            }
                            gameBoard.turnBackRotation(j, clockWise[0]);
                            countedSymmetrics++;
                        }else{
                            continue;
                        }
                    } else {
                        for (int k = 0; k < clockWise.length; k++) {
                            gameBoard.twist(j, clockWise[k]);
                            temp = getBestMove(gameBoard, repetition + 1,alpha, beta, TYPE.getOtherType(TYPE.getOtherType(playerOfTurn)));
                            if (temp > maximum) {
                                maximum = temp;
                            }
                            if(alpha<temp){
                                alpha=temp;
                            }
                            if(beta<=alpha){
                                gameBoard.turnBackRotation(j, clockWise[k]);
                                gameBoard.turnBackMarble(emptyHouse.get(i).getBlockNum(), emptyHouse.get(i).getYOfHouse(), emptyHouse.get(i).getXOfHouse());
                                return maximum;
                            }
                            gameBoard.turnBackRotation(j, clockWise[k]);
                        }
                    }
                }
                gameBoard.turnBackMarble(emptyHouse.get(i).getBlockNum(), emptyHouse.get(i).getYOfHouse(), emptyHouse.get(i).getXOfHouse());
            }
            return maximum;
        }else{
            for (int i = 0; i < emptyHouse.size(); i++) {
                gameBoard.addMarbleToBlock(playerOfTurn, emptyHouse.get(i).getBlockNum(), emptyHouse.get(i).getYOfHouse(), emptyHouse.get(i).getXOfHouse());
                int SymmetricBlocksAfterAdding=gameBoard.numSymmetricBlocks();
                int countedSymmetrics=0;
                for (int j = 0; j < blockNum.length; j++) {
                    int temp;
                    if (gameBoard.rotationSymmetric(j)) {
                        if(countedSymmetrics==0) {
                            gameBoard.twist(j, clockWise[0]);
                            temp = getBestMove(gameBoard, repetition + 1, alpha, beta, TYPE.getOtherType(TYPE.getOtherType(playerOfTurn)));
                            if (temp < minimum) {
                                minimum = temp;
                            }
                            if (minimum < beta) {
                                beta = minimum;
                            }
                            if (alpha >= beta) {
                                gameBoard.turnBackRotation(j, clockWise[0]);
                                gameBoard.turnBackMarble(emptyHouse.get(i).getBlockNum(), emptyHouse.get(i).getYOfHouse(), emptyHouse.get(i).getXOfHouse());
                                return minimum;
                            }
                            gameBoard.turnBackRotation(j, clockWise[0]);
                            countedSymmetrics++;
                        }else{
                            continue;
                        }
                    } else {
                        for (int k = 0; k < clockWise.length; k++) {
                            gameBoard.twist(j, clockWise[k]);
                            temp = getBestMove(gameBoard, repetition + 1, alpha, beta, TYPE.getOtherType(TYPE.getOtherType(playerOfTurn)));
                            if (temp < minimum) {
                                minimum = temp;
                            }
                            if(minimum<beta){
                                beta=minimum;
                            }
                            if(alpha>=beta){
                                gameBoard.turnBackRotation(j, clockWise[k]);
                                gameBoard.turnBackMarble( emptyHouse.get(i).getBlockNum(), emptyHouse.get(i).getYOfHouse(), emptyHouse.get(i).getXOfHouse());
                                return minimum;
                            }
                            gameBoard.turnBackRotation(j, clockWise[k]);
                        }
                    }
                }
                gameBoard.turnBackMarble( emptyHouse.get(i).getBlockNum(), emptyHouse.get(i).getYOfHouse(), emptyHouse.get(i).getXOfHouse());
            }
            return minimum;
        }



    }
}
