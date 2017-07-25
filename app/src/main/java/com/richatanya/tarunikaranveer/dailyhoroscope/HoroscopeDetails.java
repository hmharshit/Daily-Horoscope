package com.richatanya.tarunikaranveer.dailyhoroscope;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class HoroscopeDetails extends AppCompatActivity {
    TextView sign, horoScope;
    ImageView facultyImage;
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horoscope_details);
        int position = this.getIntent().getExtras().getInt("position");
        Horoscope horoscop = Horoscope.getFaculties(this).get(position);

        sign = (TextView) findViewById(R.id.sign);
        horoScope = (TextView) findViewById(R.id.horo_scope);
        facultyImage = (ImageView) findViewById(R.id.faculty_image);
        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.CollapsingToolbarLayout2);
        Context context = this;
        collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(context, R.color.colorPrimary));

        setSupportActionBar(toolbar);


        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowTitleEnabled(false);
        sign.setText(horoscop.sign);
        horoScope.setText(horoscop.horo);

        Picasso.with(this).load(horoscop.image_loc).into(facultyImage);
    }
}
