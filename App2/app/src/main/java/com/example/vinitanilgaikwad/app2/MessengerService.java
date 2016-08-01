package com.example.vinitanilgaikwad.app2;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class MessengerService extends Service {
    //    class IncomingHandler extends Handler {
//        @Override
//        public void handleMessage(Message msg) {
//
////            Log.i("dddsds","fsdfdsfsfs");
////            Bundle data = msg.getData();
////            String dataString = data.getString("MyString");
////            Toast.makeText(getApplicationContext(),
////                    dataString, Toast.LENGTH_SHORT).show();
////            Log.d("Me123",dataString);
//
//            Log.d("Vinit","Vinit");
//        }
//    }
//
//    final Messenger myMessenger = new Messenger(new IncomingHandler());
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        return myMessenger.getBinder();
//    }
//    private PendingIntent intentToBasicActivity;
//    private Message message;
//
//    /**
//     * For showing and hiding our notification.
//     */
//    NotificationManager mNM;
//    /**
//     * Keeps track of all current registered clients.
//     */
//    ArrayList<Messenger> mClients = new ArrayList<Messenger>();
//    /**
//     * Holds last value set by a client.
//     */
//    int mValue = 0;
//
//    /**
//     * Command to the service to register a client, receiving callbacks
//     * from the service.  The Message's replyTo field must be a Messenger of
//     * the client where callbacks should be sent.
//     */
//    static final int MSG_REGISTER_CLIENT = 1;
//
//    /**
//     * Command to the service to unregister a client, ot stop receiving callbacks
//     * from the service.  The Message's replyTo field must be a Messenger of
//     * the client as previously given with MSG_REGISTER_CLIENT.
//     */
//    static final int MSG_UNREGISTER_CLIENT = 2;
//
//    /**
//     * Command to service to set a new value.  This can be sent to the
//     * service to supply a new value, and will be sent by the service to
//     * any registered clients with the new value.
//     */
//    static final int MSG_SET_VALUE = 3;
//
//    /**
//     * Handler of incoming messages from clients.
//     */
//    class IncomingHandler extends Handler {
//        @Override
//        public void handleMessage(Message msg) {
//
//
//            // The PendingIntent to launch our activity if the user selects this notification
//          //  setPendingIntent(msg);
//            switch (msg.what) {
//                case MSG_REGISTER_CLIENT:
//                    mClients.add(msg.replyTo);
//                    break;
//                case MSG_UNREGISTER_CLIENT:
//                    mClients.remove(msg.replyTo);
//                    break;
//                case MSG_SET_VALUE:
//                    mValue = msg.arg1;
//                    for (int i = mClients.size() - 1; i >= 0; i--) {
//                        try {
//                            mClients.get(i).send(Message.obtain(null,
//                                    MSG_SET_VALUE, mValue, 0));
//                        } catch (RemoteException e) {
//                            // The client is dead.  Remove it from the list;
//                            // we are going through the list from back to front
//                            // so this is safe to do inside the loop.
//                            mClients.remove(i);
//                        }
//                    }
//                    break;
//                default:
//                    super.handleMessage(msg);
//            }
//        }
//    }
//
//    private void setPendingIntent(Message msg) {
//
//        intentToBasicActivity = PendingIntent.getActivity(this, 0,
//                new Intent(this, BasicActivity.class).putExtra("Vinit", msg.getData()), 0);
//        // Set the info for the views that show in the notification panel.
//        Notification notification = new Notification.Builder(this)
//                .setSmallIcon(R.drawable.stat_sample)  // the status icon
//                .setTicker("Vinit")  // the status text
//                .setWhen(System.currentTimeMillis())  // the time stamp
//                .setContentTitle(getText(R.string.local_service_label))  // the label of the entry
//                .setContentText("Vinit")  // the contents of the entry
//                .setContentIntent(intentToBasicActivity)  // The intent to send when the entry is clicked
//                .build();
//    }
//
//    /**
//     * Target we publish for clients to send messages to IncomingHandler.
//     */
//    final Messenger mMessenger = new Messenger(new IncomingHandler());
//
//    @Override
//    public void onCreate() {
//        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//
//        // Display a notification about us starting.
//        showNotification();
//    }
//
//    @Override
//    public void onDestroy() {
//        // Cancel the persistent notification.
//        mNM.cancel(R.string.remote_service_started);
//
//        // Tell the user we stopped.
//        Toast.makeText(this, R.string.remote_service_stopped, Toast.LENGTH_SHORT).show();
//    }
//
//    /**
//     * When binding to the service, we return an interface to our messenger
//     * for sending messages to the service.
//     */
//    @Override
//    public IBinder onBind(Intent intent) {
//        return mMessenger.getBinder();
//    }
//
//    /**
//     * Show a notification while this service is running.
//     */
//    private void showNotification() {
//        // In this sample, we'll use the same text for the ticker and the expanded notification
//        CharSequence text = getText(R.string.remote_service_started);
//
//        // The PendingIntent     to launch our activity if the user selects this notification
//        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
//                new Intent(this, BasicActivity.class).putExtra("Vinit", "Vinit"), 0);
//
//
//        // Set the info for the views that show in the notification panel.
//        Notification notification = new Notification.Builder(this)
//                .setSmallIcon(R.drawable.stat_sample)  // the status icon
//                .setTicker(text)  // the status text
//                .setWhen(System.currentTimeMillis())  // the time stamp
//                .setContentTitle(getText(R.string.local_service_label))  // the label of the entry
//                .setContentText(text)  // the contents of the entry
//                .setContentIntent(contentIntent)  // The intent to send when the entry is clicked
//                .build();
//
//        // Send the notification.
//        // We use a string id because it is a unique number.  We use it later to cancel.
//        mNM.notify(R.string.remote_service_started, notification);
//    }

    public static final String INIT ="init";
    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {

            Bundle data = msg.getData();
            String dataString = data.getString("MyString");

            Toast.makeText(getApplicationContext(),
                    dataString, Toast.LENGTH_SHORT).show();
            changeActivity(dataString);

        }
    }

    public void changeActivity(String dataString) {
        Intent intent = new Intent(getBaseContext(), BasicActivity.class);
        intent.putExtra("Vinit", dataString);
        startActivity(intent);
    }

    final Messenger myMessenger = new Messenger(new IncomingHandler());

    @Override
    public IBinder onBind(Intent intent) {
        return myMessenger.getBinder();
    }
}
