﻿using BaiduAPIStoreConnector.Models;
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
    public class MobilePhoneController : TemplateAPIController
    {
        [HttpGet]
        [ActionName("GetMobilePhone")]
        public async Task<RetDataTeleNum> Get(string tel)
        {
            string completeUrl = string.Format("mobilephone?tel={0}", tel);
            return await InnerExecute<RetDataTeleNum>(completeUrl);
        }
    }
}
