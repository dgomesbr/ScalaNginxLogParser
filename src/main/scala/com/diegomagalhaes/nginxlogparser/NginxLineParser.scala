package com.diegomagalhaes.nginxlogparser

import java.util.regex.{Matcher, Pattern}

/**
 * A sample record:
 * 192.168.0.237 - - [04/May/2015:23:02:01 -0300]  "GET /adserver/www/delivery/lg.php?bannerid=9388&campaignid=680&zoneid=1618&loc=1&referer=http%3A%2F%2Fwap.tim.com.br%2Flp%2Fsbappsclub&cb=0f7f34463a HTTP/1.1" 0.000  43 "http://wap.tim.com.br/" Agent["Mozilla/5.0 (Linux; U; Android 2.2; pt-br; GT-I5500B Build/FROYO) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1"] - . msisdn[-] xcall[-] 200
 * 
 * I put this code in the 'class' so (a) the pattern could be pre-compiled and (b) the user can create
 * multiple instances of this parser, in case they want to work in a multi-threaded way.
 * I don't know that this is necessary, but I think it is for this use case.
 * 
 */
class NginxLineParser extends Serializable {
  private val regex = "([^-]*)\\s+-\\s+(\\S+)\\s+\\[(\\d{2}\\/[a-zA-Z]{3}\\/\\d{4}:\\d{2}:\\d{2}:\\d{2}\\s+-\\d{4})\\]\\s+\\\"(.+)\\\"\\s+(\\d{1,}\\.\\d{3})\\s+(\\d+)\\s+\\\"([^\\\"]+)\\\"\\s+Agent\\[\\\"([^\\\"]+)\\\"\\]\\s+(-|\\d.\\d{3,})\\s+(\\S+)\\s+msisdn\\[(\\S+)\\]\\s+xcall\\[(\\S+)\\]\\s+(\\d{1,}).*".r

  /**
   * @param record Assumed to be an Nginx access log.
   * @return An NginxLogRecord instance wrapped in an Option.
   */
  def parse(record: String): Option[NginxLogRecord] = {

    def parseRequestField(request: String): Option[(String, String, String)] = {
      request.split(" ").toList match {
        case List(a, b, c) => Some((a, b, c))
        case other => None
      }
    }

    record match {
        case regex(ip, ruser, datetime, req, reqtime, recbytes, ref, ua, upstreamtime, pipe, msisdn, xcall, status) =>
          val requestTuple = parseRequestField(req)
          Some(
            NginxLogRecord(
              ip,
              ruser,
              datetime,
              if (requestTuple.isDefined) requestTuple.get._1 else "",
              if (requestTuple.isDefined) requestTuple.get._2 else "",
              if (requestTuple.isDefined) requestTuple.get._3 else "",
              reqtime,
              recbytes,
              ref,
              ua,
              upstreamtime,
              pipe,
              msisdn,
              xcall,
              status
            )
         )
      case _ => None
    }
  }

}

object NginxLineParser




