package com.example.weather;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
        if(networkCheck()){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("city", textCity.getText().toString());
            editor.commit();

            Intent intent = new Intent(this, WeatherActivity.class);

            intent.putExtra("city",textCity.getText().toString());
            startActivity(intent);
        }
    }

    private boolean networkCheck(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.isConnected()){
            return true;
        }else{
            Context context = getApplicationContext();
            Toast toastText = Toast.makeText(context,"Brak połączenia z internetem. Sprawdź połączenie ",Toast.LENGTH_LONG);
            toastText.setGravity(Gravity.TOP,0,0);
            toastText.show();
            return false;
        }
    }
}
