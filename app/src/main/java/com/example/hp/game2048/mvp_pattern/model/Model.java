package com.example.hp.game2048.mvp_pattern.model;

/**
 * Created by lwx on 2016/2/1.
 */
public class Model implements IModel{
    int step = 0, blankTotal = 16;
    int map[][] = new int[4][4];
    public Model() {
        for(int i=0; i<4; i++)
            for(int j=0; j<4; j++)
                map[i][j] = 0;
    }

    @Override
    public int getStep() {
        return step;
    }

    @Override
    public void addStep() {
        step++;
    }

    @Override
    public int getBlankTotal() {
        return blankTotal;
    }

    @Override
    public void addBlankTotal() {
        blankTotal++;
    }

    @Override
    public void decBlankTotal() {
        blankTotal--;
    }

    @Override
    public int getMapNum(int x, int y) {
        return map[x][y];
    }

    @Override
    public void setMapNum(int x, int y, int val) {
        map[x][y] = val;
    }
}
