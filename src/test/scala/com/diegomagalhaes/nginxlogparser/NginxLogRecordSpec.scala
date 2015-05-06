package com.diegomagalhaes.nginxlogparser

import org.scalatest.{BeforeAndAfter, FunSpec, GivenWhenThen}

class NginxLogRecordSpec extends FunSpec with BeforeAndAfter with GivenWhenThen {

  var records: Seq[String] = _

  before {
      records = SampleCombinedAccessLogRecords.data
  }

  describe("Testing the first access log record ...") {
      records = SampleCombinedAccessLogRecords.data
      val parser = new NginxLineParser
      val rec = parser.parse(records.head)
      it("the result should not be None") {
        assert(rec != None)
      }
      it("the individual fields should be right") {
          rec match {
            case Some(r: NginxLogRecord) =>
              assert( r.clientIpAddress == "192.168.0.237")
              assert(r.remoteUser == "-")
              assert(r.dateTime == "04/May/2015:23:02:01 -0300")
              assert(r.verb == "GET")
              assert(r.URL == "/adserver/www/delivery/lg.php?bannerid=9388&campaignid=680&zoneid=1618&loc=1&referer=http%3A%2F%2Fwap.tim.com.br%2Flp%2Fsbappsclub&cb=0f7f34463a")
              assert(r.HTTPVersion == "HTTP/1.1")
              assert(r.RequestProcessingTime == "0.000")
              assert(r.ReceivedBytes == "43")
              assert(r.URLReferer == "http://wap.tim.com.br/")
              assert(r.UserAgent == "Mozilla/5.0 (Linux; U; Android 2.2; pt-br; GT-I5500B Build/FROYO) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1")
              assert(r.UpstreamResponseTime == "-")
              assert(r.Pipe == ".")
              assert(r.MSISDN == "-")
              assert(r.XCALL == "-")
              assert(r.ResponseCode == "200")
            case None => fail("Record read is none")
          }

      }
  }

 /* describe("Trying to parse a record I used to fail on ...") {
      records = SampleCombinedAccessLogRecords.badRecord
      val parser = new NginxLineParser
      val rec = parser.parse(records.head)
      it("the result should not be None") {
          assert(rec == None)
      }
  }*/


}












