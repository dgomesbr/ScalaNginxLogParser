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
              assert(r.URL == "/adserver/www/delivery/lg.php?bannerid=9388")
              assert(r.HTTPVersion == "HTTP/1.1")
              assert(r.RequestProcessingTime == "0.000")
              assert(r.ReceivedBytes == "43")
              assert(r.URLReferer == "http://wap.tim.com.br/")
              assert(r.UserAgent == "Mozilla/5.0 (Linux; U; Android 2.2; pt-br; GT-I5500B")
              assert(r.UpstreamResponseTime == "-")
              assert(r.Pipe == ".")
              assert(r.ResponseCode == "200")
            case None => fail("Record read is none")
          }

      }
  }

  describe("Trying to parse a record I used to fail on ...") {
      records = SampleCombinedAccessLogRecords.badRecord
      val parser = new NginxLineParser
      val rec = parser.parse(records.head)
      it("the result should not be None") {
          assert(rec == None)
      }
  }


}












