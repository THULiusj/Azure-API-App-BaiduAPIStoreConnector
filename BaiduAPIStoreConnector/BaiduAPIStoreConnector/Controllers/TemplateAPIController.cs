using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Configuration;
using System.Web.Http;
using BaiduAPIStoreConnector.Models;

namespace BaiduAPIStoreConnector.Controllers
{
    public class TemplateAPIController : ApiController
    {
        public HttpClient client = new HttpClient();
        public TemplateAPIController()
        {
            client.BaseAddress = new Uri(WebConfigurationManager.AppSettings["BaiduAPIStoreUrl"]);
        }
        protected async Task<T> InnerExecute<T>(string completeUrl)
        {
            HttpResponseMessage response = await client.GetAsync(completeUrl);
            string responsebody = await response.Content.ReadAsStringAsync();
            ResponseInfo<T> container = JsonConvert.DeserializeObject<ResponseInfo<T>>(responsebody);
            return container.retData;
        }


    }
}
