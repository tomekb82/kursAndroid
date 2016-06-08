package colorpalette.android.tb.pl.colorpalette;

import android.content.Intent;
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

    public static final String OLD_COLOR_KEY = "old_color";
    private static final String LOG_TAG = ColorActivity.class.getSimpleName();
    public static final String RED = "red";
    public static final String GREEN = "green";
    public static final String BLUE = "blue";
    public static final String COLOR_IN_HEX = "color_in_hex";

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
    private String oldColor;


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

        Intent intent = getIntent();
        oldColor = intent.getStringExtra(OLD_COLOR_KEY);
        if(oldColor != null){
            int color = Color.parseColor(oldColor);
            red = Color.red(color);
            green = Color.green(color);
            blue = Color.blue(color);

            updateSeekBars();
            updateBackgroundColor();

            generateButton.setVisibility(View.GONE);
        }

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
        int textColorFromColor = PaletteActivity.getTextColorFromColor(color);
        colorLinearLayout.setBackgroundColor(color);
        redLabel.setTextColor(textColorFromColor);
        greenLabel.setTextColor(textColorFromColor);
        blueLabel.setTextColor(textColorFromColor);
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

    /* Przekaż dane do inngo Activity */
    @OnClick(R.id.saveButton)
    public void save() {

        Intent data = new Intent();
        data.putExtra(COLOR_IN_HEX, String.format("#%02X%02X%02X",red,green,blue));
        if(oldColor != null){
            data.putExtra(OLD_COLOR_KEY, oldColor);
        }
        setResult(RESULT_OK, data);
        finish();

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
