using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BaiduAPIStoreConnector.Models
{
    class IDCard
    {
        public string errNum { get; set; }
        public string retMsg { get; set; }
        public RetDataIDCardDetail retData { get; set; }
    }
    public class RetDataIDCardDetail
    {
        public string sex { get; set; }
        public string birthday { get; set; }
        public string address { get; set; }

    }
}
