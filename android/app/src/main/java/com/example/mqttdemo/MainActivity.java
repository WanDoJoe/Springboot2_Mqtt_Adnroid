package com.example.mqttdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements IGetMessageCallBack{
    private TextView textView;
    private Button button;
    private MyServiceConnection serviceConnection;
    private MQTTService mqttService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        serviceConnection = new MyServiceConnection();
        serviceConnection.setIGetMessageCallBack(this);
        Intent intent = new Intent(this, MQTTService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);


        textView=findViewById(R.id.push_tv);
        button=findViewById(R.id.push_bn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MQTTService.publish("测试一下子");
            }
        });
    }
    @Override
    public void setMessage(String message) {
        textView.setText(message);
        mqttService = serviceConnection.getMqttService();
        mqttService.toCreateNotification(message);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    @Override
    protected void onDestroy() {
        unbindService(serviceConnection);
        super.onDestroy();
    }

}
