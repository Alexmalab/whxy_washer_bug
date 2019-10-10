package com.example.washer;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static String Num = "2021766";
    private OkHttpClient okHttpClient;
    private EditText edt;
    private String deviceNo;
    ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        edt = findViewById(R.id.editTextTextPersonName);
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


            }
        });

    }

    public void mainStart(String string,String string1) {
        OkHttpClient  okHttpClient = new OkHttpClient();
        //Toast.makeText(getApplicationContext(), "ceshi"+string, Toast.LENGTH_SHORT).show();
        new Thread() {
            @Override
             publicvoid run() {               //https://www.httpbin.org/get?ordernum=2021760
                //                   请求order   https://wx.cleverwash.cn/api/v1/wx/order/getordernumber?schoolId=1
                Request request = new Request.Builder().url("https://wx.cleverwash.cn/api/v1/wx/order/getordernumber?schoolId=1").build();

                Call call = okHttpClient.newCall(request);
                try {
                    Response response = call.execute();
                    //Log.i(TAG, "getResponse:" + response.body().string()); //打印日志


                    Jsonben.getOrderNum(response.body().string(),string,string1);

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
    public void popuMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this,view);
        MenuInflater inflater = popupMenu.getMenuInflater();

        popupMenu.setOnMenuItemClickListener(item -> {
            switch(item.getItemId()){
                case R.id.djx:
                    deviceNo = edt.getText().toString();
                    Toast.makeText(getApplicationContext(), "机器编号"+deviceNo, Toast.LENGTH_SHORT).show();
                    mainStart("大件洗","hs"+deviceNo);
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(Jsonben.status == 1){
                        myTips("成功");
                    }else
                        myTips("失败");
                    // Log.i(TAG, "getResponse:"+ab);

                //Toast.makeText(getApplicationContext(), "大件洗", Toast.LENGTH_SHORT).show();
                return true;
                case R.id.ksx:
                    deviceNo = edt.getText().toString();
                    mainStart("快速洗","hs"+deviceNo);
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(Jsonben.status == 1){
                        myTips("成功");
                    }else
                        myTips("失败");
                    //Toast.makeText(getApplicationContext(), "快速洗", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.dts:
                    deviceNo = edt.getText().toString();
                    mainStart("单脱水","hs"+deviceNo);
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(Jsonben.status == 1){
                        myTips("成功");
                    }else
                        myTips("失败");
                    //Toast.makeText(getApplicationContext(), "单脱水", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.bzx:
                    deviceNo = edt.getText().toString();
                    mainStart("标准洗","hs"+deviceNo);
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(Jsonben.status == 1){
                        myTips("成功");
                    }else
                        myTips("失败");
                    //Toast.makeText(getApplicationContext(), "标准洗", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.qrh:
                    deviceNo = edt.getText().toString();
                    mainStart("轻柔烘","dr"+deviceNo);
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(Jsonben.status == 1){
                        myTips("成功");
                    }else
                        myTips("失败");
                    //Toast.makeText(getApplicationContext(), "标准洗", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.bzh:
                    deviceNo = edt.getText().toString();
                    mainStart("标准烘","dr"+deviceNo);
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(Jsonben.status == 1){
                        myTips("成功");
                    }else
                        myTips("失败");
                    //Toast.makeText(getApplicationContext(), "标准洗", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.lgh:
                    deviceNo = edt.getText().toString();
                    mainStart("晾干烘","dr"+deviceNo);
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(Jsonben.status == 1){
                        myTips("成功");
                    }else
                        myTips("失败");
                    //Toast.makeText(getApplicationContext(), "标准洗", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.qlh:
                    deviceNo = edt.getText().toString();
                    mainStart("强力烘","hs"+deviceNo);
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(Jsonben.status == 1){
                        myTips("成功");
                    }else
                        myTips("失败");
                    //Toast.makeText(getApplicationContext(), "标准洗", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        });
        inflater.inflate(R.menu.popupmenu,popupMenu.getMenu());
        popupMenu.show();
    }
    public  void myTips(String string){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher)
                .setTitle("提示")
                .setMessage(string)
                .create()
                .show();
    }

}

