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
    public class CurrencyController : TemplateAPIController
    {
        [HttpGet]
        [ActionName("GetCurrency")]
        public async Task<RetDataCurrency> Get(string fromCurrency, string toCurrency, string amount)
        {
            string completeUrl = string.Format("currency?fromCurrency={0}&toCurrency={1}&amount={2}", fromCurrency, toCurrency, amount);
            return await InnerExecute<RetDataCurrency>(completeUrl);
        }
    }
}
