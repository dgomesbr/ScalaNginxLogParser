package com.diegomagalhaes.nginxlogparser

import java.util.regex.{Matcher, Pattern}

/**
 * A sample record:
 * 192.168.0.237 - - [04/May/2015:23:02:01 -0300]  "GET /adserver/www/delivery/lg.php?bannerid=9388 HTTP/1.1" 0.000  43 "http://wap.tim.com.br/" Agent["Mozilla/5.0 (Linux; U; Android 2.2; pt-br; GT-I5500B"] - . 200
 * 
 * I put this code in the 'class' so (a) the pattern could be pre-compiled and (b) the user can create
 * multiple instances of this parser, in case they want to work in a multi-threaded way.
 * I don't know that this is necessary, but I think it is for this use case.
 * 
 */
class NginxLineParser extends Serializable {
  private val regex = "([^-]*)\\s+-\\s+(\\S+)\\s+\\[(\\d{2}\\/[a-zA-Z]{3}\\/\\d{4}:\\d{2}:\\d{2}:\\d{2}\\s+-\\d{4})\\]\\s+\"(.+)\"\\s+(\\d{1,}\\.\\d{3})\\s+(\\d+)\\s+\"([^\"]+)\"\\s+Agent\\[\"([^\"]+)\"\\]\\s+(-|\\d.\\d{3,})\\s+(\\S+)\\s+(\\d{1,}).*"
  private val p = Pattern.compile(regex)


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

    def buildFromMatcher(matcher: Matcher) = {

      val requestTuple = parseRequestField(matcher.group(4))

      Some(NginxLogRecord(
        matcher.group(1),
        matcher.group(2),
        matcher.group(3),
        if (requestTuple.isDefined) requestTuple.get._1 else "",
        if (requestTuple.isDefined) requestTuple.get._2 else "",
        if (requestTuple.isDefined) requestTuple.get._3 else "",
        matcher.group(5),
        matcher.group(6),
        matcher.group(7),
        matcher.group(8),
        matcher.group(9),
        matcher.group(10),
        matcher.group(11)
      ))
    }

    val matcher = p.matcher(record)
    if (matcher.find) buildFromMatcher(matcher) else None
  }
}

object NginxLineParser




