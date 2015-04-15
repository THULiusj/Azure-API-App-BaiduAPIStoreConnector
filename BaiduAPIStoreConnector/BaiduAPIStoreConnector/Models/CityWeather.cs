using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BaiduAPIStoreConnector.Models
{
        public class CityWeather
        {
            public string errNum { get; set; }
            public string retMsg { get; set; }
            public RetDataWeatherInfo retData { get; set; }
        }

        public class RetDataWeatherInfo
        {
            public RetDataWeatherInfo ()
            {

            }
            public string city { get; set; }
            public string pinyin { get; set; }
            public string citycode { get; set; }
            public string date { get; set; }
            public string time { get; set; }
            public string postcode { get; set; }
            public string longtitude { get; set; }
            public string latitude { get; set; }
            public string altitude { get; set; }
            public string weather { get; set; }
            public string temp { get; set; }
            public string l_tmp { get; set; }
            public string h_tmp { get; set; }
            public string WD { get; set; }
            public string WS { get; set; }
            public string sunrise { get; set; }
            public string sunset { get; set; }


        }
}
