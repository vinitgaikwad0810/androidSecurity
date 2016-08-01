package com.example.vinitanilgaikwad.android_addition;


import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vinitanilgaikwad.android_additionservice.IAdd;

import java.util.List;

public class MainActivity extends Activity implements OnClickListener {
    EditText etValue1, etValue2;
    Button bAdd;
    TextView mSum;
    protected IAdd AddService;
    ServiceConnection AddServiceConnection;
    private static Integer counter = 0;

    /**
     * Called when the activity is first created.
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etValue1 = (EditText) findViewById(R.id.value1);
        etValue2 = (EditText) findViewById(R.id.value2);
        mSum = (TextView) findViewById(R.id.sum);
        bAdd = (Button) findViewById(R.id.add);
        bAdd.setOnClickListener(this);

        initConnection();

    }

    void initConnection() {
        AddServiceConnection = new ServiceConnection() {

            @Override
            public void onServiceDisconnected(ComponentName name) {
                // TODO Auto-generated method stub
                AddService = null;
                Toast.makeText(getApplicationContext(), "Service Disconnected",
                        Toast.LENGTH_SHORT).show();
                Log.d("IRemote", "Binding - Service disconnected");
            }

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                // TODO Auto-generated method stub
                AddService = IAdd.Stub.asInterface((IBinder) service);

                Toast.makeText(getApplicationContext(),
                        (++counter) + " Service Connected", Toast.LENGTH_SHORT)
                        .show();

                try {
                    Log.d("IRemote", "Binding is done - " + service.getInterfaceDescriptor() + "Service connected");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        };
        if (AddService == null) {
            Intent it = new Intent();
            it.setAction("service.Calculator");
            it = createExplicitFromImplicitIntent(getApplicationContext(), it);
            // binding to remote service
            bindService(it, AddServiceConnection, Service.BIND_AUTO_CREATE);
        }
    }

    protected void onDestroy() {

        super.onDestroy();
        unbindService(AddServiceConnection);
    }

    ;

    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.add: {
                int num1 = Integer.parseInt(etValue1.getText().toString());
                int num2 = Integer.parseInt(etValue2.getText().toString());

                try {
                    mSum.setText(Html.fromHtml("<b>Result:" + AddService.add(num1, num2) + "</b>"));
                    AddService.sendMessage("Hello World");
                    Log.d("IRemote", "Binding - Add operation");
                } catch (RemoteException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            break;
        }
    }


    /***
     * Android L (lollipop, API 21) introduced a new problem when trying to invoke implicit intent,
     * "java.lang.IllegalArgumentException: Service Intent must be explicit"
     * <p/>
     * If you are using an implicit intent, and know only 1 target would answer this intent,
     * This method will help you turn the implicit intent into the explicit form.
     * <p/>
     * Inspired from SO answer: http://stackoverflow.com/a/26318757/1446466
     *
     * @param context
     * @param implicitIntent - The original implicit intent
     * @return Explicit Intent created from the implicit original intent
     */
    public static Intent createExplicitFromImplicitIntent(Context context, Intent implicitIntent) {
        // Retrieve all services that can match the given intent
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);

        Log.d("debug", "Target answering this intent would be " + resolveInfo);
        // Make sure only one match was found
//        if (resolveInfo == null || resolveInfo.size() != 1) {
//            return null;
//        }

        // Get component info and create ComponentName
        ResolveInfo serviceInfo = resolveInfo.get(0);
        String packageName = serviceInfo.serviceInfo.packageName;
        String className = serviceInfo.serviceInfo.name;
        ComponentName component = new ComponentName(packageName, className);

        // Create a new intent. Use the old one for extras and such reuse
        Intent explicitIntent = new Intent(implicitIntent);

        // Set the component to be explicit
        explicitIntent.setComponent(component);

        return explicitIntent;
    }
}
