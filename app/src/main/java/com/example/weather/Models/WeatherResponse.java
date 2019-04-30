package com.example.weather.Models;

import com.google.gson.annotations.SerializedName;

public class WeatherResponse {
    @SerializedName("main")
    private Main main;

/*    @SerializedName("weather")
    private Weather weather;*/

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

/*    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }*/
}
