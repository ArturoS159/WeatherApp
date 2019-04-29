package com.example.weather;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private TextView textCity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textCity=findViewById(R.id.textCity);
        preferences = this.getPreferences(Context.MODE_PRIVATE);
        restoreData();
    }

    private void restoreData() {
        textCity.setText(preferences.getString("city", ""));
    }
    public void checkWeather(View view) {

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("city", textCity.getText().toString());
        editor.commit();

        Intent intent = new Intent(this, WeatherActivity.class);
        intent.putExtra("city",textCity.getText().toString());
        startActivity(intent);
    }
}
