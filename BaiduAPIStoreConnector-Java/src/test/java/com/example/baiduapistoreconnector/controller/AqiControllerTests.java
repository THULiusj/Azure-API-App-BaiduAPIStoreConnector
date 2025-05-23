package com.example.baiduapistoreconnector.controller;

import com.example.baiduapistoreconnector.config.AppConfig;
import com.example.baiduapistoreconnector.exception.BaiduApiResponseException;
import com.example.baiduapistoreconnector.model.AqiInfo;
import com.example.baiduapistoreconnector.model.BaiduBaseResponse;
import com.example.baiduapistoreconnector.service.AqiApiService;
import com.example.baiduapistoreconnector.service.BaiduApiConfig;
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

@WebMvcTest(AqiController.class)
@Import(AppConfig.class) // Import AppConfig to provide OkHttpClient, which is a dependency of AqiApiService
public class AqiControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AqiApiService aqiApiServiceMock;

    @MockBean
    private BaiduApiConfig baiduApiConfigMock; // Mock this as it's used by the service for API key

    @Test
    void test_getAqiByName_success() throws Exception {
        BaiduBaseResponse<AqiInfo> successResponse = new BaiduBaseResponse<>();
        successResponse.setErrNum(0);
        successResponse.setRetMsg("success");
        AqiInfo aqiData = new AqiInfo();
        aqiData.setCity("Beijing");
        aqiData.setAqi(50);
        successResponse.setRetData(aqiData);

        when(aqiApiServiceMock.getAqiByCityName("Beijing")).thenReturn(successResponse);

        mockMvc.perform(get("/api/AQI/GetAQIByName").param("cityName", "Beijing"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errNum").value(0))
                .andExpect(jsonPath("$.retData.city").value("Beijing"))
                .andExpect(jsonPath("$.retData.aqi").value(50));
    }

    @Test
    void test_getAqiByName_serviceThrowsBaiduError() throws Exception {
        when(aqiApiServiceMock.getAqiByCityName("Beijing"))
                .thenThrow(new BaiduApiResponseException(300204, "Invalid city", "Error from Baidu API: Invalid city"));

        mockMvc.perform(get("/api/AQI/GetAQIByName").param("cityName", "Beijing"))
                .andExpect(status().isBadGateway()) // As per GlobalExceptionHandler
                .andExpect(jsonPath("$.status").value(502))
                .andExpect(jsonPath("$.message").value("Baidu API Error: Invalid city (errNum: 300204)"));
    }
    
    @Test
    void test_getAqiByName_serviceThrowsIOException() throws Exception {
        when(aqiApiServiceMock.getAqiByCityName("Beijing"))
                .thenThrow(new IOException("Network connection failed"));

        mockMvc.perform(get("/api/AQI/GetAQIByName").param("cityName", "Beijing"))
                .andExpect(status().isServiceUnavailable()) // As per GlobalExceptionHandler for IOException
                .andExpect(jsonPath("$.status").value(503))
                .andExpect(jsonPath("$.message").value("Service unavailable or network error: Network connection failed"));
    }


    @Test
    void test_getAqiByName_missingCityNameParam() throws Exception {
        mockMvc.perform(get("/api/AQI/GetAQIByName"))
                .andExpect(status().isBadRequest()); // Spring typically handles missing required params with 400
    }
}
