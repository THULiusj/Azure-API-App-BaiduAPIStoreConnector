using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BaiduAPIStoreConnector.Models
{
    class ResponseData
    {
    }
    public class RetDataAQI
    {
        public string city { get; set; }
        public string time { get; set; }
        public string aqi { get; set; }
        public string level { get; set; }
        public string core { get; set; }
    }
    public class RetDataInfo
    {
        public string cityName { get; set; }
        public string provinceName { get; set; }
        public string cityCode { get; set; }
        public string zipCode { get; set; }
        public string telAreaCode { get; set; }
    }

    public class RetDataWeatherInfo
    {
        public string city { get; set; }
        public string pinyin { get; set; }
        public string citycode { get; set; }
        public string date { get; set; }
        public string time { get; set; }
        public string postcode { get; set; }
        public string longitude { get; set; }
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

    public class RetDataIDCardDetail
    {
        public string sex { get; set; }
        public string birthday { get; set; }
        public string address { get; set; }

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

    public class RetDataTeleNum
    {
        public string telString { get; set; }
        public string province { get; set; }
        public string carrier { get; set; }

    }
}
