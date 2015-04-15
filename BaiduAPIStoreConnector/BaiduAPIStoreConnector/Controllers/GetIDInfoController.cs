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
    public class GetIDInfoController : ApiController
    {
        HttpClient client = new HttpClient();

        public GetIDInfoController()
        {
            client.BaseAddress = new Uri(WebConfigurationManager.AppSettings["BaiduAPIStoreUrl"]);
        }
        public async Task<RetDataIDCardDetail> Get(string Id)
        {
            IDCard IDcardcontainer = null;
            HttpResponseMessage response = await client.GetAsync(string.Format("icardinfo?id={0}", Id));
            string responsebody = await response.Content.ReadAsStringAsync();
            IDcardcontainer = JsonConvert.DeserializeObject<IDCard>(responsebody);
            return IDcardcontainer.retData;
        }
    }
}
