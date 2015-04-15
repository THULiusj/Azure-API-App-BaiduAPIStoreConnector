using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BaiduAPIStoreConnector.Models
{
    class TeleNumInfo
    {
        public string errNum { get; set; }
        public string retMsg { get; set; }
        public RetDataTeleNum retData { get; set; }
    }
        public class RetDataTeleNum
        {
            public string telString { get; set; }
            public string province { get; set; }
            public string carrier { get; set; }

        }
}
