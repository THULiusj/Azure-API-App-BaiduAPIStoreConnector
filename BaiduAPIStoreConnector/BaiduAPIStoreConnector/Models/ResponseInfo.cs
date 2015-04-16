using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BaiduAPIStoreConnector.Models
{
    class ResponseInfo<T>
    {
        public string errNum { get; set; }
        public string retMsg { get; set; }
        public T retData { get; set; }
    }
}
