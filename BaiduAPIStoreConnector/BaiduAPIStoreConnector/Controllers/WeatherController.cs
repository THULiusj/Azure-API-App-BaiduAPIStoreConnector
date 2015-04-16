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
    public class WeatherController : TemplateAPIController
    {
        [HttpGet]
        [ActionName("GetWeatherInfo")]
        public async Task<RetDataWeatherInfo> Get(string CityName = null, string CityPinyin = null, string CityID = null)
        {
            if (CityName == null && CityID == null && CityPinyin == null)
                return (new RetDataWeatherInfo());
            string completeUrl = null;
            if (CityName != null)
            {
                completeUrl = string.Format("weather?cityname={0}", CityName);
            }
            else if (CityPinyin != null)
            {
                completeUrl = string.Format("weather?citypinyin={0}", CityPinyin);
            }
            else
            {
                completeUrl = string.Format("weather?cityid={0}", CityID);
            }
            return await InnerExecute<RetDataWeatherInfo>(completeUrl);
        }

        [HttpGet]
        [ActionName("GetCityInfo")]
        public async Task<RetDataInfo> GetCityInfo(string CityName)
        {
            string completeUrl = string.Format("cityinfo?cityname={0}", CityName);
            return await InnerExecute<RetDataInfo>(completeUrl);
        }
    }
}
