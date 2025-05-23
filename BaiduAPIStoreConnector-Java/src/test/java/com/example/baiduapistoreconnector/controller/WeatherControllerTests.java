package com.example.baiduapistoreconnector.controller;

import com.example.baiduapistoreconnector.config.AppConfig;
import com.example.baiduapistoreconnector.exception.BaiduApiResponseException;
import com.example.baiduapistoreconnector.model.BaiduBaseResponse;
import com.example.baiduapistoreconnector.model.CityDetailInfo;
import com.example.baiduapistoreconnector.model.WeatherInfo;
import com.example.baiduapistoreconnector.service.BaiduApiConfig;
import com.example.baiduapistoreconnector.service.WeatherApiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WeatherController.class)
@Import(AppConfig.class) // To provide OkHttpClient bean for WeatherApiService
public class WeatherControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherApiService weatherApiServiceMock;

    @MockBean
    private BaiduApiConfig baiduApiConfigMock; // Mock this as it's used by the service

    // --- Tests for GetWeatherInfoByPinyin ---
    @Test
    void test_getWeatherByPinyin_success() throws Exception {
        BaiduBaseResponse<WeatherInfo> successResponse = new BaiduBaseResponse<>();
        successResponse.setErrNum(0);
        WeatherInfo weatherData = new WeatherInfo();
        weatherData.setPinyin("beijing");
        weatherData.setWeather("Sunny");
        successResponse.setRetData(weatherData);

        when(weatherApiServiceMock.getWeatherByCityPinyin("beijing")).thenReturn(successResponse);

        mockMvc.perform(get("/api/Weather/GetWeatherInfoByPinyin").param("cityPinyin", "beijing"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errNum").value(0))
                .andExpect(jsonPath("$.retData.pinyin").value("beijing"));
    }

    @Test
    void test_getWeatherByPinyin_serviceError() throws Exception {
        when(weatherApiServiceMock.getWeatherByCityPinyin("beijing"))
                .thenThrow(new BaiduApiResponseException(1, "Baidu error", "Error from Baidu"));

        mockMvc.perform(get("/api/Weather/GetWeatherInfoByPinyin").param("cityPinyin", "beijing"))
                .andExpect(status().isBadGateway())
                .andExpect(jsonPath("$.message").value("Baidu API Error: Error from Baidu (errNum: 1)"));
    }

    @Test
    void test_getWeatherByPinyin_missingParam() throws Exception {
        mockMvc.perform(get("/api/Weather/GetWeatherInfoByPinyin"))
                .andExpect(status().isBadRequest());
    }

    // --- Tests for GetWeatherInfoByName ---
    @Test
    void test_getWeatherByName_success() throws Exception {
        BaiduBaseResponse<WeatherInfo> successResponse = new BaiduBaseResponse<>();
        successResponse.setErrNum(0);
        WeatherInfo weatherData = new WeatherInfo();
        weatherData.setCity("Beijing");
        weatherData.setWeather("Clear");
        successResponse.setRetData(weatherData);

        when(weatherApiServiceMock.getWeatherByCityName("Beijing")).thenReturn(successResponse);

        mockMvc.perform(get("/api/Weather/GetWeatherInfoByName").param("cityName", "Beijing"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errNum").value(0))
                .andExpect(jsonPath("$.retData.city").value("Beijing"));
    }

    @Test
    void test_getWeatherByName_serviceThrowsIOException() throws Exception {
        when(weatherApiServiceMock.getWeatherByCityName("Beijing"))
                .thenThrow(new IOException("Network issue"));

        mockMvc.perform(get("/api/Weather/GetWeatherInfoByName").param("cityName", "Beijing"))
                .andExpect(status().isServiceUnavailable())
                .andExpect(jsonPath("$.message").value("Service unavailable or network error: Network issue"));
    }
    
    @Test
    void test_getWeatherByName_missingParam() throws Exception {
        mockMvc.perform(get("/api/Weather/GetWeatherInfoByName"))
                .andExpect(status().isBadRequest());
    }

    // --- Tests for GetCityInfoByName ---
    @Test
    void test_getCityInfoByName_success() throws Exception {
        BaiduBaseResponse<CityDetailInfo> successResponse = new BaiduBaseResponse<>();
        successResponse.setErrNum(0);
        CityDetailInfo cityData = new CityDetailInfo();
        cityData.setNameCn("Beijing");
        cityData.setAreaId("101010100");
        successResponse.setRetData(cityData);

        when(weatherApiServiceMock.getCityInfoByCityName("Beijing")).thenReturn(successResponse);

        mockMvc.perform(get("/api/Weather/GetCityInfoByName").param("cityName", "Beijing"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.errNum").value(0))
                .andExpect(jsonPath("$.retData.name_cn").value("Beijing"));
    }
    
    @Test
    void test_getCityInfoByName_serviceError_BaiduApi() throws Exception {
        when(weatherApiServiceMock.getCityInfoByCityName(anyString()))
                .thenThrow(new BaiduApiResponseException(300204, "Invalid City", "Baidu: Invalid City"));

        mockMvc.perform(get("/api/Weather/GetCityInfoByName").param("cityName", "NonExistent"))
                .andExpect(status().isBadGateway()) // Or the specific status your GlobalExceptionHandler returns for BaiduApiResponseException
                .andExpect(jsonPath("$.message").value("Baidu API Error: Invalid City (errNum: 300204)"));
    }


    @Test
    void test_getCityInfoByName_missingParam() throws Exception {
        mockMvc.perform(get("/api/Weather/GetCityInfoByName"))
                .andExpect(status().isBadRequest());
    }
}
