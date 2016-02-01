package com.example.hp.game2048.mvp_pattern.presenter;

import android.util.Log;

import com.example.hp.game2048.R;
import com.example.hp.game2048.mvp_pattern.model.Model;
import com.example.hp.game2048.mvp_pattern.view.IGameView;
import java.util.Random;

public class GamePresenter implements IGamePresenter{
    public static Point[] delta = {new Point(-1,0), new Point(1,0), new Point(0,-1), new Point(0,1)};//delta move_up, move_down, move_left, move_right
    private Random random;
    IGameView iGameView;
    Model mModel;

    public GamePresenter(IGameView iGameView){
        this.iGameView = iGameView;
        mModel = new Model();
        initMap();
    }
    private void initMap() {
        random = new Random();
        generateTwoNumber();
    }

    public int getIdFromNum(int num) {
        switch (num) {
            case 0:
                return R.drawable.num_0;
            case 2:
                return R.drawable.num_2;
            case 4:
                return R.drawable.num_4;
            case 8:
                return R.drawable.num_8;
            case 16:
                return R.drawable.num_16;
            case 32:
                return R.drawable.num_32;
            case 64:
                return R.drawable.num_64;
            case 128:
                return R.drawable.num_128;
            case 256:
                return R.drawable.num_256;
            case 512:
                return R.drawable.num_512;
            case 1024:
                return R.drawable.num_1024;
            case 2048:
                return R.drawable.num_2048;
            default:
                break;
        }
        return -1;
    }

    public int getStep(){
        return mModel.getStep();
    }
    public int getPosNum(int pos) {
        return mModel.getMapNum(pos/4, pos%4);
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
        mModel.addStep();
        makeNewNumber();
        iGameView.render();
    }
    public void moveUp(){
        //move left
        int j, k;
        for(int i=0; i<4; i++){
            j = 0;
            while(j<4){
                while((j<4)&&(mModel.getMapNum(j, i)==0)) j++; //find an unzero number
                if(j==4) break;  // no more unzero number
                k = j;
                //move it
                while((j>0)&&(mModel.getMapNum(j-1, i)==0)){
                    //swap;
                    mModel.setMapNum(j - 1, i, mModel.getMapNum(j, i));
                    mModel.setMapNum(j, i, 0);
                    j--;
                }
                //check for eliminate, use while instead of if
                while((j>0)&&mModel.getMapNum(j-1, i)==mModel.getMapNum(j, i)){
                    mModel.setMapNum(j-1, i, mModel.getMapNum(j-1, i) * 2 );
                    mModel.setMapNum(j, i, 0);
                    mModel.addBlankTotal();
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
                while((j>-1)&&(mModel.getMapNum(j, i)==0)) j--; //find an unzero number
                if(j==-1) break;  // no more unzero number
                k = j;
                //move it
                while((j<3)&&(mModel.getMapNum(j+1, i)==0)){
                    //swap;
                    mModel.setMapNum(j+1, i, mModel.getMapNum(j, i));
                    mModel.setMapNum(j, i, 0);
                    j++;
                }
                //check for eliminate, use while instead of if
                while((j<3)&&mModel.getMapNum(j+1, i)==mModel.getMapNum(j, i)){
                    mModel.setMapNum(j+1, i, mModel.getMapNum(j+1, i) * 2);
                    mModel.setMapNum(j, i, 0);
                    mModel.addBlankTotal();
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
                while((j<4)&&(mModel.getMapNum(i, j)==0)) j++; //find an unzero number
                if(j==4) break;  // no more unzero number
                k = j;
                //move it
                while((j>0)&&(mModel.getMapNum(i, j-1)==0)){
                    //swap(map[i][j-1], map[i][j]);
                    mModel.setMapNum(i, j-1, mModel.getMapNum(i, j));
                    mModel.setMapNum(i, j, 0);
                    j--;
                }
                //check for eliminate, use while instead of if
                while((j>0)&&mModel.getMapNum(i, j-1)==mModel.getMapNum(i, j)){
                    mModel.setMapNum(i, j-1, mModel.getMapNum(i, j-1) * 2);
                    mModel.setMapNum(i, j, 0);
                    mModel.addBlankTotal();
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
                while((j>-1)&&(mModel.getMapNum(i, j)==0)) j--; //find an unzero number
                if(j==-1) break;  // no more unzero number
                k = j;
                //move it
                while((j<3)&&(mModel.getMapNum(i, j+1)==0)){
                    //swap;
                    mModel.setMapNum(i, j+1, mModel.getMapNum(i, j));
                    mModel.setMapNum(i, j, 0);
                    j++;
                }
                //check for eliminate, use while instead of if
                while((j<3)&&mModel.getMapNum(i, j+1)==mModel.getMapNum(i, j)){
                    mModel.setMapNum(i, j+1, mModel.getMapNum(i, j+1) * 2);
                    mModel.setMapNum(i, j, 0);
                    mModel.addBlankTotal();
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
        int pos = random.nextInt(mModel.getBlankTotal()) + 1;// get a random number from 1 to blankTotal
        Log.e("Random number:" , String.valueOf(pos));
        for(int i=0; i<4; i++)
            for(int j=0; j<4; j++) {
                if (mModel.getMapNum(i, j) == BLANK) pos--;
                if (pos == 0) {
                    mModel.setMapNum(i, j, getTwoOrFour());
                    mModel.decBlankTotal();
                    return;
                }
            }
    }
    private void generateTwoNumber(){
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
}
