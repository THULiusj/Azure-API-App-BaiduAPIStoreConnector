package com.example.baiduapistoreconnector.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BaiduApiConfig {

    @Value("${baidu.api.key}")
    private String apiKey;

    // Base URLs for different Baidu API services
    public static final String WEATHER_BASE_URL = "http://apis.baidu.com/apistore/weatherservice";
    public static final String AQI_BASE_URL = "http://apis.baidu.com/apistore/aqiservice";
    // Add other base URLs as needed, for example:
    // public static final String CURRENCY_BASE_URL = "http://apis.baidu.com/apistore/currencyservice";
    // public static final String ID_INFO_BASE_URL = "http://apis.baidu.com/apistore/idservice";
    // public static final String MOBILE_PHONE_BASE_URL = "http://apis.baidu.com/apistore/mobileservice";

    public String getApiKey() {
        return apiKey;
    }
}
