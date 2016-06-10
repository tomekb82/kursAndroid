package solarsystem.android.tb.pl.solarsystem;

import android.content.Context;
import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

/**
 * Created by tomek on 09.06.16.
 */
public class SolarObject implements Serializable {

    private String name;
    private String text;
    private String image;

    private String video;
    private SolarObject[] moons;

    public SolarObject() {
    }

    public SolarObject(String name) {
        this.name = name;
    }

    public SolarObject(JSONObject jsonObject) throws JSONException {
        name = jsonObject.getString("name");
        text = String.format("texts/%s.txt", name.toLowerCase());
        image = String.format("images/%s.jpg", name.toLowerCase());

        video = jsonObject.optString("video");
        JSONArray moons = jsonObject.optJSONArray("moons");
        if(moons != null) {
            this.moons = getSolarObjectsFromJsonArray(moons);
        }


    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SolarObject[] getMoons() {
        return moons;
    }

    public void setMoons(SolarObject[] moons) {
        this.moons = moons;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static SolarObject[] loadArrayFromJson(Context ctx, String type){
        try {
            String json = loadStringFromAssets(ctx, "solar.json");
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray(type);

            return getSolarObjectsFromJsonArray(jsonArray);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new SolarObject[0];

    }

    @NonNull
    private static SolarObject[] getSolarObjectsFromJsonArray(JSONArray jsonArray) throws JSONException {
        SolarObject[] solarObjects = new SolarObject[jsonArray.length()];

        for(int i=0; i< jsonArray.length(); i++){
            SolarObject solarObject = new SolarObject(jsonArray.getJSONObject(i));
            solarObjects[i] = solarObject;
        }
        return solarObjects;
    }

    public static String loadStringFromAssets(Context ctx, String filename) throws IOException {
        InputStream inputStream = ctx.getAssets().open(filename);
        int size = inputStream.available();
        byte[] buffer = new byte[size];

        inputStream.read(buffer);
        inputStream.close();

        return new String(buffer, "UTF-8");
    }
}
