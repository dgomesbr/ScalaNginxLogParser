package com.diegomagalhaes.nginxlogparser

object SampleCombinedAccessLogRecords {

  val data = """
192.168.0.237 - - [04/May/2015:23:02:01 -0300]  "GET /adserver/www/delivery/lg.php?bannerid=9388 HTTP/1.1" 0.000  43 "http://wap.tim.com.br/" Agent["Mozilla/5.0 (Linux; U; Android 2.2; pt-br; GT-I5500B"] - . 200
192.168.0.237 - - [04/May/2015:23:02:01 -0300]  "GET /adserver/www/delivery/lg.php?bannerid=9389&campaignid=680&zoneid=1619&loc=1&referer=http%3A%2F%2Fwap.tim.com.br%2Flp%2Fsbappsclub&cb=e84e481818 HTTP/1.1" 0.000  43 "http://wap.tim.com.br/" Agent["Mozilla/5.0 (Linux; U; Android 2.2; pt-br; GT-I5500B Build/FROYO) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1"] - . 200
192.168.0.237 - - [04/May/2015:23:02:01 -0300]  "GET /adserver/www/delivery/lg.php?bannerid=9432&campaignid=681&zoneid=1637&loc=1&referer=http%3A%2F%2Fwap.tim.com.br%2Flp%2Fsbappsclub&cb=5455b229b0 HTTP/1.1" 0.000  43 "http://wap.tim.com.br/" Agent["Mozilla/5.0 (Linux; U; Android 2.2; pt-br; GT-I5500B Build/FROYO) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1"] - . 200
192.168.0.102 - - [04/May/2015:23:02:01 -0300]  "GET /adserver/www/delivery/new_spc.php?zones=1638, HTTP/1.1" 0.000  426 "-" Agent["Mozilla/5.0 (Linux; U; Android 4.1.2; pt-br; LG-P655H Build/JZO54K) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30"] - . 200
192.168.0.102 - - [04/May/2015:23:02:01 -0300]  "GET /adserver/www/delivery/lg.php?bannerid=9348&campaignid=676&zoneid=1554&loc=1&cb=da3d687dcc HTTP/1.1" 0.000  43 "http://wap.tim.com.br/" Agent["Mozilla/5.0 (Linux; U; Android 4.1.2; pt-br; LG-E467f Build/JZO54K) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30"] - . 200
192.168.0.102 - - [04/May/2015:23:02:01 -0300]  "GET /adserver/www/delivery/lg.php?bannerid=9310&campaignid=655&zoneid=1630&loc=1&cb=cb81d8936c HTTP/1.1" 0.000  43 "http://wap.tim.com.br/" Agent["Mozilla/5.0 (Linux; U; Android 2.3.6; pt-br; GT-S5830B Build/GINGERBREAD) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1"] - . 200
192.168.0.102 - - [04/May/2015:23:02:01 -0300]  "GET /adserver/www/delivery/new_spc.php?zones=1638, HTTP/1.1" 0.000  426 "-" Agent["Mozilla/5.0 (Linux; U; Android 2.3.4; pt-br; GT-S5830B Build/GINGERBREAD) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1"] - . 200
192.168.0.102 - - [04/May/2015:23:02:01 -0300]  "GET /adserver/www/delivery/lg.php?bannerid=9432&campaignid=681&zoneid=1637&loc=1&cb=fe8e6f00b0 HTTP/1.1" 0.000  43 "http://wap.tim.com.br/" Agent["Mozilla/5.0 (LG-T375 AppleWebkit/531 Browser/Phantom/V2.0 Widget/LGMW/3.0 MMS/LG-MMS-V1.0/1.2 Java/ASVM/1.1 Profile/MIDP-2.1 Configuration/CLDC-1.1)"] - . 200
192.168.0.102 - - [04/May/2015:23:02:01 -0300]  "GET /adserver/www/delivery/lg.php?bannerid=9349&campaignid=676&zoneid=1555&loc=1&cb=91e0c8a58b HTTP/1.1" 0.000  43 "http://wap.tim.com.br/" Agent["Mozilla/5.0 (Linux; U; Android 4.1.2; pt-br; LG-E467f Build/JZO54K) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30"] - . 200
192.168.0.102 - - [04/May/2015:23:02:01 -0300]  "GET /adserver/www/delivery/lg.php?bannerid=9382&campaignid=679&zoneid=1519&cb=40f1441eb8 HTTP/1.1" 0.000  43 "-" Agent["MOT-EX128 Obigo/WAP2.0 MIDP-2.0/CLDC-1.1"] - . 200
""".split("\n").filter(_ != "")

  val badRecord = """
66.249.70.10 - - [23/Feb/2014:03:21:59 -0700] "GET /blog/post/java/how-load-multiple-spring-context-files-standalone/ HTTP/1.0" 301 - "-" "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)"
""".split("\n").filter(_ != "")


}

