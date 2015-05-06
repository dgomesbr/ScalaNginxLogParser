# A Scala Nginx Access Log Parser

This project can be used to parse Nginx access log records in JVM applications (Scala,
Java, etc). Inspired by the same code for parsing Apache Logs by  [Alvin Alexander](https://twitter.com/alvinalexander)

We're currently using this log setup in production nginx's
```
##
# Logging Settings
##
log_format timed_combined '$remote_addr - $remote_user [$time_local]  '
    '"$request" $request_time  $body_bytes_sent '
    '"$http_referer" Agent["$http_user_agent"] '
    '$upstream_response_time $pipe $status';

    access_log /var/log/nginx/access.log timed_combined buffer=256k;
```

## Discussion

It's a copy of [Alvin Alexander - ScalaApacheAccessLogParser](https://github.com/alvinj/ScalaApacheAccessLogParser) which works with Nginx access logs. 


## Usage

The API is in flux, but right now the usage starts like this:

```scala
    val rawRecord = """192.168.0.237 - - [04/May/2015:23:02:01 -0300]  "GET /adserver/www/delivery/lg.php?bannerid=9388 HTTP/1.1" 0.000  43 "http://wap.tim.com.br/" Agent["Mozilla/5.0 (Linux; U; Android 2.2; pt-br; GT-I5500B"] - . 200""""
    
    val parser = NginxLineParser
    val line = parser.parse(rawRecord)    // an NginxLogRecord instance
```

The `NginxLogRecord` class definition looks like this:

```scala
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
        PipeTime:String,                // Pipe typically '.'
        ResponseCode:String             // HTTP Status
    )
```

In the test code you'll see that I use the parser like this:

```scala
    val parser = new NginxLineParser
    val rec = parser.parseRecord(rawRecord)
    it("the result should not be None") {
        assert(rec != None)
    }
    it("the individual fields should be right") {
        rec.foreach { r =>
            assert(r.clientIpAddress == "192.168.0.237")
            assert(r.remoteUser == "-")
            assert(r.dateTime == "04/May/2015:23:02:01 -0300")
            assert(r.verb == "GET")
            assert(r.URL == "/adserver/www/delivery/lg.php?bannerid=9388")
            assert(r.HTTPVersion == "HTTP/1.1")
            assert(r.RequestProcessingTime == "0.000")
            assert(r.ReceivedBytes:String == "43")
            assert(r.URLReferer:String == "http://wap.tim.com.br/")
            assert(r.UserAgent:String == "Mozilla/5.0 (Linux; U; Android 2.2; pt-br; GT-I5500B")
            assert(r.UpstreamResponseTime == "-")
            assert(r.PipeTime == ".")
            assert(r.ResponseCode == "200")
        }
    }
```

Tests are defined in the `NginxLogRecordSpec` class.


## Building

This project is a typical Scala/SBT project, so just use commands like this:

    sbt compile
    sbt test
    sbt package


## More information

For more information about yours truly:

* [See my website](http://diegomagalhaes.com)
* [Find me here on Twitter](https://twitter.com/dgomesbr)

All the best,    
Diego Magalhães
http://diegomagalhaes.com









