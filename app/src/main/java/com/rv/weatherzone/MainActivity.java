package com.rv.weatherzone;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private CurrentWeather mCurrentWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String apiKey = "cc217ebdc7e2d5685cce923661e73910";
        double latitude = 37.8267;
        double longitude = -122.423;

        String forecastUrl = "https://api.forecast.io/forecast/" + apiKey + "/" + latitude + "," + longitude;

        if (isNetworkAvailable()) {


            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder().url(forecastUrl).build();


            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        String jsonData = response.body().string();
                        Log.e(TAG, jsonData);
                        if (response.isSuccessful()) {
                                mCurrentWeather = getCurrentWeatherDetails(jsonData);
                        } else {
                            alertUserAboutError();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e(TAG, "Exception caught: ", e);
                    } catch (JSONException e) {
                        Log.e(TAG, "Exception caught: ", e);
                    }

                }
            });
        } else {
            Toast.makeText(this, R.string.network_unavailable,Toast.LENGTH_SHORT).show();
        }

    }

    private CurrentWeather getCurrentWeatherDetails(String jsonData) throws JSONException {
        JSONObject forecast = new JSONObject(jsonData);
        String timezone = forecast.getString("timezone");
        Log.d(TAG,timezone);

        JSONObject currently = forecast.getJSONObject("currently");

        CurrentWeather currentWeather = new CurrentWeather();
        currentWeather.setIcon(currently.getString("icon"));
        currentWeather.setTemperature(currently.getDouble("temperature"));
        currentWeather.setHumidity(currently.getDouble("humidity"));
        currentWeather.setPrecipChance(currently.getDouble("precipProbability"));
        currentWeather.setTime(currently.getLong("time"));
        currentWeather.setSummary(currently.getString("summary"));
        currentWeather.setTimeZone(timezone);

        Log.d(TAG,currentWeather.getFormattedTime());

        return currentWeather;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;

        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }

    private void alertUserAboutError() {
        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager(), "Error_dialog");
    }
}
