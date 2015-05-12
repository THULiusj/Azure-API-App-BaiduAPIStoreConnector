# Azure-API-App
    This sample code is an Azure API App. 
    It's based on Baidu API store(http://apistore.baidu.com). Baidu API store provides a variety of APIs, like weather query API, exchange rate query API, map API, social share API and so on. 
    This sample code implements the REST API of query of weather, exchange rate, air quality index, mobile phone location and information of identity card, through implementing Azure API App, and calling the related Baidu public, free and anonymous REST APIs.
    You can publish this sample to an Azure API App, and get the below REST APIs:
    1. Query for air condition index, the input parameter is city name(only cities in China)
       GET: .../api/AQI/GetAQI
    2. Query for current exchange rate, the input parameter is source currency, destination currency and currency amount
       GET: .../api/Currency/GetCurrency
    3. Query for identity card information, the input parameter is ID (only in China)
       GET: .../api/IDInfo/GetIDInfo
    4. Query for location and carrier of mobile phone, the input parameter is mobile phone number (only in China)
       GET: .../api/MobilePhone/GetMobilePhone
    5. Query for weather, the input parameter is city name or city pinyin or city code (only in China)
       GET: .../api/Weather/GetWeatherInfo
    6. Query for city information like city code, zip code, the input parameter is city name (only in China)
       GET: .../api/Weather/GetCityInfo

    If you have a console or mobile application or website, you can download the SDK automatically generated by this API App from Visual Studio, install it in the application and call the functions in it to implement query.
    You can also use it in a logic app to implement some blocks in the workflow.

	该Sample Code是基于百度API商店的即用API实现的Azure API App，包括中国国内城市查询、城市空气质量查询、移动电话号码归属地查询、身份证信息查询和汇率查询等功能的REST API。
代码使用方法：
1. 在Azure管理门户(目前Azure API App只在Global Azure上可用)中创建一个Azure API App服务。

