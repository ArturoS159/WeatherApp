package com.example.weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.weather.API.WeatherAPI;
import com.example.weather.Models.WeatherResponse;
import com.squareup.picasso.Picasso;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherActivity extends AppCompatActivity {

    private TextView textCity;
    private TextView textTime;
    private TextView textTemp;
    private TextView textPre;
    private TextView textHum;
    private TextView textTempMin;
    private TextView textTempMax;
    private ImageView imageViewUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_weather);
        String city = getIntent().getCharSequenceExtra("city").toString();

        final String API = "749561a315b14523a8f5f1ef95e45864";
        final String UNITS = "METRIC";
        textCity = findViewById(R.id.textCity);
        textTime = findViewById(R.id.textTime);
        textTemp = findViewById(R.id.textTemp);
        textPre = findViewById(R.id.textPre);
        textHum = findViewById(R.id.textHum);
        textTempMin = findViewById(R.id.textTempMin);
        textTempMax = findViewById(R.id.textTempMax);
        imageViewUrl = findViewById(R.id.imageViewUrl);

        getContent(city,API,UNITS);

        LocalTime localTime = LocalTime.now();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
        textTime.setText(localTime.format(timeFormat));
        textCity.setText(city);

    }

    private void getContent(String city,String API,String UNITS){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherAPI service = retrofit.create(WeatherAPI.class);
        Call<WeatherResponse> call = service.getWeatherByCity(city,API,UNITS);

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if(response.isSuccessful()) {
                    textTemp.setText(response.body().getMain().getTemp() + " °C");
                    textPre.setText(response.body().getMain().getPressure() + " hPa");
                    textHum.setText(response.body().getMain().getHumidity() + " %");
                    textTempMin.setText(response.body().getMain().getTempMin() + " °C");
                    textTempMax.setText(response.body().getMain().getTempMax() + " °C");
                    Picasso.get().load("http://openweathermap.org/img/w/"+response.body().getWeather().get(0).getIcon()+".png").into(imageViewUrl);
                }else{
                    Intent returnBtn = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(returnBtn);
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
