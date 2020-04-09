package ir.ac.aut;



public class Main {
    public static void main(String[] args) {
//        (new Pentago()).print(true);
        MarbleHouse marbles [][]= new MarbleHouse[6][6];
        marbles[0][0]=new MarbleHouse(0,0, TYPE.BLACK);
        marbles[0][1]=new MarbleHouse(1,0, TYPE.RED);
        marbles[0][2]=new MarbleHouse(2,0,TYPE.BLACK);
        marbles[0][3]=new MarbleHouse(0,0, TYPE.RED);
        marbles[0][4]=new MarbleHouse(1,0, TYPE.BLACK);
        marbles[0][5]=new MarbleHouse(2,0, TYPE.RED);

        marbles[1][0]=new MarbleHouse(0,1, TYPE.BLACK);
        marbles[1][1]=new MarbleHouse(1,1,TYPE.RED);
        marbles[1][2]=new MarbleHouse(2,1,TYPE.BLACK);
        marbles[1][3]=new MarbleHouse(0,2,TYPE.RED);
        marbles[1][4]=new MarbleHouse(1,2,TYPE.BLACK);
        marbles[1][5]=new MarbleHouse(2,2);

        marbles[2][0]=new MarbleHouse(0,0, TYPE.BLACK);
        marbles[2][1]=new MarbleHouse(1,0, TYPE.RED);
        marbles[2][2]=new MarbleHouse(2,0,TYPE.BLACK);
        marbles[2][3]=new MarbleHouse(0,0,TYPE.RED);
        marbles[2][4]=new MarbleHouse(1,0);
        marbles[2][5]=new MarbleHouse(2,0);

        marbles[3][0]=new MarbleHouse(0,1, TYPE.RED);
        marbles[3][1]=new MarbleHouse(1,1, TYPE.BLACK);
        marbles[3][2]=new MarbleHouse(2,1,TYPE.RED);
        marbles[3][3]=new MarbleHouse(0,2);
        marbles[3][4]=new MarbleHouse(1,2);
        marbles[3][5]=new MarbleHouse(2,2);

        marbles[4][0]=new MarbleHouse(0,1, TYPE.RED);
        marbles[4][1]=new MarbleHouse(1,1, TYPE.BLACK);
        marbles[4][2]=new MarbleHouse(2,1);
        marbles[4][3]=new MarbleHouse(0,2);
        marbles[4][4]=new MarbleHouse(1,2);
        marbles[4][5]=new MarbleHouse(2,2);


        marbles[5][0]=new MarbleHouse(0,1);
        marbles[5][1]=new MarbleHouse(1,1);
        marbles[5][2]=new MarbleHouse(2,1);
        marbles[5][3]=new MarbleHouse(0,2);
        marbles[5][4]=new MarbleHouse(1,2);
        marbles[5][5]=new MarbleHouse(2,2);





        System.out.printf("\033[0;37m"+"    ");

        for(int i=0; i<6; i++){
            System.out.printf("   %d  ", i);
            if(i==2){
                System.out.printf("    ", i);
            }
        }



        System.out.printf("\033[1;35m"+"\n    ");
        for(int i=0; i<6; i++){
            System.out.printf("+-----");
            if(i==2){
                System.out.printf("+   ");
            }
        }
        System.out.println("+");

        for(int i=0; i<6; i++){
            System.out.printf("\033[0;37m"+" "+(i+1)+"  "+"\033[1;35m");
            for(int j=0; j<6; j++){
                System.out.printf("|  ");
                if(marbles[i][j].isFull()){
                    System.out.printf(marbles[i][j].getMarble().getColorOfPlayer());
                    System.out.printf("\u26AB"+"  "+"\033[1;35m");
                }else{
                    System.out.printf("    ");
                }
                if(j==2){
                    System.out.printf("|   ");
                }
            }
            System.out.println("|");

            System.out.printf("    ");
            for(int k=0; k<6; k++){
                System.out.printf("+-----");
                if(k==0||k==2){
                    System.out.printf("-");
                }
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
                System.out.println("    +-----+-----+------+   +-----+-----+-----+");

            }
        }





    }
}





//                         1     2     3
//                      +-----+-----+-----+   +-----+-----+-----+
//                 *    |  O  |  O  |  O  |   |  O  |  O  |  O  |
//                      +-----+-----+-----+   +-----+-----+-----+
//                 *    |  O  |  O  |  O  |   |  O  |  O  |  O  |
//                      +-----+-----+-----+   +-----+-----+-----+
//                 *    |  O  |  O  |  O  |   |  O  |  O  |  O  |
//                      +-----+-----+-----+   +-----+-----+-----+

//                      +-----+-----+-----+   +-----+-----+-----+
//                 *    |  O  |  O  |  O  |   |  O  |  O  |  O  |
