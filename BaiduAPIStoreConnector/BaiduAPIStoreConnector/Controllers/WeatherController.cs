using BaiduAPIStoreConnector.Models;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Configuration;
using System.Web.Http;

namespace BaiduAPIStoreConnector.Controllers
{
    public class WeatherController : ApiController
    {
        HttpClient client = new HttpClient();

        public WeatherController()
        {
            client.BaseAddress = new Uri(WebConfigurationManager.AppSettings["BaiduAPIStoreUrl"]);
        }

        [HttpGet]
        [ActionName("GetWeatherInfo")]
        public async Task<RetDataWeatherInfo> Get(string CityName = null, string CityPinyin = null, string CityID = null)
        {
            CityWeather cityweathercontainer = null;
            HttpResponseMessage response = null;
            if (CityName == null && CityID == null && CityPinyin == null)
                return (new RetDataWeatherInfo());
            if (CityName != null)
            {
                response = await client.GetAsync(string.Format("weather?cityname={0}", CityName));
            }
            else if (CityPinyin != null)
                response = await client.GetAsync(string.Format("weather?citypinyin={0}", CityPinyin));
            else
                response = await client.GetAsync(string.Format("weather?cityid={0}", CityID));
            string responsebody = await response.Content.ReadAsStringAsync();
            cityweathercontainer = JsonConvert.DeserializeObject<CityWeather>(responsebody);
            return cityweathercontainer.retData;
        }

        [HttpGet]
        [ActionName("GetCityInfo")]
        public async Task<RetDataInfo> GetCityInfo(string CityName)
        {
            CityInfo cityinfocontainer = null;
            HttpResponseMessage response = await client.GetAsync(string.Format("cityinfo?cityname={0}", CityName));
            string responsebody = await response.Content.ReadAsStringAsync();
            cityinfocontainer = JsonConvert.DeserializeObject<CityInfo>(responsebody);
            return cityinfocontainer.retData;
        }
    }
}
