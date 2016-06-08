package colorpalette.android.tb.pl.colorpalette;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaletteActivity extends AppCompatActivity implements ColorAdatper.ColorClickedListener {

    public static final String LOG_TAG = PaletteActivity.class.getSimpleName();
    public static final int REQUEST_CODE_CREATE = 1;
    private static final int REQUEST_CODE_EDIT = 2;

    @BindView(R.id.colorsRecyclerView)
    RecyclerView colorsRecyclerView;

    private FloatingActionButton fab;

    private ColorAdatper colorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addColor();
            }
        });

        colorAdapter = new ColorAdatper(getLayoutInflater(), PreferenceManager.getDefaultSharedPreferences(this));
        colorAdapter.setColorClickedListener(this);
        colorsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        colorsRecyclerView.setAdapter(colorAdapter);

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                colorAdapter.remove(position);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(colorsRecyclerView);

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


    private void addColor() {
        Intent intent = new Intent(PaletteActivity.this, ColorActivity.class);
        startActivityForResult(intent, REQUEST_CODE_CREATE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_palette, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            addColor();
            return true;
        } else if (id == R.id.action_clear) {
            colorAdapter.clear();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_CREATE) {
                String colorInhex = data.getStringExtra(ColorActivity.COLOR_IN_HEX);
                final int position = colorAdapter.add(colorInhex);
                Snackbar.make(fab, getString(R.string.new_color_created, colorInhex), Snackbar.LENGTH_LONG)
                        .setAction(R.string.undo, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                colorAdapter.remove(position);
                                colorAdapter.notifyItemRemoved(position);
                            }
                        })
                        .show();


            } else if (requestCode == REQUEST_CODE_EDIT) {
                String colorInhex = data.getStringExtra(ColorActivity.COLOR_IN_HEX);

                String oldColor = data.getStringExtra(ColorActivity.OLD_COLOR_KEY);

                colorAdapter.replace(oldColor, colorInhex);


            }
        }
    }

    @Override
    public void onColorClicked(String colorInHex) {
        Intent intent = new Intent(this, ColorActivity.class);
        intent.putExtra(ColorActivity.OLD_COLOR_KEY, colorInHex);
        startActivityForResult(intent, REQUEST_CODE_EDIT);
    }

    public static int getTextColorFromColor(int color){
        return new Palette.Swatch(color, 1).getTitleTextColor();
    }
}
