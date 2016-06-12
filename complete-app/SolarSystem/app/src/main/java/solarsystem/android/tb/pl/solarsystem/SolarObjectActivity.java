package solarsystem.android.tb.pl.solarsystem;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SolarObjectActivity extends AppCompatActivity implements SolarObjectAdapter.SolarObjectClickedListener {

    public static final String OBJECT_KEY = "object";
    @BindView(R.id.objectImageView)
    ImageView objectImageView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.objectTextView)
    TextView objectTextView;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.moonsLabel)
    TextView moonsLabel;
    @BindView(R.id.moonsRecyclerView)
    RecyclerView moonsRecyclerView;
    private SolarObject solarObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solar_object);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        solarObject = (SolarObject) getIntent().getSerializableExtra(OBJECT_KEY);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if(solarObject.hasMovie()) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showYouYubeVideo(solarObject.getVideo());
                }
            });
        }else{
            /* Odpinamy nasz widok od Coordinator Layout - który zarządza/zmienia widoczność naszych elementów  */
            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
            lp.setAnchorId(View.NO_ID);
            fab.setVisibility(View.GONE);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        toolbarLayout.setTitle(solarObject.getName());

        try {
            String text = SolarObject.loadStringFromAssets(this, solarObject.getText());
            objectTextView.setText(Html.fromHtml(text));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Glide.with(this)
                .load(solarObject.getImagePath())
                .into(objectImageView);

        moonsRecyclerView.setVisibility(solarObject.hasMoons() ? View.VISIBLE : View.GONE);
        moonsLabel.setVisibility(solarObject.hasMoons() ? View.VISIBLE : View.GONE);

        if(solarObject.hasMoons()){
            SolarObjectAdapter adapter = new SolarObjectAdapter(solarObject.getMoons());
            adapter.setSolarObjectClickedListener(this);
            moonsRecyclerView.setAdapter(adapter);
            moonsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false));
            moonsRecyclerView.setNestedScrollingEnabled(false);
        }
    }

    private void showYouYubeVideo(String video) {

        try {
            /* Uruchamiamy aplikacje youtube jezeli mamy zainstalowana */
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + video));
            startActivity(intent);
        }catch (ActivityNotFoundException e){
            /* Uruchamiamy aplikacje youtube w przgladarce jezeli NIE mamy zainstalowanej */
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + video));
            startActivity(intent);
        }

    }

    public static void start(Activity activity, SolarObject solarObject) {
        Intent intent = new Intent(activity, SolarObjectActivity.class);
        intent.putExtra(OBJECT_KEY, solarObject);
        activity.startActivity(intent);
    }

    @Override
    public void solarObjectClicked(SolarObject solarObject) {
        SolarObjectActivity.start(this, solarObject);
    }
}
