package com.example.baiduapistoreconnector.service;

import com.example.baiduapistoreconnector.exception.BaiduApiResponseException;
import com.example.baiduapistoreconnector.model.AqiInfo;
import com.example.baiduapistoreconnector.model.BaiduBaseResponse;
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
public class AqiApiServiceTests {

    @Mock
    OkHttpClient okHttpClientMock;

    @Mock
    BaiduApiConfig baiduApiConfigMock;

    @Mock
    Call callMock;

    @InjectMocks
    AqiApiService aqiApiService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        aqiApiService = new AqiApiService(okHttpClientMock, objectMapper, baiduApiConfigMock);
        when(baiduApiConfigMock.getApiKey()).thenReturn("test-api-key");
        when(okHttpClientMock.newCall(any(Request.class))).thenReturn(callMock);
    }

    @Test
    void test_getAqiByCityName_success() throws IOException {
        // Prepare mock response
        String jsonResponse = "{\"errNum\":0,\"retMsg\":\"success\",\"retData\":{\"city\":\"Beijing\",\"aqi\":50,\"level\":\"Good\",\"core\":\"PM2.5\",\"time\":\"2023-10-27\"}}";
        Response mockHttpResponse = new Response.Builder()
                .request(new Request.Builder().url("http://testurl.com").build())
                .protocol(Protocol.HTTP_1_1)
                .code(200)
                .message("OK")
                .body(ResponseBody.create(jsonResponse, MediaType.parse("application/json")))
                .build();
        when(callMock.execute()).thenReturn(mockHttpResponse);

        BaiduBaseResponse<AqiInfo> response = aqiApiService.getAqiByCityName("Beijing");

        assertNotNull(response);
        assertEquals(0, response.getErrNum());
        assertEquals("success", response.getRetMsg());
        assertNotNull(response.getRetData());
        assertEquals("Beijing", response.getRetData().getCity());
        assertEquals(50, response.getRetData().getAqi());

        ArgumentCaptor<Request> requestCaptor = ArgumentCaptor.forClass(Request.class);
        verify(okHttpClientMock).newCall(requestCaptor.capture());
        Request capturedRequest = requestCaptor.getValue();
        assertTrue(capturedRequest.url().toString().contains("city=Beijing"));
        assertEquals("test-api-key", capturedRequest.header("apikey"));
    }

    @Test
    void test_getAqiByCityName_baiduApiError() throws IOException {
        String jsonResponse = "{\"errNum\":300204,\"retMsg\":\"Invalid city\",\"retData\":null}";
        Response mockHttpResponse = new Response.Builder()
                .request(new Request.Builder().url("http://testurl.com").build())
                .protocol(Protocol.HTTP_1_1)
                .code(200) // Baidu API often returns 200 OK even for API-level errors
                .message("OK")
                .body(ResponseBody.create(jsonResponse, MediaType.parse("application/json")))
                .build();
        when(callMock.execute()).thenReturn(mockHttpResponse);

        BaiduApiResponseException exception = assertThrows(BaiduApiResponseException.class, () -> {
            aqiApiService.getAqiByCityName("InvalidCity");
        });
        assertEquals(300204, exception.getErrNum());
        assertEquals("Invalid city", exception.getRetMsg());
    }

    @Test
    void test_getAqiByCityName_networkError() throws IOException {
        when(callMock.execute()).thenThrow(new IOException("Network issue"));

        IOException exception = assertThrows(IOException.class, () -> {
            aqiApiService.getAqiByCityName("Beijing");
        });
        assertEquals("Network issue", exception.getMessage());
    }

    @Test
    void test_getAqiByCityName_invalidCity_null() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            aqiApiService.getAqiByCityName(null);
        });
        assertEquals("City Name cannot be null or empty.", exception.getMessage());
    }

    @Test
    void test_getAqiByCityName_invalidCity_empty() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            aqiApiService.getAqiByCityName("  ");
        });
        assertEquals("City Name cannot be null or empty.", exception.getMessage());
    }
}
