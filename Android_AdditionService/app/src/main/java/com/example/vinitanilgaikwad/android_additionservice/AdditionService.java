package com.example.vinitanilgaikwad.android_additionservice;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;


public class AdditionService extends Service {


    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return mBinder;
    }

    /**
     * IAdd definition is below
     */
    private final IAdd.Stub mBinder = new IAdd.Stub() {

        @Override
        public int add(int num1, int num2) throws RemoteException {
            // TODO Auto-generated method stub
            Log.d("debug", "Give numbers are " + num1 + num2);
            return (num1 + num2);
        }

        @Override
        public void sendMessage(String message) throws RemoteException {


            Log.d("Message from App1", message);
        }


    };
}
