using BaiduAPIStoreConnector.Models;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Configuration;
using System.Web.Http;

namespace BaiduAPIStoreConnector.Controllers
{
    public class AQIController : ApiController
    {
        HttpClient client = new HttpClient();

        public AQIController()
        {
            client.BaseAddress = new Uri(WebConfigurationManager.AppSettings["BaiduAPIStoreUrl"]);
        }
        public async Task<RetDataAQI> Get(string CityName)
        {
            CityAQI cityaqicontainer = null;
            HttpResponseMessage response = await client.GetAsync(string.Format("aqi?city={0}", CityName));
            string responsebody = await response.Content.ReadAsStringAsync();
            cityaqicontainer = JsonConvert.DeserializeObject<CityAQI>(responsebody);
            return cityaqicontainer.retData;
        }
    }
}
