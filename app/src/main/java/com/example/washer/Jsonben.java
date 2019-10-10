package com.example.washer;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Jsonben  {
    private static final String TAG ="555" ;
    private static OkHttpClient okHttpClient;
    private static OkHttpClient okHttpClient2;
    public  static  int status = 1;
    //private OkHttpClient okHttpClient;

    public static void getOrderNum(String string,String string2,String string3) throws JSONException {

        JSONObject jsonObject = new JSONObject(string);
        boolean   ifSuccess = (boolean) jsonObject.get("success");
        String orderNum = (String) jsonObject.get("data");
        if (ifSuccess == true){

            Log.i(TAG, "orderNum接收成功:"+orderNum);

            creatOrd(orderNum,string2,string3) ;

        }else
            Log.i(TAG, "orderNum接收失败:"+orderNum);


    }


    public static void creatOrd(String string,String string2,String string3){
        new Thread() {
            @Override
            public void run() {               //https://www.httpbin.org/get?ordernum=2021760
                //                   请求order   https://wx.cleverwash.cn/api/v1/wx/order/getordernumber?schoolId=1
                //https://wx.cleverwash.cn/api/v1/wx/order/createorder?order_number=200202111052020223162760&open_id=oAD8O0S31OFppfK_szwOHw-fsk0M&union_id=ovT9q0niFnqUsCPHfU545qfiqcwo&order_money=3&pay_money=3&device_id=hs13634133&service_name=%E6%A0%87%E5%87%86%E6%B4%97
                String order_number = string;
                String open_id = "";
                String union_id = "";
                String order_money = "3";
                String pay_money = "3";
                String device_id = string3;
                String service_name = string2;

                Request request = new Request.Builder().url("https://wx.cleverwash.cn/api/v1/wx/order/createorder?"+"order_number="+order_number+"&"+"open_id="+open_id+"&"+"union_id="+union_id+"&"+"order_money="+order_money+"&"+"pay_money="+pay_money+"&"+"device_id="+device_id+"&"+"service_name="+service_name ).build();

                Call call = okHttpClient.newCall(request);
                try {
                    Response response = call.execute();
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    int ifSuccess = (int) jsonObject.get("code");
                    if (ifSuccess ==200 ){
                        Log.i(TAG, "成功创建订单:"+order_number);
                        confirmOrd(order_number);
                    }
                    //Log.i(TAG, "getResponse:" + response.body().string());
                    //Log.i(TAG, "成功创建订单:"+order_number);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        okHttpClient = new OkHttpClient();

    }

    private static void confirmOrd(String string) {
        new Thread() {
            @Override
            public void run() {
                String order_number = string;
                                                                //https://wx.cleverwash.cn/api/v1/wx/order/confirm?orderNumber=
                Request request = new Request.Builder().url("https://wx.cleverwash.cn/api/v1/wx/order/confirm?orderNumber="+order_number).build();
                //Request request = new Request.Builder().url("https://wx.cleverwash.cn/api/v1/wx/order/createorder?order_number=200202111052020223162760&open_id=oAD8O0S31OFppfK_szwOHw-fsk0M&union_id=ovT9q0niFnqUsCPHfU545qfiqcwo&order_money=3&pay_money=3&device_id=hs13634133").build();
                Call call = okHttpClient2.newCall(request);
                try {
                    Response response = call.execute();
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    //boolean ifSuccess =(boolean) jsonObject.get("success");
                    int ifSuccess = (int) jsonObject.get("code");
                    if (ifSuccess ==200 ){
                        //Log.i(TAG, "操作完成:");
                        status = 1;


                    }else
                        status = 0;
                        //Log.i(TAG, "操作失败:");

//                    Log.i(TAG, "getResponse:" + response.body().string());
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        okHttpClient2 = new OkHttpClient();
    }

}
