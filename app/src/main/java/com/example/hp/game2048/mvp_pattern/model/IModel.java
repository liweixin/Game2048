package com.example.hp.game2048.mvp_pattern.model;

/**
 * Created by lwx on 2016/2/1.
 */
public interface IModel {
    int getStep();
    void addStep();
    int getBlankTotal();
    void addBlankTotal();
    void decBlankTotal();
    int getMapNum(int x, int y);
    void setMapNum(int x, int y, int val);
}
