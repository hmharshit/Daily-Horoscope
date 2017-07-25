package com.richatanya.tarunikaranveer.dailyhoroscope;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class Horoscopes extends AppCompatActivity {
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horoscopes);

        gridView = (GridView) findViewById(R.id.faculty_list);
        final ArrayList<Horoscope> horoscopeList = Horoscope.getFaculties(this);
        HoroscopeCardAdapter adapter = new HoroscopeCardAdapter(this, horoscopeList);
        gridView.setAdapter(adapter);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setTitleTextColor(getResources().getColor(R.color.actionBarText));
        getSupportActionBar().setTitle("Daily Horoscope");

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        final Context context = this;

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, HoroscopeDetails.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });


    }

}
