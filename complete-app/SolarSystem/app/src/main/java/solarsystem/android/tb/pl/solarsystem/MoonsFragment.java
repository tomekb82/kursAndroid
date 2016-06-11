package solarsystem.android.tb.pl.solarsystem;


import android.content.Context;
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
    //@BindView(R.id.moonsTabLayout)
    //TabLayout moonsTabLayout;

    private TabCallback tabCallback;

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
    public void onAttach(Context context) {
        super.onAttach(context);
        tabCallback = (TabCallback) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        tabCallback = null;
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
    public void onDestroyView() {
        super.onDestroyView();
        //ButterKnife.unbind(this);
        tabCallback.hideTabs();
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

        /* Przekazujemy viewPagera do callbacku (zostanie podpięty do TabLayout w głównym activity) */
        tabCallback.showTabs(moonsViewPager);
        /* Podpinamy viewPagera do TabLayoutu */
        //moonsTabLayout.setupWithViewPager(moonsViewPager);

    }

    public interface TabCallback {
        void showTabs(ViewPager viewPager);
        void hideTabs();

    }
}
