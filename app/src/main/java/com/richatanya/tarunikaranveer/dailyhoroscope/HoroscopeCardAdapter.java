package com.richatanya.tarunikaranveer.dailyhoroscope;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by root on 25/7/17.
 */


public class HoroscopeCardAdapter extends BaseAdapter {


    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Horoscope> data_src;

    public HoroscopeCardAdapter(Context context, ArrayList<Horoscope> items) {
        this.context = context;
        data_src = items;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data_src.size();
    }

    @Override
    public Object getItem(int position) {
        return data_src.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("Harshit", "Inside getView");
        ViewHolder holder;
        if (convertView == null) {

            // 2
            convertView = inflater.inflate(R.layout.horoscope_layout, parent, false);

            // 3
            holder = new ViewHolder();
            holder.facultyImage = (ImageView) convertView.findViewById(R.id.faculty_image);
            holder.zodiac = (TextView) convertView.findViewById(R.id.fac_name);

            // 4
            convertView.setTag(holder);
        } else {
            // 5
            holder = (ViewHolder) convertView.getTag();
        }

        TextView zod = holder.zodiac;
        ImageView logo = holder.facultyImage;

        Horoscope horos = (Horoscope) getItem(position);
        zod.setText(horos.sign);
        Picasso.with(context).load(horos.image_loc).into(logo);

        return convertView;

    }

    public void measureItems(int columnWidth) {
        Log.d("Harshit", "Inside measureItems");
        // Obtain system inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Inflate temp layout object for measuring
        GridViewItemLayout itemView = (GridViewItemLayout) inflater.inflate(R.layout.horoscope_layout, null);

        // Create measuring specs
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(columnWidth, View.MeasureSpec.EXACTLY);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

        // Loop through each data object
        for (int index = 0; index < data_src.size(); index++) {
            Horoscope item = data_src.get(index);

            // Set position and data
            itemView.setPosition(index);
            itemView.updateItemDisplay(item);

            // Force measuring
            itemView.requestLayout();
            itemView.measure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    private static class ViewHolder {
        public TextView zodiac;
        public ImageView facultyImage;
    }
}


class Horoscope {
    String sign;
    String horo;
    String image_loc;

    public Horoscope(String sign, String horo, String image_loc) {
        this.sign = sign;
        this.horo = horo;
        this.image_loc = image_loc;

    }

    static private String loadJSONFromAsset(Context context) {
        String json = null;
        try {

            InputStream is = context.getAssets().open("horoscope.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

    public static ArrayList<Horoscope> getFaculties(Context context) {
        JSONObject obj;
        try {
            obj = new JSONObject(loadJSONFromAsset(context));
        } catch (JSONException ex) {
            ex.printStackTrace();
            return null;
        }
        JSONArray horoscope;
        try {


            horoscope = obj.getJSONArray("zodiac");
        } catch (JSONException ex) {
            ex.printStackTrace();
            return null;
        }
        ArrayList<Horoscope> horoscopes = new ArrayList<Horoscope>();

        for (int i = 0; i < horoscope.length(); i++) {
            Horoscope x;
            try {
                JSONObject jo = horoscope.getJSONObject(i);
                x = new Horoscope(jo.getString("name"), jo.getString("horoscope"), jo.getString("url"));
            } catch (JSONException ex) {
                ex.printStackTrace();
                return null;
            }
            horoscopes.add(x);
        }

        return horoscopes;
    }
}

