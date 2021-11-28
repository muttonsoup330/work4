package com.example.work4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ServiceConnection mServiceConnection;
    CalcService mCalcService;
    String  xstr,ystr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mServiceConnection=new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder iBinder) {
                mCalcService=((CalcService.LocalBinder)iBinder).getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        Button button0=findViewById(R.id.button0);
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText text1 = (EditText)findViewById(R.id.text1);
                EditText text2 = (EditText)findViewById(R.id.text2);
                xstr =text1.getText().toString();
                ystr =text2.getText().toString();
            }
        });
        Button button1=findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,CalcService.class);
                bindService(intent,mServiceConnection, Service.BIND_AUTO_CREATE);
            }
        });
        Button button2=findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCalcService!=null) {
                    unbindService(mServiceConnection);
                    Toast.makeText(MainActivity.this,"stop service",Toast.LENGTH_LONG).show();
                }
            }
        });
        Button button3=findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mCalcService!=null)
                    Toast.makeText(MainActivity.this,"Using service +:"
                            +mCalcService.jia(Integer.parseInt(xstr),Integer.parseInt(ystr)),Toast.LENGTH_LONG).show();
            }
        });
        Button button4=findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCalcService!=null)
                    Toast.makeText(MainActivity.this,"Using service -:"
                            +mCalcService.jian(Integer.parseInt(xstr),Integer.parseInt(ystr)),Toast.LENGTH_LONG).show();
            }
        });
        Button button5=findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCalcService!=null)
                    Toast.makeText(MainActivity.this,"Using service *:"
                            +mCalcService.cheng(Integer.parseInt(xstr),Integer.parseInt(ystr)),Toast.LENGTH_LONG).show();
            }
        });
        Button button6=findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCalcService!=null)
                    Toast.makeText(MainActivity.this,"Using service /:"
                            +mCalcService.chu(Integer.parseInt(xstr),Integer.parseInt(ystr)),Toast.LENGTH_LONG).show();
            }
        });
    }
}