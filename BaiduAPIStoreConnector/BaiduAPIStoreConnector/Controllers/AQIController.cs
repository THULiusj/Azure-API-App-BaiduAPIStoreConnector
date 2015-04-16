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
    public class AQIController : TemplateAPIController
    {
        [HttpGet]
        [ActionName("GetAQI")]
        public async Task<RetDataAQI> Get(string CityName)
        {
            string completeUrl = string.Format("aqi?city={0}", CityName);
            return await InnerExecute<RetDataAQI>(completeUrl);
        }
    }
}
