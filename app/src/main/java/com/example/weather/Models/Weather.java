package com.example.weather.Models;

import com.google.gson.annotations.SerializedName;

class Weather {
    @SerializedName("icon")
    private String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
