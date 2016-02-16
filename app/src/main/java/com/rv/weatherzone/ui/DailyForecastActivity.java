package com.rv.weatherzone.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.AndroidCharacter;
import android.view.View;
import android.widget.ArrayAdapter;

import com.rv.weatherzone.R;
import com.rv.weatherzone.adapters.DayAdapter;
import com.rv.weatherzone.weather.DailyForecast;

public class DailyForecastActivity extends ListActivity {

    private DailyForecast[] mDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast);

        Intent intent = getIntent();
        mDays = intent.getParcelableArrayExtra(MainActivity.DAILY_FORECAST);

    }
}