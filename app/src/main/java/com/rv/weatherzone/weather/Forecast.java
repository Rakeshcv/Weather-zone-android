package com.rv.weatherzone.weather;

public class Forecast {

    private CurrentWeather mCurrentWeather;
    private HourlyForecast[] mHourlyForecast;
    private DailyForecast[] mDailyForecast;

    public CurrentWeather getCurrentWeather() {
        return mCurrentWeather;
    }

    public void setCurrentWeather(CurrentWeather currentWeather) {
        mCurrentWeather = currentWeather;
    }

    public HourlyForecast[] getHourlyForecast() {
        return mHourlyForecast;
    }

    public void setHourlyForecast(HourlyForecast[] hourlyForecast) {
        mHourlyForecast = hourlyForecast;
    }

    public DailyForecast[] getDailyForecast() {
        return mDailyForecast;
    }

    public void setDailyForecast(DailyForecast[] dailyForecast) {
        mDailyForecast = dailyForecast;
    }
}
