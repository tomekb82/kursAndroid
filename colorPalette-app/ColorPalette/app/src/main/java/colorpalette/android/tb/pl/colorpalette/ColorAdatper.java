package colorpalette.android.tb.pl.colorpalette;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomek on 07.06.16.
 */
public class ColorAdatper extends RecyclerView.Adapter<ColorViewHolder>{

    private final LayoutInflater layoutInflater;
    private List<String> colors = new ArrayList<>();

    public ColorAdatper(LayoutInflater layoutInflater) {

        this.layoutInflater = layoutInflater;
    }

    /* Tworzy widok i opakowuje w Holder*/
    @Override
    public ColorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(android.R.layout.simple_expandable_list_item_1, parent, false);
        return new ColorViewHolder(view);
    }

    /* Przypisanie danych do konkretnego wiersza, łaczy widok z danymi, dla wierszy wyświetla dane */
    @Override
    public void onBindViewHolder(ColorViewHolder holder, int position) {

        String colorInhex = colors.get(position);
        
        holder.setColor(colorInhex);
    }

    /* Liczba elmentów wewnetrzych adaptera - ilosc elementow listy */
    @Override
    public int getItemCount() {
        return colors.size();
    }

    public void add(String color){
        colors.add(color);
        notifyItemInserted(colors.size() - 1);
    }
}

class ColorViewHolder extends RecyclerView.ViewHolder{


    private String color;

    private TextView textView;

    /* Wiersz  tabeli */
    public ColorViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        textView.setText(color);
        textView.setBackgroundColor(Color.parseColor(color));
    }
}
