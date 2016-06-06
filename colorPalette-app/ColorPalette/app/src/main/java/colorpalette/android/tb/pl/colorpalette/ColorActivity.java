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

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ColorActivity extends AppCompatActivity /*implements  View.OnClickListener*/ {

    private static final String LOG_TAG = ColorActivity.class.getSimpleName();
    @BindView(R.id.redSeekBar)
    SeekBar redSeekBar;
    @BindView(R.id.greenSeekBar)
    SeekBar greenSeekBar;
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
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //generateButton.setOnClickListener(this);
        //saveButton.setOnClickListener(this);

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

    /*@Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.generateButton:
                break;
            case R.id.saveButton:
                break;
        }
    }*/

    //@OnClick(R.id.generateButton)
    public void generate() {

        red = random.nextInt(256);
        green = random.nextInt(256);
        blue = random.nextInt(256);

        int color = Color.rgb(red, green, blue);

        Log.d(LOG_TAG, String.valueOf(red));
        Log.d(LOG_TAG, String.valueOf(green));
        Log.d(LOG_TAG, String.valueOf(blue));
        Log.d(LOG_TAG, String.valueOf(color));

        colorLinearLayout.setBackgroundColor(color);


    }

    //@OnClick(R.id.saveButton)
    public void save() {
    }

    @OnClick({R.id.generateButton, R.id.saveButton})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.generateButton:
                generate();
                break;
            case R.id.saveButton:
                break;
        }
    }
}
