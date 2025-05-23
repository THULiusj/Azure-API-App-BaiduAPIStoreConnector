package com.example.baiduapistoreconnector.controller;

import com.example.baiduapistoreconnector.model.BaiduBaseResponse;
import com.example.baiduapistoreconnector.model.CityDetailInfo;
import com.example.baiduapistoreconnector.model.WeatherInfo;
import com.example.baiduapistoreconnector.service.WeatherApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/Weather")
@Tag(name = "Weather Controller", description = "Provides weather information and city details for Chinese cities.")
public class WeatherController {

    private final WeatherApiService weatherApiService;

    public WeatherController(WeatherApiService weatherApiService) {
        this.weatherApiService = weatherApiService;
    }

    @GetMapping("/GetWeatherInfoByPinyin")
    @Operation(summary = "Get Weather by City Pinyin",
                 description = "Retrieves current weather information for a specified city in China using its Pinyin.",
                 responses = {
                     @ApiResponse(responseCode = "200", description = "Successfully retrieved weather data",
                                  content = @Content(mediaType = "application/json",
                                                     schema = @Schema(implementation = BaiduBaseResponse.class))),
                     @ApiResponse(responseCode = "400", description = "Invalid city Pinyin provided",
                                  content = @Content(mediaType = "application/json",
                                                     schema = @Schema(implementation = com.example.baiduapistoreconnector.exception.ErrorResponse.class))),
                     @ApiResponse(responseCode = "502", description = "Error from Baidu API",
                                  content = @Content(mediaType = "application/json",
                                                     schema = @Schema(implementation = com.example.baiduapistoreconnector.exception.ErrorResponse.class)))
                 })
    public BaiduBaseResponse<WeatherInfo> getWeatherByPinyin(
            @Parameter(description = "Pinyin of the city in China (e.g., beijing)", required = true)
            @RequestParam String cityPinyin) throws IOException {
        return weatherApiService.getWeatherByCityPinyin(cityPinyin);
    }

    @GetMapping("/GetWeatherInfoByName")
    @Operation(summary = "Get Weather by City Name",
                 description = "Retrieves current weather information for a specified city in China using its name.",
                 responses = {
                     @ApiResponse(responseCode = "200", description = "Successfully retrieved weather data"), // Schema can be inferred
                     @ApiResponse(responseCode = "400", description = "Invalid city name provided",
                                  content = @Content(mediaType = "application/json",
                                                     schema = @Schema(implementation = com.example.baiduapistoreconnector.exception.ErrorResponse.class))),
                     @ApiResponse(responseCode = "503", description = "Service unavailable or network error",
                                  content = @Content(mediaType = "application/json",
                                                     schema = @Schema(implementation = com.example.baiduapistoreconnector.exception.ErrorResponse.class)))
                 })
    public BaiduBaseResponse<WeatherInfo> getWeatherByName(
            @Parameter(description = "Name of the city in China (e.g., Beijing)", required = true)
            @RequestParam String cityName) throws IOException {
        return weatherApiService.getWeatherByCityName(cityName);
    }

    @GetMapping("/GetCityInfoByName")
    @Operation(summary = "Get City Information by City Name",
                 description = "Retrieves detailed information (like province, area ID) for a specified city in China.",
                 responses = {
                     @ApiResponse(responseCode = "200", description = "Successfully retrieved city data"),
                     @ApiResponse(responseCode = "502", description = "Error from Baidu API (e.g., city not found)",
                                  content = @Content(mediaType = "application/json",
                                                     schema = @Schema(implementation = com.example.baiduapistoreconnector.exception.ErrorResponse.class)))
                 })
    public BaiduBaseResponse<CityDetailInfo> getCityInfoByName(
            @Parameter(description = "Name of the city in China (e.g., Beijing)", required = true)
            @RequestParam String cityName) throws IOException {
        return weatherApiService.getCityInfoByCityName(cityName);
    }
}
