package com.example.hp.game2048;

import android.util.Log;

import java.util.Random;

/**
 * Created by HP on 2015/10/9.
 */
public class Logic {
    public final static int BLANK = 0;
    public final static int MOVE_UP = 0, MOVE_DOWN = 1, MOVE_LEFT = 2, MOVE_RIGHT = 3;
    public static Point[] delta = {new Point(-1,0), new Point(1,0), new Point(0,-1), new Point(0,1)};//delta move_up, move_down, move_left, move_right
    private int map[][] = new int[4][4];
    private int blankTotal = 16;
    private Random random;
    private int step = 0;

    public Logic(){
        for(int i=0; i<4; i++)
            for(int j=0; j<4; j++)
                map[i][j] = 0;
        random = new Random();
        mapInitial();
    }

    public int getStep(){
        return step;
    }
    public int getPosNum(int pos){
       return map[pos/4][pos%4];
   }
    public void move(int direction){
        switch (direction){
            case MOVE_UP:
                moveUp();
                break;
            case MOVE_DOWN:
                moveDown();
                break;
            case MOVE_LEFT:
                moveLeft();
                break;
            case MOVE_RIGHT:
                moveRight();
                break;
            default:
                Log.e("Move direction: ", "Undefined direction");
                break;
        }
        step++;
        makeNewNumber();
    }
    public void moveUp(){
        //move left
        int j, k;
        for(int i=0; i<4; i++){
            j = 0;
            while(j<4){
                while((j<4)&&(map[j][i]==0)) j++; //find an unzero number
                if(j==4) break;  // no more unzero number
                k = j;
                //move it
                while((j>0)&&(map[j-1][i]==0)){
                    //swap;
                    map[j-1][i] = map[j][i];
                    map[j][i] = 0;
                    j--;
                }
                //check for eliminate, use while instead of if
                while((j>0)&&map[j-1][i]==map[j][i]){
                    map[j-1][i] = map[j-1][i] * 2;
                    map[j][i] = 0;
                    blankTotal++;
                    j--;
                }
                //find next unzero number, start from k + 1
                j = k + 1;
            }
        }
    }
    public void moveDown(){
        //move left
        int j, k;
        for(int i=0; i<4; i++){
            j = 3;
            while(j>=0){
                while((j>-1)&&(map[j][i]==0)) j--; //find an unzero number
                if(j==-1) break;  // no more unzero number
                k = j;
                //move it
                while((j<3)&&(map[j+1][i]==0)){
                    //swap;
                    map[j+1][i] = map[j][i];
                    map[j][i] = 0;
                    j++;
                }
                //check for eliminate, use while instead of if
                while((j<3)&&map[j+1][i]==map[j][i]){
                    map[j+1][i] = map[j+1][i] * 2;
                    map[j][i] = 0;
                    blankTotal++;
                    j++;
                }
                //find next unzero number, start from k + 1
                j = k - 1;
            }
        }
    }
    public void moveLeft(){
        //move left
        int j, k;
        for(int i=0; i<4; i++){
            j = 0;
            while(j<4){
                while((j<4)&&(map[i][j]==0)) j++; //find an unzero number
                if(j==4) break;  // no more unzero number
                k = j;
                //move it
                while((j>0)&&(map[i][j-1]==0)){
                    //swap(map[i][j-1], map[i][j]);
                    map[i][j-1] = map[i][j];
                    map[i][j] = 0;
                    j--;
                }
                //check for eliminate, use while instead of if
                while((j>0)&&map[i][j-1]==map[i][j]){
                    map[i][j-1] = map[i][j-1] * 2;
                    map[i][j] = 0;
                    blankTotal++;
                    j--;
                }
                //find next unzero number, start from k + 1
                j = k + 1;
            }
        }
    }
    public void moveRight(){
        //move left
        int j, k;
        for(int i=0; i<4; i++){
            j = 3;
            while(j>=0){
                while((j>-1)&&(map[i][j]==0)) j--; //find an unzero number
                if(j==-1) break;  // no more unzero number
                k = j;
                //move it
                while((j<3)&&(map[i][j+1]==0)){
                    //swap;
                    map[i][j+1] = map[i][j];
                    map[i][j] = 0;
                    j++;
                }
                //check for eliminate, use while instead of if
                while((j<3)&&map[i][j+1]==map[i][j]){
                    map[i][j+1] = map[i][j+1] * 2;
                    map[i][j] = 0;
                    blankTotal++;
                    j++;
                }
                //find next unzero number, start from k + 1
                j = k - 1;
            }
        }
    }

    private int getTwoOrFour(){
        int rdmNum = random.nextInt(3);
        if (rdmNum==0) return 4;
            else return 2;
    }
    private void makeNewNumber(){
        int pos = random.nextInt(blankTotal) + 1;// get a random number from 1 to blankTotal
        Log.e("Random number:" , String.valueOf(pos));
        for(int i=0; i<4; i++)
            for(int j=0; j<4; j++) {
                if (map[i][j] == BLANK) pos--;
                if (pos == 0) {
                    map[i][j] = getTwoOrFour();
                    blankTotal --;
                    Log.e("Blank total: ", String.valueOf(blankTotal));
                    return;
                }
            }
    }
    private void mapInitial(){
        makeNewNumber();
        makeNewNumber();
    }

    private static class Point{
        private int x,y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        public int getX(){
            return x;
        }
        public int getY(){
            return y;
        }
        private void setX(int x){
            this.x = x;
        }
        private void setY(int y){
            this.y = y;
        }
    }
    private class UpdateArray{
        int head,tail;
        int list[];
    }

    //test for basic move
    private void mapInitialTest1(){
        map[0][0] = 2;
        map[0][1] = 4;
        map[0][2] = 8;
        map[0][3] = 16;
        map[1][3] = 32;
        map[2][1] = 2;
        map[2][3] = 4;
    }
    //Test for move left or right
    private void mapInitialTest2(){
        map[0][0] = 2;
        map[0][1] = 2;
        map[1][0] = 4;
        map[1][1] = 4;
        map[1][2] = 8;
        map[1][3] = 8;
        map[2][1] = 2;
        map[2][2] = 2;
        map[2][3] = 2;
        map[3][0] = 2;
        map[3][1] = 2;
        map[3][2] = 2;
        map[3][3] = 2 ;
    }
    private void mapInitialForMoveTest(){
        map[0][0] = 2;
        map[0][1] = 2;
        map[1][0] = 4;
        map[1][1] = 4;
        map[1][2] = 8;
        map[1][3] = 8;
        map[2][1] = 2;
        map[2][2] = 2;
        map[2][3] = 2;
        map[3][0] = 2;
        map[3][1] = 2;
        map[3][2] = 2;
        map[3][3] = 2;
        blankTotal = 3;
    }
}
