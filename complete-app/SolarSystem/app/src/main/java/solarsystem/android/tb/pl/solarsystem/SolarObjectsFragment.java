package solarsystem.android.tb.pl.solarsystem;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class SolarObjectsFragment extends Fragment implements SolarObjectAdapter.SolarObjectClickedListener {

    public static final String OBJECTS_KEY = "objects";
    public static final int GRID_LAYOUT_COLUMNS = 2;

    @BindView(R.id.objectRecyclerView)
    RecyclerView objectRecyclerView;

    public SolarObjectsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_solar_objects, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SolarObject[] objects = (SolarObject[]) getArguments().getSerializable(OBJECTS_KEY);

        objectRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), GRID_LAYOUT_COLUMNS));
        SolarObjectAdapter adapter = new SolarObjectAdapter(objects);
        adapter.setSolarObjectClickedListener(this);

        objectRecyclerView.setAdapter(adapter);
    }

    public static SolarObjectsFragment newInstance(SolarObject[] objects) {

        Bundle args = new Bundle();
        args.putSerializable(OBJECTS_KEY, objects);

        SolarObjectsFragment fragment = new SolarObjectsFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void solarObjectClicked(SolarObject solarObject) {
        Log.d(SolarObjectsFragment.class.getSimpleName(), "Clicked:" + solarObject.getName());
    }
}
