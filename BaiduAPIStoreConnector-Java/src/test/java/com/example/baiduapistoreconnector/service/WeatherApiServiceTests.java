package com.example.baiduapistoreconnector.service;

import com.example.baiduapistoreconnector.exception.BaiduApiResponseException;
import com.example.baiduapistoreconnector.model.BaiduBaseResponse;
import com.example.baiduapistoreconnector.model.CityDetailInfo;
import com.example.baiduapistoreconnector.model.WeatherInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WeatherApiServiceTests {

    @Mock
    OkHttpClient okHttpClientMock;

    @Mock
    BaiduApiConfig baiduApiConfigMock;

    @Mock
    Call callMock;

    @InjectMocks
    WeatherApiService weatherApiService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        weatherApiService = new WeatherApiService(okHttpClientMock, objectMapper, baiduApiConfigMock);
        when(baiduApiConfigMock.getApiKey()).thenReturn("test-api-key");
        when(okHttpClientMock.newCall(any(Request.class))).thenReturn(callMock);
    }

    // Tests for getWeatherByCityPinyin
    @Test
    void test_getWeatherByCityPinyin_success() throws IOException {
        String jsonResponse = "{\"errNum\":0,\"retMsg\":\"success\",\"retData\":{\"city\":\"Beijing\",\"pinyin\":\"beijing\",\"weather\":\"Cloudy\"}}";
        Response mockHttpResponse = new Response.Builder()
                .request(new Request.Builder().url("http://testurl.com").build())
                .protocol(Protocol.HTTP_1_1)
                .code(200).message("OK")
                .body(ResponseBody.create(jsonResponse, MediaType.parse("application/json")))
                .build();
        when(callMock.execute()).thenReturn(mockHttpResponse);

        BaiduBaseResponse<WeatherInfo> response = weatherApiService.getWeatherByCityPinyin("beijing");

        assertNotNull(response);
        assertEquals(0, response.getErrNum());
        assertEquals("success", response.getRetMsg());
        assertNotNull(response.getRetData());
        assertEquals("Beijing", response.getRetData().getCity());
        assertEquals("Cloudy", response.getRetData().getWeather());

        ArgumentCaptor<Request> requestCaptor = ArgumentCaptor.forClass(Request.class);
        verify(okHttpClientMock).newCall(requestCaptor.capture());
        assertTrue(requestCaptor.getValue().url().toString().contains("citypinyin=beijing"));
    }

    @Test
    void test_getWeatherByCityPinyin_baiduApiError() throws IOException {
        String jsonResponse = "{\"errNum\":300204,\"retMsg\":\"Invalid city pinyin\",\"retData\":null}";
        Response mockHttpResponse = new Response.Builder()
                .request(new Request.Builder().url("http://testurl.com").build())
                .protocol(Protocol.HTTP_1_1)
                .code(200).message("OK")
                .body(ResponseBody.create(jsonResponse, MediaType.parse("application/json")))
                .build();
        when(callMock.execute()).thenReturn(mockHttpResponse);

        assertThrows(BaiduApiResponseException.class, () -> weatherApiService.getWeatherByCityPinyin("invalidpinyin"));
    }

    @Test
    void test_getWeatherByCityPinyin_networkError() throws IOException {
        when(callMock.execute()).thenThrow(new IOException("Network error"));
        assertThrows(IOException.class, () -> weatherApiService.getWeatherByCityPinyin("beijing"));
    }

    @Test
    void test_getWeatherByCityPinyin_invalidInput() {
        assertThrows(IllegalArgumentException.class, () -> weatherApiService.getWeatherByCityPinyin(null));
        assertThrows(IllegalArgumentException.class, () -> weatherApiService.getWeatherByCityPinyin(" "));
    }


    // Tests for getWeatherByCityName
    @Test
    void test_getWeatherByCityName_success() throws IOException {
        String jsonResponse = "{\"errNum\":0,\"retMsg\":\"success\",\"retData\":{\"city\":\"Shanghai\",\"weather\":\"Sunny\"}}";
        Response mockHttpResponse = new Response.Builder()
            .request(new Request.Builder().url("http://testurl.com").build())
            .protocol(Protocol.HTTP_1_1).code(200).message("OK")
            .body(ResponseBody.create(jsonResponse, MediaType.parse("application/json"))).build();
        when(callMock.execute()).thenReturn(mockHttpResponse);

        BaiduBaseResponse<WeatherInfo> response = weatherApiService.getWeatherByCityName("Shanghai");

        assertNotNull(response);
        assertEquals(0, response.getErrNum());
        assertEquals("Shanghai", response.getRetData().getCity());
        ArgumentCaptor<Request> requestCaptor = ArgumentCaptor.forClass(Request.class);
        verify(okHttpClientMock).newCall(requestCaptor.capture());
        assertTrue(requestCaptor.getValue().url().toString().contains("cityname=Shanghai"));
    }

    @Test
    void test_getWeatherByCityName_baiduApiError() throws IOException {
        String jsonResponse = "{\"errNum\":300205,\"retMsg\":\"Invalid city name\",\"retData\":null}";
        Response mockHttpResponse = new Response.Builder()
            .request(new Request.Builder().url("http://testurl.com").build())
            .protocol(Protocol.HTTP_1_1).code(200).message("OK")
            .body(ResponseBody.create(jsonResponse, MediaType.parse("application/json"))).build();
        when(callMock.execute()).thenReturn(mockHttpResponse);
        assertThrows(BaiduApiResponseException.class, () -> weatherApiService.getWeatherByCityName("InvalidCity"));
    }

    @Test
    void test_getWeatherByCityName_networkError() throws IOException {
        when(callMock.execute()).thenThrow(new IOException("Network problem"));
        assertThrows(IOException.class, () -> weatherApiService.getWeatherByCityName("Shanghai"));
    }

     @Test
    void test_getWeatherByCityName_invalidInput() {
        assertThrows(IllegalArgumentException.class, () -> weatherApiService.getWeatherByCityName(null));
        assertThrows(IllegalArgumentException.class, () -> weatherApiService.getWeatherByCityName("  "));
    }


    // Tests for getCityInfoByCityName
    @Test
    void test_getCityInfoByCityName_success() throws IOException {
        String jsonResponse = "{\"errNum\":0,\"retMsg\":\"success\",\"retData\":{\"province_cn\":\"Beijing\",\"name_cn\":\"Beijing\"}}";
         Response mockHttpResponse = new Response.Builder()
            .request(new Request.Builder().url("http://testurl.com").build())
            .protocol(Protocol.HTTP_1_1).code(200).message("OK")
            .body(ResponseBody.create(jsonResponse, MediaType.parse("application/json"))).build();
        when(callMock.execute()).thenReturn(mockHttpResponse);

        BaiduBaseResponse<CityDetailInfo> response = weatherApiService.getCityInfoByCityName("Beijing");

        assertNotNull(response);
        assertEquals(0, response.getErrNum());
        assertEquals("Beijing", response.getRetData().getProvinceCn());
        ArgumentCaptor<Request> requestCaptor = ArgumentCaptor.forClass(Request.class);
        verify(okHttpClientMock).newCall(requestCaptor.capture());
        assertTrue(requestCaptor.getValue().url().toString().contains("cityinfo?cityname=Beijing"));
    }

    @Test
    void test_getCityInfoByCityName_baiduApiError() throws IOException {
        String jsonResponse = "{\"errNum\":300201,\"retMsg\":\"City not found\",\"retData\":null}";
        Response mockHttpResponse = new Response.Builder()
            .request(new Request.Builder().url("http://testurl.com").build())
            .protocol(Protocol.HTTP_1_1).code(200).message("OK")
            .body(ResponseBody.create(jsonResponse, MediaType.parse("application/json"))).build();
        when(callMock.execute()).thenReturn(mockHttpResponse);
        assertThrows(BaiduApiResponseException.class, () -> weatherApiService.getCityInfoByCityName("NonExistentCity"));
    }

    @Test
    void test_getCityInfoByCityName_networkError() throws IOException {
        when(callMock.execute()).thenThrow(new IOException("Connection failed"));
        assertThrows(IOException.class, () -> weatherApiService.getCityInfoByCityName("Beijing"));
    }

    @Test
    void test_getCityInfoByCityName_invalidInput() {
        assertThrows(IllegalArgumentException.class, () -> weatherApiService.getCityInfoByCityName(null));
        assertThrows(IllegalArgumentException.class, () -> weatherApiService.getCityInfoByCityName(""));
    }
}
