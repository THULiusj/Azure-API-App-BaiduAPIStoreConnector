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
    public class MobilePhoneController : ApiController
    {
        HttpClient client = new HttpClient();

        public MobilePhoneController()
        {
            client.BaseAddress = new Uri(WebConfigurationManager.AppSettings["BaiduAPIStoreUrl"]);
        }
        public async Task<RetDataTeleNum> Get(string tel)
        {
            TeleNumInfo telenumcontainer = null;
            HttpResponseMessage response = await client.GetAsync(string.Format("mobilephone?tel={0}", tel));
            string responsebody = await response.Content.ReadAsStringAsync();
            telenumcontainer = JsonConvert.DeserializeObject<TeleNumInfo>(responsebody);
            return telenumcontainer.retData;
        }
    }
}
