package com.example.weather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textCity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textCity=findViewById(R.id.textCity);
    }

    public void checkWeather(View view) {
        Intent intent = new Intent(this, WeatherActivity.class);
        intent.putExtra("city",textCity.getText().toString());
        startActivity(intent);
    }
}
