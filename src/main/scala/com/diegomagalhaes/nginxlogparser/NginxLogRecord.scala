package com.diegomagalhaes.nginxlogparser

/**
 * @see http://nginx.org/en/docs/http/ngx_http_log_module.html for details
 */
case class NginxLogRecord (
        clientIpAddress:String,         // should be an ip address, but may also be the hostname if hostname-lookups are enabled
        remoteUser:String,              // typically '-'
        dateTime:String,                // [day/month/year:hour:minute:second zone]
        verb:String,                    // HTTP verb GET, POST, etc
        URL:String,                     // Resource accessed (URL)
        HTTPVersion:String,             // HTTP version: 1.1, 1.0
        RequestProcessingTime:String,   // Request Time in ms
        ReceivedBytes:String,           // Bytes received in the response
        URLReferer:String,              // Referer URL
        UserAgent:String,               // Which User Agent
        UpstreamResponseTime:String,    // Upstream response time, typically '-'
        ResponseCode:String             // HTTP Status
)
