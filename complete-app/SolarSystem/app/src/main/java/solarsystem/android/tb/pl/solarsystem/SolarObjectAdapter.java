package solarsystem.android.tb.pl.solarsystem;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tomek on 10.06.16.
 */
public class SolarObjectAdapter extends RecyclerView.Adapter<SolarObjectAdapter.SolarObjectViewHolder>{

    private final SolarObject[] solarObjects;

    private SolarObjectClickedListener solarObjectClickedListener;

    public void setSolarObjectClickedListener(SolarObjectClickedListener solarObjectClickedListener) {
        this.solarObjectClickedListener = solarObjectClickedListener;
    }

    public SolarObjectAdapter(SolarObject[] solarObjects) {
        this.solarObjects = solarObjects;
    }

    @Override
    public SolarObjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_solar_object, parent, false);
        return new SolarObjectViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return solarObjects.length;
    }

    /* Wyciągniecie elementu który chcemy wyświetlać */
    @Override
    public void onBindViewHolder(SolarObjectViewHolder holder, int position) {
        SolarObject solarObject = solarObjects[position];
        holder.setSolarObject(solarObject);
    }


    private void itemClicked(SolarObject solarObject) {
        if(solarObjectClickedListener != null){
            solarObjectClickedListener.solarObjectClicked(solarObject);
        }
    }

    class SolarObjectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.itemImageView)
        ImageView itemImageView;
        @BindView(R.id.itemTextView)
        TextView itemTextView;

        private SolarObject solarObject;

        public SolarObjectViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public SolarObject getSolarObject() {
            return solarObject;
        }

        public void setSolarObject(SolarObject solarObject) {
            this.solarObject = solarObject;

            itemTextView.setText(solarObject.getName());

            Glide.with(itemImageView.getContext())
                    .load(solarObject.getImagePath())
                    .placeholder(R.drawable.planet_placeholder)
                    .fitCenter()
                    .into(itemImageView);
        }

        @Override
        public void onClick(View v) {
            itemClicked(solarObject);
        }
    }


    /* Informujemy zewnętrzną klase ze cos sie wydarzylo */
    public interface SolarObjectClickedListener {
        void solarObjectClicked(SolarObject solarObject );
    }

}
