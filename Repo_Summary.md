**Project Title:** Azure API App - Baidu API Store Connector

**Project Purpose:**
This project is an Azure API App that serves as a connector to the Baidu API store. It simplifies access to various Baidu APIs by exposing them as a set of RESTful services. This allows developers to easily integrate these services into their own applications (console, mobile, web) or use them within Azure Logic Apps to build workflows.

**Key Features (APIs Provided):**
The application provides the following REST APIs, primarily focused on data related to China:
- **AQI (Air Quality Index):** Retrieves AQI data for a specified city in China.
- **Currency:** Performs currency exchange rate conversions.
- **IDInfo:** Fetches information based on a Chinese ID card number.
- **MobilePhone:** Provides location and carrier information for a Chinese mobile phone number.
- **Weather:**
    - Gets weather information for a specified city in China (using city name, pinyin, or city code).
    - Gets city information like city code and zip code for a specified city in China.

**Main Technologies Used:**
- **Programming Language & Framework:** C#, ASP.NET Web API
- **Platform:** Microsoft Azure API App
- **External Service Integration:** Baidu API Store
- **API Documentation:** Swagger (Swashbuckle)

**Deployment & Usage:**
The application is designed to be deployed as an Azure API App. Once deployed, it can be consumed directly via its REST endpoints, through SDKs generated for client applications (e.g., .NET console apps), or integrated into Azure Logic Apps.

This project demonstrates how to wrap existing third-party APIs into an Azure API App, making them more accessible and manageable within the Azure ecosystem.
