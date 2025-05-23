package com.example.baiduapistoreconnector.controller;

import com.example.baiduapistoreconnector.model.AqiInfo;
import com.example.baiduapistoreconnector.model.BaiduBaseResponse;
import com.example.baiduapistoreconnector.service.AqiApiService;
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
@RequestMapping("/api/AQI")
@Tag(name = "AQI Controller", description = "Provides Air Quality Index (AQI) information for Chinese cities.")
public class AqiController {

    private final AqiApiService aqiApiService;

    public AqiController(AqiApiService aqiApiService) {
        this.aqiApiService = aqiApiService;
    }

    @GetMapping("/GetAQIByName")
    @Operation(summary = "Get AQI by City Name",
                 description = "Retrieves the Air Quality Index (AQI) for a specified city in China.",
                 responses = {
                     @ApiResponse(responseCode = "200", description = "Successfully retrieved AQI data",
                                  content = @Content(mediaType = "application/json",
                                                     schema = @Schema(implementation = BaiduBaseResponse.class))), // Specify wrapper
                     @ApiResponse(responseCode = "400", description = "Invalid city name provided or other bad request",
                                  content = @Content(mediaType = "application/json",
                                                     schema = @Schema(implementation = com.example.baiduapistoreconnector.exception.ErrorResponse.class))),
                     @ApiResponse(responseCode = "502", description = "Error from Baidu API",
                                  content = @Content(mediaType = "application/json",
                                                     schema = @Schema(implementation = com.example.baiduapistoreconnector.exception.ErrorResponse.class))),
                     @ApiResponse(responseCode = "503", description = "Service unavailable or network error",
                                  content = @Content(mediaType = "application/json",
                                                     schema = @Schema(implementation = com.example.baiduapistoreconnector.exception.ErrorResponse.class)))
                 })
    public BaiduBaseResponse<AqiInfo> getAqiByName(
            @Parameter(description = "Name of the city in China (e.g., Beijing)", required = true)
            @RequestParam String cityName) throws IOException {
        return aqiApiService.getAqiByCityName(cityName);
    }
}
