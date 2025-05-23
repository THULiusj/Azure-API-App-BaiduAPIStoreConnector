package com.example.baiduapistoreconnector.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Detailed weather information for a city.")
public class WeatherInfo {

    @JsonProperty("city")
    @Schema(description = "Name of the city.", example = "Beijing")
    private String city;

    @JsonProperty("pinyin")
    @Schema(description = "Pinyin of the city.", example = "beijing")
    private String pinyin;

    @JsonProperty("citycode")
    @Schema(description = "City code.", example = "101010100")
    private String citycode;

    @JsonProperty("date")
    @Schema(description = "Date of the weather information.", example = "2023-10-27")
    private String date;

    @JsonProperty("time")
    @Schema(description = "Time of the weather information.", example = "10:00")
    private String time;

    @JsonProperty("postCode")
    @Schema(description = "Postal code of the city.", example = "100000")
    private String postCode;

    @JsonProperty("longitude")
    @Schema(description = "Longitude of the city.", example = "116.391")
    private double longitude;

    @JsonProperty("latitude")
    @Schema(description = "Latitude of the city.", example = "39.904")
    private double latitude;

    @JsonProperty("altitude")
    @Schema(description = "Altitude of the city.", example = "33m")
    private String altitude;

    @JsonProperty("weather")
    @Schema(description = "Weather condition.", example = "Sunny")
    private String weather;

    @JsonProperty("temp")
    @Schema(description = "Current temperature in Celsius.", example = "25")
    private String temp;

    @JsonProperty("l_tmp")
    @Schema(description = "Lowest temperature for the day in Celsius.", example = "18")
    private String l_tmp;

    @JsonProperty("h_tmp")
    @Schema(description = "Highest temperature for the day in Celsius.", example = "28")
    private String h_tmp;

    @JsonProperty("WD")
    @Schema(description = "Wind direction.", example = "NW")
    private String WD; // Wind Direction

    @JsonProperty("WS")
    @Schema(description = "Wind speed.", example = "3-4 level")
    private String WS; // Wind Speed

    @JsonProperty("sunrise")
    @Schema(description = "Sunrise time.", example = "06:30")
    private String sunrise;

    @JsonProperty("sunset")
    @Schema(description = "Sunset time.", example = "18:00")
    private String sunset;

    // Getters and setters
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getL_tmp() {
        return l_tmp;
    }

    public void setL_tmp(String l_tmp) {
        this.l_tmp = l_tmp;
    }

    public String getH_tmp() {
        return h_tmp;
    }

    public void setH_tmp(String h_tmp) {
        this.h_tmp = h_tmp;
    }

    public String getWD() {
        return WD;
    }

    public void setWD(String WD) {
        this.WD = WD;
    }

    public String getWS() {
        return WS;
    }

    public void setWS(String WS) {
        this.WS = WS;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }
}
