package com.yongjun.stock;


/**
 * Created by weh on 2017/7/8.
 */
public class AppTest {
  public static void main(String[] args) {

    int i = 0 ;
//    while (true){

      String time = "1498873047";
      String token = "ccf04544284344bbbca6b62af1b1f39f";

      String reqStr ="{\"companyId\":12,\"interId\":\"toa.processBizFlow\",\"operate\":\"select\",\"ver\":1000}";

      String key = "adkj58ghf7545ytjk";
      String signStr = "time" + time + "token" + token + reqStr +key;
      String url = "https://nideqianbao-api.milicaixian.cn/nideqianbao/appservice?token="+token+"&time="+time+"&sign=";

      url = url + MD5Util.MD5(signStr).toLowerCase();
//    String url = "http://192.168.1.185:8080/huajinbao/appservice?token=7bfb07c016de4a0d9b34e1a8312a3b58&time=1498873047&sign=424d575461f4bec906e13c7e14ac3cec";
//    String reqStr = "{interId:'toa.submitCardBind',cardNo:6217850800010522439,mobile:18221604420,companyId:1,ver:1000,custId:16}";

      System.out.println( url );

//      String s = HttpClient4Utils.httpPost(url, reqStr);
//      System.out.println(s);

//      i++;
//      System.out.println(i);
//      if(i>1000) break;

//    }


  }

}
