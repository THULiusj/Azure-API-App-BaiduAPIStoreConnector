package com.example.baiduapistoreconnector.service;

import com.example.baiduapistoreconnector.model.AqiInfo;
import com.example.baiduapistoreconnector.model.BaiduBaseResponse;
import com.example.baiduapistoreconnector.exception.BaiduApiResponseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class AqiApiService {

    private final OkHttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final BaiduApiConfig baiduApiConfig;

    public AqiApiService(OkHttpClient httpClient, ObjectMapper objectMapper, BaiduApiConfig baiduApiConfig) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
        this.baiduApiConfig = baiduApiConfig;
    }

    public BaiduBaseResponse<AqiInfo> getAqiByCityName(String cityName) throws IOException {
        if (cityName == null || cityName.trim().isEmpty()) {
            throw new IllegalArgumentException("City Name cannot be null or empty.");
        }
        String encodedCityName;
        try {
            encodedCityName = URLEncoder.encode(cityName, StandardCharsets.UTF_8.name());
        } catch (java.io.UnsupportedEncodingException e) {
            throw new IOException("Failed to URL encode cityName: " + cityName, e);
        }

        String url = BaiduApiConfig.AQI_BASE_URL + "/aqi?city=" + encodedCityName;
        Request request = new Request.Builder()
                .url(url)
                .header("apikey", baiduApiConfig.getApiKey())
                .get()
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected HTTP code " + response.code() + " " + response.message() + " for URL: " + url);
            }
            ResponseBody body = response.body();
            if (body == null) {
                throw new IOException("Response body is null for URL: " + url);
            }
            String jsonResponse = body.string();
            BaiduBaseResponse<AqiInfo> baiduResponse = objectMapper.readValue(jsonResponse, new TypeReference<BaiduBaseResponse<AqiInfo>>() {});
            if (baiduResponse.getErrNum() != 0) {
                throw new BaiduApiResponseException(baiduResponse.getErrNum(), baiduResponse.getRetMsg(), "Error from Baidu API: " + baiduResponse.getRetMsg());
            }
            return baiduResponse;
        }
    }
}
