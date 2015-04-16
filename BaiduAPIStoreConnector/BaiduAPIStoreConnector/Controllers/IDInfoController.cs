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
    public class IDInfoController : TemplateAPIController
    {
        [HttpGet]
        [ActionName("GetIDInfo")]
        public async Task<RetDataIDCardDetail> Get(string Id)
        {
            string completeUrl = string.Format("icardinfo?id={0}", Id);
            return await InnerExecute<RetDataIDCardDetail>(completeUrl);
        }
    }
}
