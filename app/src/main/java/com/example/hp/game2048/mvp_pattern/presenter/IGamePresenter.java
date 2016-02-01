package com.example.hp.game2048.mvp_pattern.presenter;

/**
 * Created by lwx on 2016/2/1.
 */
public interface IGamePresenter {
    int getPosNum(int position);
    int getStep();
    void move(int direction);
    int BLANK = 0;
    int MOVE_UP = 0, MOVE_DOWN = 1, MOVE_LEFT = 2, MOVE_RIGHT = 3;
}
