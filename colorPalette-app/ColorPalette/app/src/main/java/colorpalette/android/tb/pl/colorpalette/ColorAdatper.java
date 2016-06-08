package colorpalette.android.tb.pl.colorpalette;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomek on 07.06.16.
 */
public class ColorAdatper extends RecyclerView.Adapter<ColorViewHolder>{

    public static final String COLORS_KEY = "colors";
    private final LayoutInflater layoutInflater;
    private SharedPreferences sharedPreferences;
    private List<String> colors = new ArrayList<>();

    private ColorClickedListener colorClickedListener;
    private String oldColor;

    public ColorAdatper(LayoutInflater layoutInflater, SharedPreferences sharedPreferences) {

        this.layoutInflater = layoutInflater;
        this.sharedPreferences = sharedPreferences;

        String colorsJson = sharedPreferences.getString(COLORS_KEY, "[]");
        try {
            JSONArray jsonArray = new JSONArray(colorsJson);
            for(int i=0; i<jsonArray.length(); i++){
                colors.add(i, jsonArray.getString(i));
            }
            notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* Tworzy widok i opakowuje w Holder*/
    @Override
    public ColorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(android.R.layout.simple_expandable_list_item_1, parent, false);
        return new ColorViewHolder(view, this);
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

        storeInPreferences();
    }

    private void storeInPreferences() {
        try {
            JSONArray jsonArray = new JSONArray();
            for(int i=0; i<colors.size(); i++){
                jsonArray.put(i, colors.get(i));
            }
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(COLORS_KEY, jsonArray.toString());
            editor.apply();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void remove(int position) {

        colors.remove(position);
        storeInPreferences();
    }

    public void setColorClickedListener(ColorClickedListener colorClickedListener) {
        this.colorClickedListener = colorClickedListener;
    }

    public void clicked(int position) {
        if(colorClickedListener != null){
            colorClickedListener.onColorClicked(colors.get(position));
        }
    }

    public void replace(String oldColor, String colorInhex) {
        int indexOf = colors.indexOf(oldColor);
        colors.set(indexOf, colorInhex);
        notifyItemChanged(indexOf);
        storeInPreferences();
    }

    public void clear() {
        colors.clear();
        notifyDataSetChanged();

        sharedPreferences.edit().clear().apply();
    }

    public interface ColorClickedListener{
        void onColorClicked(String colorInHex);
    }
}

class ColorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


    private final ColorAdatper colorAdapter;
    private String color;
    private TextView textView;

    /* Wiersz  tabeli */
    public ColorViewHolder(View itemView, ColorAdatper colorAdatper) {
        super(itemView);
        this.colorAdapter = colorAdatper;
        textView = (TextView) itemView;
        textView.setOnClickListener(this);

    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        textView.setText(color);
        textView.setBackgroundColor(Color.parseColor(color));
    }

    @Override
    public void onClick(View v) {

        colorAdapter.clicked(getAdapterPosition());
    }
}
