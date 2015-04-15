using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace BaiduAPIStoreConnector.Models
{
        public class CityInfo
        {
            public string errNum { get; set; }
            public string retMsg { get; set; }
            public RetDataInfo retData { get; set; }
        }

        public class RetDataInfo
        {
            public string cityName { get; set; }
            public string provinceName { get; set; }
            public string cityCode { get; set; }
            public string zipCode { get; set; }
            public string telAreaCode { get; set; }
        }
}