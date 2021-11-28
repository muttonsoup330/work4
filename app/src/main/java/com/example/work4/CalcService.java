package com.example.work4;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class CalcService extends Service {
    public CalcService() {
    }
    private LocalBinder mLocalBinder=new LocalBinder();
    public class LocalBinder extends Binder {
        CalcService getService(){
            return CalcService.this;
        }
    }
    public int jia(int x, int y){
        return x+y;
    }
    public int jian(int x,int y){
        return x-y;
    }
    public int cheng(int x,int y){
        return x*y;
    }
    public int chu(int x,int y){
        return x/y;
    }
    @Override
    public IBinder onBind(Intent intent) {
        return mLocalBinder;
    }
}