using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BaiduAPIStoreConnector.Models
{
        class IntlCurrency
        {
            public string errNum { get; set; }
            public string retMsg { get; set; }
            public RetDataCurrency retData { get; set; }
        }
        public class RetDataCurrency
        {
            public string date { get; set; }
            public string time { get; set; }
            public string fromCurrency { get; set; }
            public string currency { get; set; }
            public string toCurrency { get; set; }
            public string convertedamount { get; set; }
        }
}
