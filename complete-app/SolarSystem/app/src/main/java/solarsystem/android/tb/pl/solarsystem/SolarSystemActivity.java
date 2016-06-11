package solarsystem.android.tb.pl.solarsystem;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SolarSystemActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MoonsFragment.TabCallback {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.moonsTabLayout)
    TabLayout moonsTabLayout;
    @BindView(R.id.containterLayout)
    FrameLayout containterLayout;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private SolarObject[] planets;
    private SolarObject[] others;
    private SolarObject[] objectsWithMoons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solar_system);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        navView.setNavigationItemSelectedListener(this);

        planets = SolarObject.loadArrayFromJson(this, "planets");
        others = SolarObject.loadArrayFromJson(this, "others");

        List<SolarObject> planetsWithMoons = new ArrayList<>();
        for (SolarObject planet : planets) {
            if (planet.hasMoons()) {
                planetsWithMoons.add(planet);
            }
        }

        for (SolarObject other : others) {
            if (other.hasMoons()) {
                planetsWithMoons.add(other);
            }
        }

        objectsWithMoons = new SolarObject[planetsWithMoons.size()];
        objectsWithMoons = planetsWithMoons.toArray(objectsWithMoons);

        navView.setCheckedItem(R.id.nav_planets);
        onNavigationItemSelected(navView.getMenu().findItem(R.id.nav_planets));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.solar_system, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_planets) {

            SolarObjectsFragment fragment = SolarObjectsFragment.newInstance(planets);

            replaceFragment(fragment);

        } else if (id == R.id.nav_moons) {

            replaceFragment(MoonsFragment.newInstance(objectsWithMoons));

        } else if (id == R.id.nav_other) {

            SolarObjectsFragment fragment = SolarObjectsFragment.newInstance(others);

            replaceFragment(fragment);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.containterLayout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void showTabs(ViewPager viewPager) {
        moonsTabLayout.setVisibility(View.VISIBLE);
        moonsTabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void hideTabs() {
        moonsTabLayout.removeAllTabs();
        moonsTabLayout.setOnTabSelectedListener(null);
        moonsTabLayout.setVisibility(View.GONE);
    }
}
