package com.example.baiduapistoreconnector.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Air Quality Index (AQI) details for a city.")
public class AqiInfo {

    @JsonProperty("city")
    @Schema(description = "Name of the city.", example = "Beijing")
    private String city;

    @JsonProperty("aqi")
    @Schema(description = "Air Quality Index value.", example = "50")
    private int aqi;

    @JsonProperty("level")
    @Schema(description = "AQI level, e.g., 'Good', 'Moderate'.", example = "Good")
    private String level;

    @JsonProperty("core")
    @Schema(description = "Primary pollutant.", example = "PM2.5")
    private String core; // Primary pollutant

    @JsonProperty("time")
    @Schema(description = "Timestamp of the AQI data.", example = "2023-10-27 10:00:00")
    private String time;

    // Getters and setters
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAqi() {
        return aqi;
    }

    public void setAqi(int aqi) {
        this.aqi = aqi;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCore() {
        return core;
    }

    public void setCore(String core) {
        this.core = core;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
