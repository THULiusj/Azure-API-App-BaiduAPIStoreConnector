using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BaiduAPIStoreConnector.Models
{
    class CityAQI
    {
        public string errNum { get; set; }
        public string retMsg { get; set; }
        public RetDataAQI retData { get; set; }
    }
    public class RetDataAQI
    {
        public string city { get; set; }
        public string time { get; set; }
        public string aqi { get; set; }
        public string level { get; set; }
        public string core { get; set; }
    }
}
