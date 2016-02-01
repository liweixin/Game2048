package com.example.hp.game2048.mvp_pattern.view;

/**
 * Created by lwx on 2016/2/1.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.game2048.R;
import com.example.hp.game2048.mvp_pattern.presenter.GamePresenter;
import com.example.hp.game2048.mvp_pattern.presenter.IGamePresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameActivity extends AppCompatActivity implements IGameView {

    private ImageView[] imageView = new ImageView[16];
    GamePresenter mGamePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initImageView();
        mGamePresenter = new GamePresenter(this);
        render();
    }

    public void render() {
        for (int i = 0; i < 16; i++)
            imageView[i].setImageResource(mGamePresenter.getIdFromNum(mGamePresenter.getPosNum(i)));
        step.setText(String.valueOf(mGamePresenter.getStep()));
    }

    public void initImageView() {
        imageView[0]=grid0;
        imageView[1]=grid1;
        imageView[2]=grid2;
        imageView[3]=grid3;
        imageView[4]=grid4;
        imageView[5]=grid5;
        imageView[6]=grid6;
        imageView[7]=grid7;
        imageView[8]=grid8;
        imageView[9]=grid9;
        imageView[10]=grid10;
        imageView[11]=grid11;
        imageView[12]=grid12;
        imageView[13]=grid13;
        imageView[14]=grid14;
        imageView[15]=grid15;
    }
    @Bind(R.id.grid_0)
    ImageView grid0;
    @Bind(R.id.grid_1)
    ImageView grid1;
    @Bind(R.id.grid_2)
    ImageView grid2;
    @Bind(R.id.grid_3)
    ImageView grid3;
    @Bind(R.id.grid_4)
    ImageView grid4;
    @Bind(R.id.grid_5)
    ImageView grid5;
    @Bind(R.id.grid_6)
    ImageView grid6;
    @Bind(R.id.grid_7)
    ImageView grid7;
    @Bind(R.id.grid_8)
    ImageView grid8;
    @Bind(R.id.grid_9)
    ImageView grid9;
    @Bind(R.id.grid_10)
    ImageView grid10;
    @Bind(R.id.grid_11)
    ImageView grid11;
    @Bind(R.id.grid_12)
    ImageView grid12;
    @Bind(R.id.grid_13)
    ImageView grid13;
    @Bind(R.id.grid_14)
    ImageView grid14;
    @Bind(R.id.grid_15)
    ImageView grid15;
    @Bind(R.id.button_up)
    Button buttonUp;
    @Bind(R.id.button_down)
    Button buttonDown;
    @Bind(R.id.button_left)
    Button buttonLeft;
    @Bind(R.id.button_right)
    Button buttonRight;
    @Bind(R.id.step)
    TextView step;
    @OnClick(R.id.button_up)
    public void moveUp(){
        mGamePresenter.move(IGamePresenter.MOVE_UP);
    }
    @OnClick(R.id.button_down)
    public void moveDown(){
        mGamePresenter.move(IGamePresenter.MOVE_DOWN);
    }
    @OnClick(R.id.button_left)
    public void moveLeft(){
        mGamePresenter.move(IGamePresenter.MOVE_LEFT);
    }
    @OnClick(R.id.button_right)
    public void moveRight(){
        mGamePresenter.move(IGamePresenter.MOVE_RIGHT);
    }
}
