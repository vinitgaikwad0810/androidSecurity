package com.example.vinitanilgaikwad.app1;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);

        Intent svc = new Intent();
        svc.setComponent(new ComponentName(
                "com.example.vinitanilgaikwad.app2",                    // App2's package
                "com.example.vinitanilgaikwad.app2.MessengerService"));  // FQN of App2's service
        svc.putExtra("MyString", message);
        startService(svc);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.content);
        layout.addView(textView);

        //    Intent serviceIntent = new Intent("com.example.vinitanilgaikwad.app2");

        //  bindService(serviceIntent, myConnection, Context.BIND_AUTO_CREATE);

        //  startService(serviceIntent);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }


    Messenger myService = null;
    boolean isBound;

    private ServiceConnection myConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            myService = new Messenger(service);
            isBound = true;
        }

        public void onServiceDisconnected(ComponentName className) {
            myService = null;
            isBound = false;
        }
    };

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//
//        unbindService(myConnection);
//    }

    public void sendMessageToApp2(View view) {


//       // if (!isBound) return;
////
////        Message msg = Message.obtain();
////
        Bundle bundle = new Bundle();
        bundle.putString("MyString", "Vinit");

//
//
//
//     //   msg.setData(bundle);
//
//        try {
//            myService.send(msg);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//    }

    }
}