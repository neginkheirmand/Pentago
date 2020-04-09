package ir.ac.aut;

public class Block {

    private MarbleHouse[][] blockHouses;

    public Block(){
        blockHouses= new MarbleHouse[3][3];
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                blockHouses[i][j]=new MarbleHouse(j, i);
            }
        }
    }

    public void notch( boolean isClockWise){
        if(isClockWise){
        clockWise();
        }else{

        }
        return;
    }

    private void clockWise(){
        //        clock-wise
        MarbleHouse clock_wise[][]= new MarbleHouse[3][3];

        for(int x=0; x<3; x++){
            for(int y=0; y<3; y++){
                clock_wise[y][x]=blockHouses[2-x][y];
            }
        }
        blockHouses= clock_wise;
        return;
    }

    private void counterClockWise(){

        //counter clock-wise
        MarbleHouse counter_clock_wise[][]= new MarbleHouse[3][3];
        for(int x=0; x<3; x++){
            for(int y=0; y<3; y++){
                counter_clock_wise[y][x]=blockHouses[x][2-y];
            }
        }
        blockHouses= counter_clock_wise;
        return;

    }

}