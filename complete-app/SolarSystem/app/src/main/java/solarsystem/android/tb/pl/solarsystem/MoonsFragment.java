package solarsystem.android.tb.pl.solarsystem;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoonsFragment extends Fragment {


    private static final String OBJECTS_KEY = "objects";
    @BindView(R.id.moonsViewPager)
    ViewPager moonsViewPager;
    @BindView(R.id.moonsTabLayout)
    TabLayout moonsTabLayout;

    public MoonsFragment() {
        // Required empty public constructor
    }

    public static MoonsFragment newInstance(SolarObject[] solarObjects) {

        Bundle args = new Bundle();
        /* Zapamietujemy obiekty podczas tworzenia instancji */
        args.putSerializable(OBJECTS_KEY, solarObjects);

        MoonsFragment fragment = new MoonsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_moons, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /* Wyciągamy obiekty */
        SolarObject[] solarObjects = (SolarObject[]) getArguments().getSerializable(OBJECTS_KEY);

        /* Tworzymy adapter */
        MoonsPagerAdapter moonsPagerAdapter = new MoonsPagerAdapter(getChildFragmentManager(), solarObjects);

        /* Podpinamy adapter do view pagera */
        moonsViewPager.setAdapter(moonsPagerAdapter);

        /* Podpinamy viewPagera do TabLayoutu */
        moonsTabLayout.setupWithViewPager(moonsViewPager);

    }
}
