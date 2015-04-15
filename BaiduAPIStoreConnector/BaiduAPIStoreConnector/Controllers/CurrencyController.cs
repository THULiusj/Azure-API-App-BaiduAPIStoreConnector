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
    public class CurrencyController : ApiController
    {
        HttpClient client = new HttpClient();

        public CurrencyController()
        {
            client.BaseAddress = new Uri(WebConfigurationManager.AppSettings["BaiduAPIStoreUrl"]);
        }
        public async Task<RetDataCurrency> Get(string fromCurrency, string toCurrency, string amount)
        {
            IntlCurrency currencycontainer = null;
            HttpResponseMessage response = await client.GetAsync(string.Format("currency?fromCurrency={0}&toCurrency={1}&amount={2}", fromCurrency, toCurrency, amount));
            string responsebody = await response.Content.ReadAsStringAsync();
            currencycontainer = JsonConvert.DeserializeObject<IntlCurrency>(responsebody);
            return currencycontainer.retData;
        }
    }
}
