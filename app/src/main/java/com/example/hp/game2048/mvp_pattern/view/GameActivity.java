package com.example.hp.game2048.mvp_pattern.view;

/**
 * Created by lwx on 2016/2/1.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.hp.game2048.R;
import com.example.hp.game2048.mvp_pattern.presenter.GamePresenter;

public class GameActivity extends AppCompatActivity implements IGameView{
    private Button[] button = new Button[4];
    private TextView step;
    private ImageView[] imageView = new ImageView[16];
    public final int[] gridToId = {R.id.grid_0, R.id.grid_1, R.id.grid_2, R.id.grid_3,
            R.id.grid_4, R.id.grid_5, R.id.grid_6, R.id.grid_7,
            R.id.grid_8, R.id.grid_9, R.id.grid_10, R.id.grid_11,
            R.id.grid_12, R.id.grid_13, R.id.grid_14, R.id.grid_15};
    private int getIdFromNum(int num){
        switch (num){
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
    public void render(){
        for(int i=0; i<16; i++)
            imageView[i].setImageResource(getIdFromNum(mGamePresenter.getPosNum(i)));
        step.setText(String.valueOf(mGamePresenter.getStep()));
    }

    private void imageviewInit(){
        for(int i=0; i<16; i++)
            imageView[i] = (ImageView) findViewById(gridToId[i]);
    }

    private void buttonInit(){
        button[0] = (Button) findViewById(R.id.button_up);
        button[1] = (Button) findViewById(R.id.button_down);
        button[2] = (Button) findViewById(R.id.button_left);
        button[3] = (Button) findViewById(R.id.button_right);
        for(int i=0; i<4; i++){
            final int id = i;
            button[id].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mGamePresenter.move(id);
                }
            });
        }
    }

    GamePresenter mGamePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageviewInit();
        buttonInit();
        step = (TextView) findViewById(R.id.step);
        mGamePresenter = new GamePresenter(this);
        render();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
