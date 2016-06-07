package colorpalette.android.tb.pl.colorpalette;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ColorActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener /*implements  View.OnClickListener*/ {

    private static final String LOG_TAG = ColorActivity.class.getSimpleName();
    public static final String RED = "red";
    public static final String GREEN = "green";
    public static final String BLUE = "blue";

    @BindView(R.id.redLabel)
    TextView redLabel;
    @BindView(R.id.redSeekBar)
    SeekBar redSeekBar;
    @BindView(R.id.greenLabel)
    TextView greenLabel;
    @BindView(R.id.greenSeekBar)
    SeekBar greenSeekBar;
    @BindView(R.id.blueLabel)
    TextView blueLabel;
    @BindView(R.id.blueSeekBar)
    SeekBar blueSeekBar;
    @BindView(R.id.generateButton)
    Button generateButton;
    @BindView(R.id.saveButton)
    Button saveButton;
    @BindView(R.id.colorLinearLayout)
    LinearLayout colorLinearLayout;

    private ActionBar actionBar;

    private int red;
    private int green;
    private int blue;

    private Random random = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
        ButterKnife.bind(this);
//        ButterKnife.setDebug(true);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        redSeekBar.setOnSeekBarChangeListener(this);
        greenSeekBar.setOnSeekBarChangeListener(this);
        blueSeekBar.setOnSeekBarChangeListener(this);

        Log.d(LOG_TAG, "onCreate");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateSeekBars() {
        redSeekBar.setProgress(red);
        greenSeekBar.setProgress(green);
        blueSeekBar.setProgress(blue);
    }

    private void updateBackgroundColor() {
        int color = Color.rgb(red, green, blue);
        colorLinearLayout.setBackgroundColor(color);
    }

    /*@Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.generateButton:
                break;
            case R.id.saveButton:
                break;
        }
    }*/

    @OnClick(R.id.generateButton)
    public void generate() {

        red = random.nextInt(256);
        green = random.nextInt(256);
        blue = random.nextInt(256);

        updateSeekBars();

        Log.d(LOG_TAG, String.valueOf(red));
        Log.d(LOG_TAG, String.valueOf(green));
        Log.d(LOG_TAG, String.valueOf(blue));

        updateBackgroundColor();
    }

    @OnClick(R.id.saveButton)
    public void save() {
    }


   /* @OnClick({R.id.generateButton, R.id.saveButton})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.generateButton:
                generate();
                break;
            case R.id.saveButton:
                break;
        }
    }*/

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.redSeekBar:
                red = progress;
                break;
            case R.id.greenSeekBar:
                green = progress;
                break;
            case R.id.blueSeekBar:
                blue = progress;
                break;
        }
        updateBackgroundColor();

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    /* Zapamietywanie wartosci miedzy uruchomieniami */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(RED, red);
        outState.putInt(GREEN, green);
        outState.putInt(BLUE, blue);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        red = savedInstanceState.getInt(RED);
        green = savedInstanceState.getInt(GREEN);
        blue = savedInstanceState.getInt(BLUE);
    }
}
